package com.mzfreeapp.floatingbrowser.dashboard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.mzfreeapp.floatingbrowser.R;
import com.mzfreeapp.floatingbrowser.window.floatingbackground.FloatingBackground;
import com.mzfreeapp.floatingbrowser.window.floatingkeyboard.FloatingKeyboard;
import com.mzfreeapp.floatingbrowser.window.floatingwindow.FloatingWindow;


public class CheckService {
    Context context;
    Resources resources;
    Intent floating_browser, floating_keyboard, floating_background;

    /*Check service does anything that needed for services,
      such as checking if permission is active or not,
      requesting permission, and refreshing services.*/
    public CheckService(Context context, Resources resources) {
        this.context = context;
        this.resources = resources;

        floating_browser = new Intent(context, FloatingWindow.class);
        floating_keyboard = new Intent(context, FloatingKeyboard.class);
        floating_background = new Intent(context, FloatingBackground.class);
    }

    /*Checking whether overlay permission is given or not, if not the app will request that.*/
    public void checkOverlayPermission() {
        if (isPermissionGranted()) {
            Toast.makeText(context, resources.getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
        } else {
            requestOverlayDisplayPermission();
        }
    }

    /*Requesting overlay permission, .*/
    protected void requestOverlayDisplayPermission() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setCancelable(true);
        builder.setTitle(resources.getString(R.string.permission_need));
        builder.setMessage(resources.getString(R.string.permission_request));
        builder.setPositiveButton(resources.getString(R.string.open_settings), (dialog, which) -> {

            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse(resources.getString(R.string.uri_parse) +
                            context.getPackageName()));

            context.startActivity(intent);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean isPermissionGranted() {
        return Settings.canDrawOverlays(context);
    }

    private boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    // Check and stop services if they are active
    public void checkAndStopService(Context context) {
        stopServiceIfRunning(context, FloatingWindow.class);
        stopServiceIfRunning(context, FloatingKeyboard.class);
        stopServiceIfRunning(context, FloatingBackground.class);
    }

    // Stop service if it is running
    private void stopServiceIfRunning(Context context, Class<?> serviceClass) {
        if (isServiceRunning(context, serviceClass)) {
            context.stopService(new Intent(context, serviceClass));
        }
    }

    //Start all services
    public void startServices() {
        context.startService(floating_browser);
        context.startService(floating_keyboard);
        context.startService(floating_background);
    }
}

