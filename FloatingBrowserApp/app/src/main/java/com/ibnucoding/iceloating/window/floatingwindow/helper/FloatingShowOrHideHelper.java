package com.ibnucoding.iceloating.window.floatingwindow.helper;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.dashboard.DashboardUtils;

public class FloatingShowOrHideHelper {
    ViewGroup floatView;
    WindowManager windowManager;
    DisplayMetrics metrics;
    WindowManager.LayoutParams floatWindowLayoutParam;
    int intwidth, intheight, density;

    int LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
    boolean UNFOCUS_ACTIVE = false;
    boolean HIDDEN_ACTIVE = false;
    SharedPreferences sp;

    public void init(ViewGroup floatView, WindowManager windowManager, DisplayMetrics metrics, SharedPreferences sp) {
        this.floatView = floatView;
        this.windowManager = windowManager;
        this.metrics = metrics;
        this.sp = sp;

        density = (int) metrics.density;
    }

    public void startFloating() {
        intwidth = sp.getInt("FLOATING_WINDOW_WIDTH", 300 * density);
        intheight = sp.getInt("FLOATING_WINDOW_HEIGHT", 600 * density);

        UNFOCUS_ACTIVE = DashboardUtils.getUnfocusBoolean();

        if (UNFOCUS_ACTIVE) {
            floatWindowLayoutParam = new WindowManager.LayoutParams(
                    intwidth
                    , intheight,
                    LAYOUT_TYPE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
        } else {
            floatWindowLayoutParam = new WindowManager.LayoutParams(
                    intwidth
                    , intheight,
                    LAYOUT_TYPE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT
            );
        }

        floatWindowLayoutParam.gravity = Gravity.START | Gravity.TOP;
        floatWindowLayoutParam.x = sp.getInt("FLOATING_WINDOW_XPOS", 0);
        floatWindowLayoutParam.y =  sp.getInt("FLOATING_WINDOW_YPOS", 0);

        windowManager.addView(floatView, floatWindowLayoutParam);

        floatView.setOnTouchListener(new View.OnTouchListener() {
            final WindowManager.LayoutParams floatWindowLayoutUpdateParam = floatWindowLayoutParam;
            double x;
            double y;
            double px;
            double py;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        x = floatWindowLayoutUpdateParam.x;
                        y = floatWindowLayoutUpdateParam.y;

                        px = event.getRawX();

                        py = event.getRawY();


                        break;

                    case MotionEvent.ACTION_MOVE:
                        floatWindowLayoutUpdateParam.x = (int) ((x + event.getRawX()) - px);
                        floatWindowLayoutUpdateParam.y = (int) ((y + event.getRawY()) - py);

                        windowManager.updateViewLayout(floatView, floatWindowLayoutUpdateParam);
                        break;

                    case MotionEvent.ACTION_UP:

                }
                return false;
            }


        });
    }

    public void showFloating() {
        floatWindowLayoutParam.gravity = Gravity.START | Gravity.TOP;

        floatWindowLayoutParam.x = sp.getInt("FLOATING_WINDOW_XPOS", 0);
        floatWindowLayoutParam.y =  sp.getInt("FLOATING_WINDOW_YPOS", 0);
        UNFOCUS_ACTIVE = DashboardUtils.getUnfocusBoolean();

        intwidth = sp.getInt("FLOATING_WINDOW_WIDTH", 300 * density);
        intheight = sp.getInt("FLOATING_WINDOW_HEIGHT", 600 * density);


        if (UNFOCUS_ACTIVE) {
            floatWindowLayoutParam = new WindowManager.LayoutParams(
                    intwidth
                    , intheight,
                    LAYOUT_TYPE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
        } else {
            floatWindowLayoutParam = new WindowManager.LayoutParams(
                    intwidth
                    , intheight,
                    LAYOUT_TYPE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSLUCENT
            );
        }

        floatView.setOnTouchListener(new View.OnTouchListener() {
            final WindowManager.LayoutParams floatWindowLayoutUpdateParam = floatWindowLayoutParam;
            double x;
            double y;
            double px;
            double py;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        x = floatWindowLayoutUpdateParam.x;
                        y = floatWindowLayoutUpdateParam.y;

                        px = event.getRawX();

                        py = event.getRawY();


                        break;

                    case MotionEvent.ACTION_MOVE:
                        floatWindowLayoutUpdateParam.x = (int) ((x + event.getRawX()) - px);
                        floatWindowLayoutUpdateParam.y = (int) ((y + event.getRawY()) - py);

                        windowManager.updateViewLayout(floatView, floatWindowLayoutUpdateParam);
                        break;

                    case MotionEvent.ACTION_UP:

                }
                return false;
            }


        });

        floatWindowLayoutParam.gravity = Gravity.START | Gravity.TOP;
        floatWindowLayoutParam.x = sp.getInt("FLOATING_WINDOW_XPOS", 0);
        floatWindowLayoutParam.y =  sp.getInt("FLOATING_WINDOW_YPOS", 0);

        windowManager.updateViewLayout(floatView, floatWindowLayoutParam);
        showLayout();

    }

    public void hideFloating() {
        intwidth = sp.getInt("SHOWBUTTON_WIDTH", 60 * density);
        intheight = sp.getInt("SHOWBUTTON_HEIGHT", 60 * density);

        floatView.setOnTouchListener(null);

        floatWindowLayoutParam = new WindowManager.LayoutParams(
                intwidth,
                intheight,
                LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        floatWindowLayoutParam.gravity = Gravity.START | Gravity.TOP;
        floatWindowLayoutParam.x = sp.getInt("SHOWBUTTON_XPOS", 0);
        floatWindowLayoutParam.y =  sp.getInt("SHOWBUTTON_YPOS", 0);
        windowManager.updateViewLayout(floatView, floatWindowLayoutParam);


        hideLayout();
    }

    private void hideLayout() {
        ConstraintLayout textFieldContainer = floatView.findViewById(R.id.text_field_container);
        WebView webView = floatView.findViewById(R.id.floating_webview);
        LinearLayout bottom_navigation_bar = floatView.findViewById(R.id.bottom_navigation_bar);
        View background = floatView.findViewById(R.id.background);
        ImageView stop_bypass = floatView.findViewById(R.id.stop_bypass);
        ImageView show_floating = floatView.findViewById(R.id.show_floating);
        RelativeLayout button_move = floatView.findViewById(R.id.button_move);

        HIDDEN_ACTIVE = DashboardUtils.getHiddenMode();

        if (HIDDEN_ACTIVE) {
            show_floating.setBackgroundColor(Color.TRANSPARENT);
        } else {
            show_floating.setBackgroundResource(R.drawable.button_show_floating);
        }


        button_move.setVisibility(View.GONE);
        background.setVisibility(View.GONE);
        textFieldContainer.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
        bottom_navigation_bar.setVisibility(View.GONE);
        stop_bypass.setVisibility(View.GONE);
        show_floating.setVisibility(View.VISIBLE);
    }

    private void showLayout() {
        ConstraintLayout textFieldContainer = floatView.findViewById(R.id.text_field_container);
        WebView webView = floatView.findViewById(R.id.floating_webview);
        LinearLayout bottom_navigation_bar = floatView.findViewById(R.id.bottom_navigation_bar);
        View background = floatView.findViewById(R.id.background);
        RelativeLayout button_move = floatView.findViewById(R.id.button_move);

        ImageView stop_bypass = floatView.findViewById(R.id.stop_bypass);
        ImageView show_floating = floatView.findViewById(R.id.show_floating);

        show_floating.setBackgroundColor(Color.TRANSPARENT);

        button_move.setVisibility(View.VISIBLE);
        background.setVisibility(View.VISIBLE);
        textFieldContainer.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
        bottom_navigation_bar.setVisibility(View.VISIBLE);
        stop_bypass.setVisibility(View.GONE);
        show_floating.setVisibility(View.GONE);
    }

}
