package com.ibnucoding.iceloating.window.floatingwindow.modechanger;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ibnucoding.iceloating.R;

public class DndMode {
    Context context;
    public DndMode(Context context, ViewGroup floatView){
        this.context = context;

        ImageView media_mute = floatView.findViewById(R.id.media_mode_mute);
        ImageView media_active = floatView.findViewById(R.id.media_mode_active);

        media_active.setOnClickListener(view -> {
            enableDNDMode();
            media_active.setVisibility(View.GONE);
            media_mute.setVisibility(View.VISIBLE);

        });
        media_mute.setOnClickListener(view -> {
            disableDNDMode();
            media_active.setVisibility(View.VISIBLE);
            media_mute.setVisibility(View.GONE);
        });



    }



    private void enableDNDMode() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager.isNotificationPolicyAccessGranted()) {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
        } else {
            requestDNDPermission();
        }
    }


    private void disableDNDMode() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager.isNotificationPolicyAccessGranted()) {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
        } else {
            requestDNDPermission();
        }
    }

    private void requestDNDPermission() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (!notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            context.startActivity(intent);
            Toast.makeText(context, "Tolong aktifkan izin 'Jangan ganggu'.", Toast.LENGTH_SHORT).show();
        }
    }
}
