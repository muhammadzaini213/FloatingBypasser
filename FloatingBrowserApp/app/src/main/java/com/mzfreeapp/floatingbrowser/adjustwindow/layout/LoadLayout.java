package com.mzfreeapp.floatingbrowser.adjustwindow.layout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mzfreeapp.floatingbrowser.R;

public class LoadLayout {
    public LoadLayout(View view, Context context, float density) {
        SharedPreferences sp = context.getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);

        //load all saved layouts with SharedPreferences;
        loadFloatingWindow(view, density, sp);
        loadFloatingKeyboard(view, density, sp);
        loadShowButton(view, density, sp);
    }



    protected void loadShowButton(View view, float density, SharedPreferences sp) {
        ConstraintLayout showbutton_container = view.findViewById(R.id.showbutton_container);
        ImageView showbutton_button = showbutton_container.findViewById(R.id.showbutton_button);

        ViewGroup.LayoutParams layoutParams = showbutton_button.getLayoutParams();
        layoutParams.width = sp.getInt("SHOWBUTTON_WIDTH", (int) (60 * density));
        layoutParams.height = sp.getInt("SHOWBUTTON_HEIGHT", (int) (60 * density));
        showbutton_container.setX(sp.getInt("SHOWBUTTON_XPOS", 0));
        showbutton_container.setY(sp.getInt("SHOWBUTTON_YPOS", 0));
        showbutton_button.setLayoutParams(layoutParams);
    }

    protected void loadFloatingKeyboard(View view, float density, SharedPreferences sp) {
        View floating_keyboard_container = view.findViewById(R.id.floating_keyboard_container);

        ViewGroup.LayoutParams layoutParams = floating_keyboard_container.getLayoutParams();
        layoutParams.width = sp.getInt("FLOATING_KEYBOARD_WIDTH", (int) (400 * density));
        layoutParams.height = sp.getInt("FLOATING_KEYBOARD_HEIGHT", (int) (330 * density));
        floating_keyboard_container.setX(sp.getInt("FLOATING_KEYBOARD_XPOS", 0));
        floating_keyboard_container.setY(sp.getInt("FLOATING_KEYBOARD_YPOS", 0));
        floating_keyboard_container.setLayoutParams(layoutParams);
    }

    protected void loadFloatingWindow(View view, float density, SharedPreferences sp) {
        View floating_window_container = view.findViewById(R.id.floating_window_container);

        ViewGroup.LayoutParams layoutParams = floating_window_container.getLayoutParams();
        layoutParams.width = sp.getInt("FLOATING_WINDOW_WIDTH", (int) (300 * density));
        layoutParams.height = sp.getInt("FLOATING_WINDOW_HEIGHT", (int) (600 * density));
        floating_window_container.setX(sp.getInt("FLOATING_WINDOW_XPOS", 0));
        floating_window_container.setY(sp.getInt("FLOATING_WINDOW_YPOS", 0));
        floating_window_container.setLayoutParams(layoutParams);
    }
}
