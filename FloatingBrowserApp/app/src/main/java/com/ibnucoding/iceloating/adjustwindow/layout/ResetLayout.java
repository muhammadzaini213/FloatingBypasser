package com.ibnucoding.iceloating.adjustwindow.layout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ibnucoding.iceloating.R;

public class ResetLayout {

    public ResetLayout(View view, float density) {
        //resetting all layout to default state
        resetShowButton(view, density);
        resetFloatingWindow(view, density);
        resetFloatingKeyboard(view, density);
    }



    private void resetShowButton(View view, float density) {
        ConstraintLayout showbutton_container = view.findViewById(R.id.showbutton_container);
        ImageView showbutton_button = showbutton_container.findViewById(R.id.showbutton_button);

        int newWidth = (int) (60 * density);
        int newHeight = (int) (60 * density);
        ViewGroup.LayoutParams layoutParams = showbutton_button.getLayoutParams();
        layoutParams.width = newWidth;
        layoutParams.height = newHeight;
        showbutton_button.setLayoutParams(layoutParams);
        showbutton_container.setX(0);
        showbutton_container.setY(0);

    }

    private void resetFloatingWindow(View view, float density) {
        View floating_window_container = view.findViewById(R.id.floating_window_container);

        int newWidth = (int) (300 * density);
        int newHeight = (int) (600 * density);
        ViewGroup.LayoutParams layoutParams = floating_window_container.getLayoutParams();
        layoutParams.width = newWidth;
        layoutParams.height = newHeight;
        floating_window_container.setLayoutParams(layoutParams);
        floating_window_container.setX(0);
        floating_window_container.setY(0);
    }

    private void resetFloatingKeyboard(View view, float density) {
        View floating_keyboard_container = view.findViewById(R.id.floating_keyboard_container);

        int newWidth = (int) (400 * density);
        int newHeight = (int) (330 * density);
        ViewGroup.LayoutParams layoutParams = floating_keyboard_container.getLayoutParams();
        layoutParams.width = newWidth;
        layoutParams.height = newHeight;
        floating_keyboard_container.setLayoutParams(layoutParams);
        floating_keyboard_container.setX(0);
        floating_keyboard_container.setY(0);

    }
}
