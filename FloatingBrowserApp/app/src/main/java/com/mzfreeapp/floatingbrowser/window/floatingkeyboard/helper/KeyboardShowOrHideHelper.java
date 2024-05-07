package com.mzfreeapp.floatingbrowser.window.floatingkeyboard.helper;

import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class KeyboardShowOrHideHelper {
    private final ViewGroup keyboardView;
    private final WindowManager windowManager;

    DisplayMetrics metrics;
    private WindowManager.LayoutParams floatKeyboardLayoutParam;
    private int intwidth;
    private int intheight;
    private int density;
    SharedPreferences sp;

    public KeyboardShowOrHideHelper(ViewGroup keyboardView, WindowManager windowManager, DisplayMetrics metrics, SharedPreferences sp) {
        this.keyboardView = keyboardView;
        this.windowManager = windowManager;
        this.metrics = metrics;
        this.sp = sp;

        density = (int) metrics.density;
    }

    public void startFloating() {
        int LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        intwidth = sp.getInt("FLOATING_KEYBOARD_WIDTH", 400 * density);
        intheight = sp.getInt("FLOATING_KEYBOARD_HEIGHT", 330 * density);

        floatKeyboardLayoutParam = new WindowManager.LayoutParams(
                intwidth,
                intheight,
                LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        floatKeyboardLayoutParam.gravity = Gravity.START| Gravity.TOP;
        floatKeyboardLayoutParam.x = sp.getInt("FLOATING_KEYBOARD_XPOS", 0);
        floatKeyboardLayoutParam.y = sp.getInt("FLOATING_KEYBOARD_YPOS", 0);


        keyboardView.setOnTouchListener(new View.OnTouchListener() {
            final WindowManager.LayoutParams floatKeyboardLayoutUpdateParam = floatKeyboardLayoutParam;
            double x;
            double y;
            double px;
            double py;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = floatKeyboardLayoutUpdateParam.x;
                        y = floatKeyboardLayoutUpdateParam.y;
                        px = event.getRawX();
                        py = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        floatKeyboardLayoutUpdateParam.x = (int) ((x + event.getRawX()) - px);
                        floatKeyboardLayoutUpdateParam.y = (int) ((y + event.getRawY()) - py);
                        windowManager.updateViewLayout(keyboardView, floatKeyboardLayoutUpdateParam);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        windowManager.addView(keyboardView, floatKeyboardLayoutParam);
    }

    public void hideFloating() {
        int LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        intwidth = 0;
        intheight = 0;


        floatKeyboardLayoutParam = new WindowManager.LayoutParams(
                intwidth,
                intheight,
                LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );


        keyboardView.setOnTouchListener(new View.OnTouchListener() {
            final WindowManager.LayoutParams floatKeyboardLayoutUpdateParam = floatKeyboardLayoutParam;
            double x;
            double y;
            double px;
            double py;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = floatKeyboardLayoutUpdateParam.x;
                        y = floatKeyboardLayoutUpdateParam.y;
                        px = event.getRawX();
                        py = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        floatKeyboardLayoutUpdateParam.x = (int) ((x + event.getRawX()) - px);
                        floatKeyboardLayoutUpdateParam.y = (int) ((y + event.getRawY()) - py);
                        windowManager.updateViewLayout(keyboardView, floatKeyboardLayoutUpdateParam);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        windowManager.removeView(keyboardView);
    }

}