package com.ibnucoding.iceloating.window.floatingwindow.modechanger;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.ibnucoding.iceloating.R;

public class ModeChangeHelper {
    Context context;
    public void setChangeMode(ViewGroup floatView, SharedPreferences sp, Context context) {
        this.context = context;
        ImageView floating_mode_web = floatView.findViewById(R.id.floating_mode_web);
        ImageView floating_mode_text = floatView.findViewById(R.id.floating_mode_text);
        WebView webView = floatView.findViewById(R.id.floating_webview);
        CustomEditText freezer_text = floatView.findViewById(R.id.freezer_text_view);
        freezer_text.setFocusable(true);
        freezer_text.setTextIsSelectable(true);

        freezer_text.setText(sp.getString("FREEZER_TEXT", ""));

        floating_mode_web.setOnClickListener(view -> {
            floating_mode_web.setVisibility(View.GONE);
            webView.setVisibility(View.GONE);
            floating_mode_text.setVisibility(View.VISIBLE);
            freezer_text.setVisibility(View.VISIBLE);
        });

        floating_mode_text.setOnClickListener(view -> {
            floating_mode_web.setVisibility(View.VISIBLE);
            webView.setVisibility(View.VISIBLE);
            floating_mode_text.setVisibility(View.GONE);
            freezer_text.setVisibility(View.GONE);
        });

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