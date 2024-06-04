package com.ibnucoding.iceloating.window.floatingwindow;

import static com.ibnucoding.iceloating.window.floatingbackground.FloatingBackgroundUtils.setFloatingBackgroundListener;
import static com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item.setFloatingWindowListener;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.dashboard.DashboardUtils;
import com.ibnucoding.iceloating.window.floatingbackground.FloatingBackgroundListener;
import com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item;
import com.ibnucoding.iceloating.window.floatingwindow.helper.FloatingShowOrHideHelperSafety;

public class FloatingWindow extends Service implements SensorEventListener {

    private static final int SHAKE_THRESHOLD = 800;
    boolean isFloatingActive;
    FloatingWindowUtils utils;
    private ViewGroup floatView, safetyView;
    private WindowManager windowManager;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastUpdate;
    private float last_x, last_y, last_z;

    @Override
    public void onCreate() {
        super.onCreate();

        if (!DashboardUtils.getUSE_VIBRATION()) {
            DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();

            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            floatView = (ViewGroup) inflater.inflate(R.layout.window_floating_window, null);
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

            safetyView = (ViewGroup) inflater.inflate(R.layout.window_double_safety, null);

            SharedPreferences sp = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);

            utils = new FloatingWindowUtils(sp);
            utils.init(floatView, safetyView, windowManager, metrics, getApplicationContext());

            isFloatingActive = true;

            setFloatingWindowListener(new FloatingWindowListener() {
                @Override
                public void onHideButtonClick() {
                    utils.hideFloating();
                }

                @Override
                public void onKeyboarButtonClick() {
                    // Implement if needed
                }

                @Override
                public void onBypassButtonClick() {
                    utils.bypassFloating();
                }

                @Override
                public void onDeactivateButtonClick() {
                    Toast.makeText(getApplicationContext(), getString(R.string.deactivate_notification), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStopBypassButtonClick() {
                    utils.bypassFloating();
                    utils.showFloating();
                }

                @Override
                public void onShowFloatingButtonClick() {
                    utils.showFloating();
                }

                @Override
                public void onDeactivateButtonLongPress() {
                    onDestroy();
                }
            });

            setFloatingBackgroundListener(new FloatingBackgroundListener() {
                @Override
                public void backgroundActive() {
                    if (isFloatingActive) {
                        windowManager.removeView(floatView);
                        utils.startFloating();
                    } else {
                        utils.startFloating();
                    }

                    isFloatingActive = true;
                }

                @Override
                public void backgroundNonActive() {
                    if (isFloatingActive) {
                        windowManager.removeView(floatView);
                        isFloatingActive = false;
                        BottomNavbarLayer2Item.deactivateKeyboard();
                    }
                }
            });


        } else {
            if (DashboardUtils.getANTI_OBSCURE()) {
                DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();

                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                floatView = (ViewGroup) inflater.inflate(R.layout.window_floating_window, null);
                windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

                safetyView = (ViewGroup) inflater.inflate(R.layout.window_double_safety, null);

                SharedPreferences sp = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);

                utils = new FloatingWindowUtils(sp);
                utils.init(floatView, safetyView, windowManager, metrics, getApplicationContext());

                isFloatingActive = false;
                windowManager.removeView(floatView);

                setFloatingWindowListener(new FloatingWindowListener() {
                    @Override
                    public void onHideButtonClick() {
                        utils.hideFloating();
                    }

                    @Override
                    public void onKeyboarButtonClick() {
                        // Implement if needed
                    }

                    @Override
                    public void onBypassButtonClick() {
                        utils.bypassFloating();
                    }

                    @Override
                    public void onDeactivateButtonClick() {
                        Toast.makeText(getApplicationContext(), getString(R.string.deactivate_notification), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStopBypassButtonClick() {
                        utils.bypassFloating();
                        utils.showFloating();
                    }

                    @Override
                    public void onShowFloatingButtonClick() {
                        utils.showFloating();
                    }

                    @Override
                    public void onDeactivateButtonLongPress() {
                        onDestroy();
                    }
                });

                setFloatingBackgroundListener(new FloatingBackgroundListener() {
                    @Override
                    public void backgroundActive() {
                        if (isFloatingActive) {
                            windowManager.removeView(floatView);
                            utils.startFloating();
                        } else {
                            utils.startFloating();
                        }

                        isFloatingActive = true;
                    }

                    @Override
                    public void backgroundNonActive() {
                        if (isFloatingActive) {
                            windowManager.removeView(floatView);
                            isFloatingActive = false;
                            BottomNavbarLayer2Item.deactivateKeyboard();
                        }
                    }
                });


            }
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            if (sensorManager != null) {
                accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            lastUpdate = System.currentTimeMillis();
            if (accelerometer != null) {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }

            Toast.makeText(getApplicationContext(), "Sensor getar diaktifkan, goyangkan perangkat anda saat ingin memunculkan Iceloating", Toast.LENGTH_LONG).show();
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isFloatingActive) {
            windowManager.removeView(floatView);
            isFloatingActive = false;
        }

        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        if (FloatingShowOrHideHelperSafety.isSafetyActive()) {
            windowManager.removeView(safetyView);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager.isNotificationPolicyAccessGranted()) {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
        }


        stopSelf();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float deltaX = x - last_x;
                float deltaY = y - last_y;
                float deltaZ = z - last_z;

                last_x = x;
                last_y = y;
                last_z = z;

                float speed = Math.abs(deltaX + deltaY + deltaZ) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    onShake();
                }
            }
        }
    }

    private void onShake() {
        if (!DashboardUtils.getANTI_OBSCURE()) {
            if (!isFloatingActive) {
                try {
                    DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();

                    LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    floatView = (ViewGroup) inflater.inflate(R.layout.window_floating_window, null);
                    windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

                    safetyView = (ViewGroup) inflater.inflate(R.layout.window_double_safety, null);

                    SharedPreferences sp = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);

                    utils = new FloatingWindowUtils(sp);
                    utils.init(floatView, safetyView, windowManager, metrics, getApplicationContext());

                    isFloatingActive = true;

                    setFloatingWindowListener(new FloatingWindowListener() {
                        @Override
                        public void onHideButtonClick() {
                            utils.hideFloating();
                        }

                        @Override
                        public void onKeyboarButtonClick() {
                            // Implement if needed
                        }

                        @Override
                        public void onBypassButtonClick() {
                            utils.bypassFloating();
                        }

                        @Override
                        public void onDeactivateButtonClick() {
                            Toast.makeText(getApplicationContext(), getString(R.string.deactivate_notification), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onStopBypassButtonClick() {
                            utils.bypassFloating();
                            utils.showFloating();
                        }

                        @Override
                        public void onShowFloatingButtonClick() {
                            utils.showFloating();
                        }

                        @Override
                        public void onDeactivateButtonLongPress() {
                            onDestroy();

                        }
                    });

                    setFloatingBackgroundListener(new FloatingBackgroundListener() {
                        @Override
                        public void backgroundActive() {
                            if (isFloatingActive) {
                                windowManager.removeView(floatView);
                                utils.startFloating();
                            } else {
                                utils.startFloating();
                            }

                            isFloatingActive = true;
                        }

                        @Override
                        public void backgroundNonActive() {
                            if (isFloatingActive) {
                                utils.hideFloating();
                                windowManager.removeView(floatView);
                                isFloatingActive = false;
                                BottomNavbarLayer2Item.deactivateKeyboard();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BottomNavbarLayer2Item.mlistener.onHideButtonClick();
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}
