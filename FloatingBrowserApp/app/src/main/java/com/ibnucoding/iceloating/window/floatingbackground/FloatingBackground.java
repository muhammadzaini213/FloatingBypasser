package com.ibnucoding.iceloating.window.floatingbackground;

import static com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item.setStopForegroundListener;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
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

public class FloatingBackground extends Service {
    ViewGroup backgroundView;
    WindowManager windowManager;
    NotificationHelper notificationHelper;
    int volumePrev = 0;
    boolean isBackgroundActive = false;
    private BroadcastReceiver broadcastReceiver;

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
            utils.startFloating();
            isBackgroundActive = true;


            View floating_background = backgroundView.findViewById(R.id.floating_background);
            floating_background.setOnClickListener(v -> {
                if (isBackgroundActive) {
                    windowManager.removeView(backgroundView);
                    isBackgroundActive = false;
                    utils.stopFloating();
                }
            });

            setBroadcastReceiver(utils);


        }

    }

    private void setBroadcastReceiver(FloatingBackgroundUtils utils) {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                /*
                This function is only activated if volume is changed
                 */
                if ("android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction())) {

                    int volume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);

                    if (!isBackgroundActive) {
                        utils.startFloating();
                        isBackgroundActive = true;
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
        if(isBackgroundActive) {
            windowManager.removeView(backgroundView);
            isBackgroundActive = false;
        }
        stopSelf();
    }






}
