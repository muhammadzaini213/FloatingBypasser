package com.ibnucoding.iceloating.window.floatingwindow.modechanger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ibnucoding.iceloating.R;

public class ModeChangeHelper {
    Context context;

    boolean isMenuOpened;

    public void setChangeMode(ViewGroup floatView, SharedPreferences sp, Context context) {
        this.context = context;
        isMenuOpened = false;
        ImageView floating_mode_web = floatView.findViewById(R.id.floating_mode_web);
        ImageView floating_mode_text = floatView.findViewById(R.id.floating_mode_text);
        WebView webView = floatView.findViewById(R.id.floating_webview);
        CustomEditText freezer_text = floatView.findViewById(R.id.freezer_text_view);

        LinearLayout top_navbar = floatView.findViewById(R.id.top_navbar);

        ImageView more_menu = floatView.findViewById(R.id.more_button);

        more_menu.setOnClickListener(view -> {
            if (!isMenuOpened) {
                openMenu(top_navbar);
            } else {
                closeMenu(top_navbar);
            }
        });

        freezer_text.setFocusable(true);
        freezer_text.setTextIsSelectable(true);

        freezer_text.setText(sp.getString("FREEZER_TEXT", ""));

        floating_mode_web.setOnClickListener(view -> {
            webView.setVisibility(View.VISIBLE);
            freezer_text.setVisibility(View.GONE);
        });

        floating_mode_text.setOnClickListener(view -> {
            webView.setVisibility(View.GONE);
            freezer_text.setVisibility(View.VISIBLE);
        });
        new DndMode(context, floatView);
    }




    ObjectAnimator rotateAnimator;
    ObjectAnimator translateYAnimator, scaleYAnimator;

    private void closeMenu(LinearLayout top_navbar) {
        isMenuOpened = false;

        top_navbar.setVisibility(View.VISIBLE);  // Make sure the navbar is visible before starting the animation

        translateYAnimator = ObjectAnimator.ofFloat(top_navbar, "translationY", 0, -top_navbar.getHeight());
        scaleYAnimator = ObjectAnimator.ofFloat(top_navbar, "scaleY", 1f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateYAnimator, scaleYAnimator);
        animatorSet.setDuration(500);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                top_navbar.setVisibility(View.GONE);  // Hide the navbar after the animation ends
            }
        });

        animatorSet.start();
    }

    private void openMenu(LinearLayout top_navbar) {
        isMenuOpened = true;

        top_navbar.setTranslationY(-top_navbar.getHeight());  // Move the navbar above the visible area
        top_navbar.setVisibility(View.VISIBLE);  // Make it visible before starting the animation

        translateYAnimator = ObjectAnimator.ofFloat(top_navbar, "translationY", -top_navbar.getHeight(), 0);
        scaleYAnimator = ObjectAnimator.ofFloat(top_navbar, "scaleY", 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateYAnimator, scaleYAnimator);
        animatorSet.setDuration(500);

        animatorSet.start();
    }

}