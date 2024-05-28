package com.ibnucoding.iceloating.window.floatingkeyboard;

import static com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar.BottomNavbarLayer2Item.setKeyboardWindowListener;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.ibnucoding.iceloating.R;


public class FloatingKeyboard extends Service {
    ViewGroup keyboardView;
    WindowManager windowManager;
    boolean isKeyboardActive = false;

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();


        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        keyboardView = (ViewGroup) inflater.inflate(R.layout.window_floating_keyboard, null);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        SharedPreferences sp = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        FloatingKeyboardUtils utils = new FloatingKeyboardUtils();
        utils.init(keyboardView, windowManager, metrics, sp, getApplicationContext());

        /*
        Listening to bottom navbar layer 2 items
         */
        setKeyboardWindowListener(new FloatingKeyboardListener() {
            @Override
            public void onHideButtonClick() {
                if (isKeyboardActive) {
                    utils.hideFloating();
                    isKeyboardActive = false;
                }
            }

            @Override
            public void onKeyboardButtonClick() {
                if (isKeyboardActive) {
                    utils.hideFloating();
                    isKeyboardActive = false;
                } else {
                    utils.showFloating();
                    isKeyboardActive = true;
                }
            }

            @Override
            public void onDeactivateButtonLongPress() {
               onDestroy();
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
        if (isKeyboardActive) {
            windowManager.removeView(keyboardView);
            isKeyboardActive = false;
        }
        stopSelf();
    }
}
