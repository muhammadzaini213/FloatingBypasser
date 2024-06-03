package com.ibnucoding.iceloating.window.floatingbackground;

import static com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item.setStopForegroundListener;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.dashboard.DashboardUtils;
import com.ibnucoding.iceloating.window.floatingbackground.floatingbackgroundhelper.NotificationHelper;
import com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item;

public class FloatingBackground extends Service implements SensorEventListener {
    ViewGroup backgroundView;
    WindowManager windowManager;
    NotificationHelper notificationHelper;
    int volumePrev = 0;
    boolean isBackgroundActive = false;
    private BroadcastReceiver broadcastReceiver;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static final int SHAKE_THRESHOLD = 800;
    private long lastUpdate;
    private float last_x, last_y, last_z;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.blank);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    FloatingBackgroundUtils utils;

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        backgroundView = (ViewGroup) inflater.inflate(R.layout.window_floating_background, null);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        utils = new FloatingBackgroundUtils();
        utils.init(backgroundView, windowManager, metrics);

        notificationHelper = new NotificationHelper(this, this);
        notificationHelper.requestDndPermission();
        notificationHelper.startForegroundService();


        setStopForegroundListener(new StopForegroundListener() {
            @Override
            public void stopForeground() {
                onDestroy();
            }
        });

        /*
        Only start the floating if the the ANTI_OBSCURE is activated.
         */
        if (DashboardUtils.getANTI_OBSCURE()) {

            if (!DashboardUtils.getUSE_VIBRATION()) {
                utils.startFloating();
                isBackgroundActive = true;
            }

            View floating_background = backgroundView.findViewById(R.id.floating_background);
            if (DashboardUtils.getHiddenMode()) {
                floating_background.setBackgroundColor(Color.TRANSPARENT);
                BottomNavbarLayer2Item.mlistener.onHideButtonClick();
            }
            floating_background.setOnClickListener(v -> {
                if (isBackgroundActive) {
                    windowManager.removeView(backgroundView);
                    isBackgroundActive = false;
                    utils.stopFloating();
                }
            });

            if (DashboardUtils.getUSE_VIBRATION()) {
                sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                if (sensorManager != null) {
                    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                }
                lastUpdate = System.currentTimeMillis();
                if (accelerometer != null) {
                    sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                }

                Toast.makeText(getApplicationContext(), "Sensor getar diaktifkan, goyangkan perangkat anda saat ingin memunculkan Iceloating", Toast.LENGTH_LONG).show();
            } else {
                setBroadcastReceiver(utils);
            }


        }

    }


    private void setBroadcastReceiver(FloatingBackgroundUtils utils) {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                /*
                This function is only activated if volume is changed
                 */

                if ("android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction())) {

                    int volume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);

                    if (!isBackgroundActive) {
                        utils.startFloating();
                        isBackgroundActive = true;

                        BottomNavbarLayer2Item.mlistener.onHideButtonClick();

                    }
                    volumePrev = volume;


                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(broadcastReceiver, filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBackgroundActive) {
            windowManager.removeView(backgroundView);
            isBackgroundActive = false;
        }

        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
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
//        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        if (v != null && v.hasVibrator()) {
//            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
//        }
        if (DashboardUtils.getANTI_OBSCURE() && DashboardUtils.getUSE_VIBRATION()) {
            if (!isBackgroundActive) {
                utils.startFloating();
                isBackgroundActive = true;

                BottomNavbarLayer2Item.mlistener.onHideButtonClick();

            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}
