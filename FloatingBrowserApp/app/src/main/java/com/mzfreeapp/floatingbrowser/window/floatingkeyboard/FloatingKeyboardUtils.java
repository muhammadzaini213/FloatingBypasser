package com.mzfreeapp.floatingbrowser.window.floatingkeyboard;

import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mzfreeapp.floatingbrowser.window.floatingkeyboard.helper.KeyboardItems;
import com.mzfreeapp.floatingbrowser.window.floatingkeyboard.helper.KeyboardShowOrHideHelper;

public class FloatingKeyboardUtils {

    KeyboardShowOrHideHelper showOrHideHelper;

    public void init(ViewGroup keyboardView, WindowManager windowManager, DisplayMetrics metrics, SharedPreferences sp) {
        showOrHideHelper = new KeyboardShowOrHideHelper(keyboardView, windowManager, metrics, sp);
        KeyboardItems items = new KeyboardItems();
        items.init(keyboardView);
    }


    public void showFloating() {
        showOrHideHelper.startFloating();
    }

    public void hideFloating() {
        showOrHideHelper.hideFloating();
    }

}
