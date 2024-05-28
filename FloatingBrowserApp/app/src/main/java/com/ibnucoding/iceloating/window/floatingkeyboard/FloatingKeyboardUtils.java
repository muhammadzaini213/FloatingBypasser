package com.ibnucoding.iceloating.window.floatingkeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ibnucoding.iceloating.window.floatingkeyboard.helper.KeyboardItems;
import com.ibnucoding.iceloating.window.floatingkeyboard.helper.KeyboardShowOrHideHelper;

public class FloatingKeyboardUtils {

    KeyboardShowOrHideHelper showOrHideHelper;

    public void init(ViewGroup keyboardView, WindowManager windowManager, DisplayMetrics metrics, SharedPreferences sp, Context context) {
        showOrHideHelper = new KeyboardShowOrHideHelper(keyboardView, windowManager, metrics, sp);
        KeyboardItems items = new KeyboardItems();
        items.init(keyboardView, context);
    }


    public void showFloating() {
        showOrHideHelper.startFloating();
    }

    public void hideFloating() {
        showOrHideHelper.hideFloating();
    }

}
