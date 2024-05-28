package com.ibnucoding.iceloating.adjustwindow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.adjustwindow.layout.AdjustingLayoutLogic;
import com.ibnucoding.iceloating.adjustwindow.layout.CheckBoxLayout;
import com.ibnucoding.iceloating.adjustwindow.layout.LoadLayout;
import com.ibnucoding.iceloating.adjustwindow.layout.ResetLayout;
import com.ibnucoding.iceloating.adjustwindow.layout.SaveLayout;
import com.ibnucoding.iceloating.dashboard.listeners.ClickListener;

public class AdjustWindowFragment extends Fragment {

    boolean isDropdownOpen = true;
    View floating_window_container, floating_keyboard_container;
    ConstraintLayout showbutton_container;
    ImageView showbutton_button;
    float density;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adjust_window, container, false);

        density = getResources().getDisplayMetrics().density;
        showbutton_container = view.findViewById(R.id.showbutton_container);
        showbutton_button = showbutton_container.findViewById(R.id.showbutton_button);
        floating_window_container = view.findViewById(R.id.floating_window_container);
        floating_keyboard_container = view.findViewById(R.id.floating_keyboard_container);

        //All logics needed for handling adjust window fragment
        new LoadLayout(view, requireContext(), density);
        new CheckBoxLayout(view);
        new AdjustingLayoutLogic(view, density);

        setResetAndSaveButton(view);
        setExitAndDropDownButton(view);

        return view;
    }

    private void setExitAndDropDownButton(View view) {
        ConstraintLayout adjust_layout_title = view.findViewById(R.id.adjust_layout_title);
        ImageView dropdown_button = view.findViewById(R.id.dropdown_button);
        ImageView exit_button = adjust_layout_title.findViewById(R.id.exit_button);
        exit_button.setOnClickListener(v -> ClickListener.alistener.onBackButton());

        dropdown_button.setOnClickListener(v -> {
                    if (isDropdownOpen) {
                        dropdown_button.setImageResource(R.drawable.button_arrow_down);
                        adjust_layout_title.setVisibility(View.GONE);
                        isDropdownOpen = false;
                    } else {
                        dropdown_button.setImageResource(R.drawable.button_arrow_up);
                        adjust_layout_title.setVisibility(View.VISIBLE);
                        isDropdownOpen = true;
                    }
                }

        );

    }

    private void setResetAndSaveButton(View view) {
        ConstraintLayout adjust_layout_title = view.findViewById(R.id.adjust_layout_title);
        LinearLayout save_and_reset = adjust_layout_title.findViewById(R.id.save_and_reset);

        Button reset_button = save_and_reset.findViewById(R.id.reset_button);
        Button save_button = save_and_reset.findViewById(R.id.save_button);

        SharedPreferences sp = requireContext().getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        reset_button.setOnClickListener(v -> {
            new ResetLayout(view, density);
        });
        save_button.setOnClickListener(v -> {
            new SaveLayout(view, editor);
            Toast.makeText(getContext(),"Saved", Toast.LENGTH_SHORT).show();
        });
    }

}

