package com.mzfreeapp.floatingbrowser.window.floatingbackground.floatingbackgroundhelper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.mzfreeapp.floatingbrowser.HomeActivity;
import com.mzfreeapp.floatingbrowser.R;

public class NotificationHelper {

    private static final int NOTIFICATION_ID = 2113;
    private static final String CHANNEL_ID = "ForegroundServiceChannel";

    Context context;
    Service service;

    /*
    Used to create notification for floating background to make
    the service working on the background properly.
     */
    public NotificationHelper(Context context, Service service) {
        this.context = context;
        this.service = service;
    }

    private void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
    }

    private Notification buildNotification() {
        Intent notificationIntent = new Intent(context, HomeActivity.class); // Replace YourActivity with your actual activity
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
        );

        return new NotificationCompat.Builder(service, CHANNEL_ID)
                .setContentTitle("Floating Browser")
                .setContentText("Running")
                .setSmallIcon(R.drawable.app_icon_foreground)
                .setContentIntent(pendingIntent)
                .build();
    }

    public void startForegroundService() {
        createNotificationChannel();
        Notification notification = buildNotification();
        service.startForeground(NOTIFICATION_ID, notification);
    }
}
