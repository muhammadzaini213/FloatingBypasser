package com.mzfreeapp.floatingbrowser.adjustwindow.layout;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mzfreeapp.floatingbrowser.R;

public class CheckBoxLayout {


    public CheckBoxLayout(View view) {
        ConstraintLayout adjust_layout_title = view.findViewById(R.id.adjust_layout_title);
        LinearLayout showview_checkbox = adjust_layout_title.findViewById(R.id.showview_checkbox);

        ConstraintLayout showbutton_container = view.findViewById(R.id.showbutton_container);
        View floating_window_container = view.findViewById(R.id.floating_window_container);
        View floating_keyboard_container = view.findViewById(R.id.floating_keyboard_container);

        CheckBox browser_checkbox = showview_checkbox.findViewById(R.id.browser_checkbox);
        CheckBox keyboard_checkbox = showview_checkbox.findViewById(R.id.keyboard_checkbox);
        CheckBox showbutton_checkbox = showview_checkbox.findViewById(R.id.showbutton_checkbox);

        browser_checkbox.setChecked(true);
        keyboard_checkbox.setChecked(true);
        showbutton_checkbox.setChecked(true);


        //if not checked, hide the layout
        browser_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                floating_window_container.setVisibility(View.VISIBLE);
            } else {
                floating_window_container.setVisibility(View.GONE);
            }
        });

        keyboard_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                floating_keyboard_container.setVisibility(View.VISIBLE);
            } else {
                floating_keyboard_container.setVisibility(View.GONE);
            }
        });

        showbutton_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showbutton_container.setVisibility(View.VISIBLE);
            } else {
                showbutton_container.setVisibility(View.GONE);
            }
        });

    }
}
