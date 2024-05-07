package com.mzfreeapp.floatingbrowser.window.floatingwindow;

import static com.mzfreeapp.floatingbrowser.window.floatingbackground.FloatingBackgroundUtils.setFloatingBackgroundListener;
import static com.mzfreeapp.floatingbrowser.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item.setFloatingWindowListener;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mzfreeapp.floatingbrowser.R;
import com.mzfreeapp.floatingbrowser.window.floatingbackground.FloatingBackgroundListener;
import com.mzfreeapp.floatingbrowser.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item;


public class FloatingWindow extends Service {

    boolean isFloatingActive;
    private ViewGroup floatView;
    private WindowManager windowManager;

    @Override
    public void onCreate() {
        super.onCreate();

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        floatView = (ViewGroup) inflater.inflate(R.layout.window_floating_window, null);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);


        SharedPreferences sp = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);

        FloatingWindowUtils utils = new FloatingWindowUtils(sp);
        utils.init(floatView, windowManager, metrics);

        isFloatingActive = true;

        /*
        Handle logic from bottom navbar layer2 items, which had more complicated uses.
         */
        setFloatingWindowListener(new FloatingWindowListener() {
            @Override
            public void onHideButtonClick() {
                utils.hideFloating();
            }

            @Override
            public void onKeyboarButtonClick() {

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

        /*
        This listener only active if AntiObscure is activated
         */
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


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isFloatingActive) {
            windowManager.removeView(floatView);
            isFloatingActive = false;
        }
        stopSelf();
    }
}