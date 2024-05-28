package com.ibnucoding.iceloating.window.floatingbackground.floatingbackgroundhelper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.ibnucoding.iceloating.HomeActivity;
import com.ibnucoding.iceloating.R;

public class NotificationHelper {

    private static final int NOTIFICATION_ID = 2113;
    private static final String CHANNEL_ID = "Iceloating Service Channel";

    Context context;
    Service service;

    public NotificationHelper(Context context, Service service) {
        this.context = context;
        this.service = service;
    }

    private void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Iceloating Service Channel",
                NotificationManager.IMPORTANCE_HIGH // Ensure high importance for visibility
        );
        serviceChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); // Ensure visibility on lockscreen
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
    }

    private Notification buildNotification() {
        Intent notificationIntent = new Intent(context, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Update flag usage
        );

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Floating Browser")
                .setContentText("Running")
                .setSmallIcon(R.drawable.app_icon_foreground)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Ensure high priority
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();
    }

    public void startForegroundService() {
        createNotificationChannel();
        Notification notification = buildNotification();
        service.startForeground(NOTIFICATION_ID, notification);
    }

    public void requestDndPermission() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (!notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Start as new task
            context.startActivity(intent);
        } else {
            allowNotificationsDuringDnd(notificationManager);
        }
    }

    private void allowNotificationsDuringDnd(NotificationManager notificationManager) {
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
    }
}
