package com.mzfreeapp.floatingbrowser.window.floatingwindow.helper;

import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mzfreeapp.floatingbrowser.R;

public class FloatingBypassModeHelper {
    ViewGroup floatView;
    WindowManager windowManager;
    DisplayMetrics metrics;
    WindowManager.LayoutParams floatWindowLayoutParam;
    int density, intwidth, intheight;
    boolean isBypassMode = false;
    int LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
    SharedPreferences sp;

    public FloatingBypassModeHelper(ViewGroup floatView, WindowManager windowManager, DisplayMetrics metrics, SharedPreferences sp) {
        this.floatView = floatView;
        this.windowManager = windowManager;
        this.metrics = metrics;
        this.sp = sp;

        density = (int) metrics.density;
    }

    public void bypassFloating() {
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

        if (isBypassMode) {
            isBypassMode = false;
            showLayout();
        } else if (!isBypassMode) {
            isBypassMode = true;
        }


        hideLayout();
        updateLayout();
    }

    protected void updateLayout() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isBypassMode) {
                    windowManager.removeView(floatView);
                    windowManager.addView(floatView, floatWindowLayoutParam);
                    windowManager.updateViewLayout(floatView, floatWindowLayoutParam);
                    updateLayout();
                }

            }
        }, 3000);
    }

    protected void hideLayout() {
        ConstraintLayout textFieldContainer = floatView.findViewById(R.id.text_field_container);
        WebView webView = floatView.findViewById(R.id.floating_webview);
        LinearLayout bottom_navigation_bar = floatView.findViewById(R.id.bottom_navigation_bar);
        View background = floatView.findViewById(R.id.background);
        ImageView stop_bypass = floatView.findViewById(R.id.stop_bypass);
        ImageView show_floating = floatView.findViewById(R.id.show_floating);
        RelativeLayout button_move = floatView.findViewById(R.id.button_move);

        button_move.setVisibility(View.GONE);
        background.setVisibility(View.GONE);
        textFieldContainer.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
        bottom_navigation_bar.setVisibility(View.GONE);
        stop_bypass.setVisibility(View.VISIBLE);
        show_floating.setVisibility(View.GONE);
    }

    protected void showLayout() {
        ConstraintLayout textFieldContainer = floatView.findViewById(R.id.text_field_container);
        WebView webView = floatView.findViewById(R.id.floating_webview);
        LinearLayout bottom_navigation_bar = floatView.findViewById(R.id.bottom_navigation_bar);
        View background = floatView.findViewById(R.id.background);
        ImageView stop_bypass = floatView.findViewById(R.id.stop_bypass);
        ImageView show_floating = floatView.findViewById(R.id.show_floating);
        RelativeLayout button_move = floatView.findViewById(R.id.button_move);

        button_move.setVisibility(View.VISIBLE);
        background.setVisibility(View.VISIBLE);
        textFieldContainer.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
        bottom_navigation_bar.setVisibility(View.VISIBLE);
        stop_bypass.setVisibility(View.GONE);
        show_floating.setVisibility(View.GONE);
    }

}
