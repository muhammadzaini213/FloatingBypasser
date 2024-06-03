package com.ibnucoding.iceloating.window.floatingbackground;

import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;


public class FloatingBackgroundUtils {
    private static FloatingBackgroundListener blistener;
    ViewGroup backgroundView;
    WindowManager windowManager;
    DisplayMetrics metrics;
    int width, height, intwidth, intheight;
    int LAYOUT_TYPE;
    WindowManager.LayoutParams floatWindowLayoutParam;

    public static void setFloatingBackgroundListener(FloatingBackgroundListener listener) {
        blistener = listener;
    }


    public void init(ViewGroup backgroundView, WindowManager windowManager, DisplayMetrics metrics) {
        this.backgroundView = backgroundView;
        this.windowManager = windowManager;
        this.metrics = metrics;
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

    }

    public void startFloating() {
        intwidth = (int) (width * (1.00f));
        intheight = (int) (height * (1.00f));

        floatWindowLayoutParam = new WindowManager.LayoutParams(
                intwidth
                , intheight,
                LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        floatWindowLayoutParam.gravity = Gravity.START | Gravity.TOP;
        floatWindowLayoutParam.x = 0;
        floatWindowLayoutParam.y = 0;

        windowManager.addView(backgroundView, floatWindowLayoutParam);
        blistener.backgroundActive();
    }

    public void stopFloating() {
//        BottomNavbarLayer2Item.mlistener.onHideButtonClick();
        blistener.backgroundNonActive();
    }

}
