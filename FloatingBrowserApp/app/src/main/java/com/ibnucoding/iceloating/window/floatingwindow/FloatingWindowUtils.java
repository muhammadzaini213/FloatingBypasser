package com.ibnucoding.iceloating.window.floatingwindow;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ibnucoding.iceloating.dashboard.DashboardUtils;
import com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar.BottomNavbarItems;
import com.ibnucoding.iceloating.window.floatingwindow.helper.FloatingBypassModeHelper;
import com.ibnucoding.iceloating.window.floatingwindow.helper.FloatingShowOrHideHelper;
import com.ibnucoding.iceloating.window.floatingwindow.helper.FloatingShowOrHideHelperSafety;
import com.ibnucoding.iceloating.window.floatingwindow.modechanger.ModeChangeHelper;
import com.ibnucoding.iceloating.window.webview.WebviewHelper;

public class FloatingWindowUtils {
    ViewGroup floatView;
    WindowManager windowManager;
    FloatingShowOrHideHelper showOrHideHelper;
    FloatingShowOrHideHelperSafety showOrHideHelperSafety;
    FloatingBypassModeHelper bypassModeHelper;
    SharedPreferences sp;

    protected FloatingWindowUtils(SharedPreferences sp) {
        this.sp = sp;
    }


    protected void init(ViewGroup floatView, ViewGroup safetyView, WindowManager windowManager, DisplayMetrics metrics, Context context) {
        this.floatView = floatView;
        this.windowManager = windowManager;

        BottomNavbarItems navbarItems = new BottomNavbarItems();
        WebviewHelper webviewHelper = new WebviewHelper();
        ModeChangeHelper changeHelper = new ModeChangeHelper();

        if (DashboardUtils.getDOUBLE_SAFETY() && DashboardUtils.getANTI_OBSCURE()) {
            DashboardUtils.setDOUBLE_SAFETY(false);
        }
        if (DashboardUtils.getDOUBLE_SAFETY()) {
            showOrHideHelperSafety = new FloatingShowOrHideHelperSafety();
            showOrHideHelperSafety.init(floatView, safetyView, windowManager, metrics, sp);
        } else {
            showOrHideHelper = new FloatingShowOrHideHelper();
            showOrHideHelper.init(floatView, windowManager, metrics, sp);
        }

        bypassModeHelper = new FloatingBypassModeHelper(floatView, windowManager, metrics, sp);

        navbarItems.setOnClick(floatView, context);
        webviewHelper.setWebView(floatView);
        changeHelper.setChangeMode(floatView, sp, context);

        startFloating();


    }

    public void startFloating() {
        if (DashboardUtils.getDOUBLE_SAFETY()) {
            showOrHideHelperSafety.startFloating();
        } else {
            showOrHideHelper.startFloating();
        }
    }

    protected void bypassFloating() {
        bypassModeHelper.bypassFloating();
    }

    protected void hideFloating() {
        if (DashboardUtils.getDOUBLE_SAFETY()) {
            showOrHideHelperSafety.hideFloating();
        } else {
            showOrHideHelper.hideFloating();
        }
    }

    protected void showFloating() {
        if (DashboardUtils.getDOUBLE_SAFETY()) {
            showOrHideHelperSafety.showFloating();
        } else {
            showOrHideHelper.showFloating();
        }
    }


}
