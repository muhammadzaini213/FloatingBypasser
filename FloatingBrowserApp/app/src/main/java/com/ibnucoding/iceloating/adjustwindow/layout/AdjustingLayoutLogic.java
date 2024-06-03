package com.ibnucoding.iceloating.adjustwindow.layout;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ibnucoding.iceloating.R;

public class AdjustingLayoutLogic {
    View view;
    float density;

    public AdjustingLayoutLogic(View view, float density) {
        this.view = view;
        this.density = density;

        //set all adjusting layout logic, such as move, minimize, and zooming
        adjustFloatingKeyboard();
        adjustFloatingWindow();
        adjustShowButton();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void adjustFloatingKeyboard() {
        View floating_keyboard_container = view.findViewById(R.id.floating_keyboard_container);

        LinearLayout floating_keyboard_option = floating_keyboard_container.findViewById(R.id.floating_keyboard_option);

        ImageView keyboard_minimize = floating_keyboard_option.findViewById(R.id.floating_keyboard_minimize);
        ImageView keyboard_move = floating_keyboard_option.findViewById(R.id.floating_keyboard_move);
        ImageView keyboard_zoom = floating_keyboard_option.findViewById(R.id.floating_keyboard_zoom);

        keyboard_minimize.setOnClickListener(v -> {
            int newWidth = (int) (floating_keyboard_container.getWidth() - 4 * density);
            int newHeight = (int) (floating_keyboard_container.getHeight() - 3 * density);
            ViewGroup.LayoutParams layoutParams = floating_keyboard_container.getLayoutParams();
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            floating_keyboard_container.setLayoutParams(layoutParams);
        });


        keyboard_move.setOnTouchListener(new View.OnTouchListener() {
            private float dx, dy;
            private int lastAction;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dx = floating_keyboard_container.getX() - event.getRawX();
                        dy = floating_keyboard_container.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = event.getRawX() + dx;
                        float newY = event.getRawY() + dy;
                        floating_keyboard_container.setX(newX);
                        floating_keyboard_container.setY(newY);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        keyboard_zoom.setOnClickListener(v -> {
            int newWidth = (int) (floating_keyboard_container.getWidth() + 4 * density);
            int newHeight = (int) (floating_keyboard_container.getHeight() + 3 * density);
            ViewGroup.LayoutParams layoutParams = floating_keyboard_container.getLayoutParams();
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            floating_keyboard_container.setLayoutParams(layoutParams);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void adjustFloatingWindow() {
        View floating_window_container = view.findViewById(R.id.floating_window_container);

        LinearLayout floating_window_option = floating_window_container.findViewById(R.id.floating_window_option);

        ImageView window_minimize = floating_window_option.findViewById(R.id.floating_window_minimize);
        ImageView window_move = floating_window_option.findViewById(R.id.floating_window_move);
        ImageView window_zoom = floating_window_option.findViewById(R.id.floating_window_zoom);

        window_minimize.setOnClickListener(v -> {
            int newWidth = (int) (floating_window_container.getWidth() - 5 * density);
            int newHeight = (int) (floating_window_container.getHeight() - 10 * density);
            ViewGroup.LayoutParams layoutParams = floating_window_container.getLayoutParams();
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            floating_window_container.setLayoutParams(layoutParams);
        });


        window_move.setOnTouchListener(new View.OnTouchListener() {
            private float dx, dy;
            private int lastAction;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dx = floating_window_container.getX() - event.getRawX();
                        dy = floating_window_container.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = event.getRawX() + dx;
                        float newY = event.getRawY() + dy;
                        floating_window_container.setX(newX);
                        floating_window_container.setY(newY);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        window_zoom.setOnClickListener(v -> {
            int newWidth = (int) (floating_window_container.getWidth() + 5 * density);
            int newHeight = (int) (floating_window_container.getHeight() + 10 * density);
            ViewGroup.LayoutParams layoutParams = floating_window_container.getLayoutParams();
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            floating_window_container.setLayoutParams(layoutParams);
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    private void adjustShowButton() {
        ConstraintLayout showbutton_container = view.findViewById(R.id.showbutton_container);

        ImageView showbutton_button = showbutton_container.findViewById(R.id.showbutton_button);
        LinearLayout showbutton_option = showbutton_container.findViewById(R.id.showbutton_option);

        ImageView showbutton_minimize = showbutton_option.findViewById(R.id.showbutton_minimize);
        ImageView showbutton_move = showbutton_option.findViewById(R.id.showbutton_move);
        ImageView showbutton_zoom = showbutton_option.findViewById(R.id.showbutton_zoom);


        showbutton_minimize.setOnClickListener(v -> {
            int newWidth = (int) (showbutton_button.getWidth() - 5 * density);
            int newHeight = (int) (showbutton_button.getHeight() - 5 * density);
            ViewGroup.LayoutParams layoutParams = showbutton_button.getLayoutParams();
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            showbutton_button.setLayoutParams(layoutParams);
        });


        showbutton_move.setOnTouchListener(new View.OnTouchListener() {
            private float dx, dy;
            private int lastAction;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dx = showbutton_container.getX() - event.getRawX();
                        dy = showbutton_container.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = event.getRawX() + dx;
                        float newY = event.getRawY() + dy;

                        showbutton_container.setX(newX);
                        showbutton_container.setY(newY);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        showbutton_zoom.setOnClickListener(v -> {
            int newWidth = (int) (showbutton_button.getWidth() + 5 * density);
            int newHeight = (int) (showbutton_button.getHeight() + 5 * density);
            ViewGroup.LayoutParams layoutParams = showbutton_button.getLayoutParams();
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            showbutton_button.setLayoutParams(layoutParams);
        });
    }

}

