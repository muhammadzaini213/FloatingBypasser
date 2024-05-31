package com.ibnucoding.iceloating.adjustwindow.layout;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ibnucoding.iceloating.R;

public class SaveLayout {

    public SaveLayout(View view, SharedPreferences.Editor editor) {
        //saving all layout position and size
        saveShowButton(view, editor);
        saveFloatingKeyboard(view, editor);
        saveFloatingWindow(view, editor);
        saveDoubleSafety(view, editor);
    }

    private void saveDoubleSafety(View view, SharedPreferences.Editor editor) {
        ConstraintLayout double_safety_container = view.findViewById(R.id.double_safety_container);
        ImageView double_safety_button = double_safety_container.findViewById(R.id.double_safety_button);

        editor.putInt("SAFETY_WIDTH", (int) double_safety_button.getWidth());
        editor.putInt("SAFETY_HEIGHT", (int) double_safety_button.getHeight());
        editor.putInt("SAFETY_XPOS", (int) double_safety_container.getX());
        editor.putInt("SAFETY_YPOS", (int) double_safety_container.getY());
        editor.apply();

    }
    private void saveShowButton(View view, SharedPreferences.Editor editor) {
        ConstraintLayout showbutton_container = view.findViewById(R.id.showbutton_container);
        ImageView showbutton_button = showbutton_container.findViewById(R.id.showbutton_button);

        editor.putInt("SHOWBUTTON_WIDTH", (int) showbutton_button.getWidth());
        editor.putInt("SHOWBUTTON_HEIGHT", (int) showbutton_button.getHeight());
        editor.putInt("SHOWBUTTON_XPOS", (int) showbutton_container.getX());
        editor.putInt("SHOWBUTTON_YPOS", (int) showbutton_container.getY());
        editor.apply();

    }

    private void saveFloatingKeyboard(View view, SharedPreferences.Editor editor) {
        View floating_keyboard_container = view.findViewById(R.id.floating_keyboard_container);

        editor.putInt("FLOATING_KEYBOARD_WIDTH", (int) floating_keyboard_container.getWidth());
        editor.putInt("FLOATING_KEYBOARD_HEIGHT", (int) floating_keyboard_container.getHeight());
        editor.putInt("FLOATING_KEYBOARD_XPOS", (int) floating_keyboard_container.getX());
        editor.putInt("FLOATING_KEYBOARD_YPOS", (int) floating_keyboard_container.getY());
        editor.apply();
    }

    private void saveFloatingWindow(View view, SharedPreferences.Editor editor) {
        View floating_window_container = view.findViewById(R.id.floating_window_container);

        editor.putInt("FLOATING_WINDOW_WIDTH", (int) floating_window_container.getWidth());
        editor.putInt("FLOATING_WINDOW_HEIGHT", (int) floating_window_container.getHeight());
        editor.putInt("FLOATING_WINDOW_XPOS", (int) floating_window_container.getX());
        editor.putInt("FLOATING_WINDOW_YPOS", (int) floating_window_container.getY());
        editor.apply();
    }
}
