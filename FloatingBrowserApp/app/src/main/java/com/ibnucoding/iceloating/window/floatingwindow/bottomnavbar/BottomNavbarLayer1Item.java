package com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.dashboard.DashboardUtils;

public class BottomNavbarLayer1Item {

    ObjectAnimator rotateAnimator;
    ObjectAnimator translateYAnimator, scaleYAnimator;
    boolean isDrawerOpen;
    Context context;

    protected void layer1ClickListener(LinearLayout layer1, LinearLayout layer2, WebView webView, Context context, ViewGroup floatView) {
        ImageView back_button = layer1.findViewById(R.id.back_button);
        ImageView forward_button = layer1.findViewById(R.id.forward_button);
        ImageView home_button = layer1.findViewById(R.id.home_button);
        ImageView refresh_button = layer1.findViewById(R.id.refresh_button);
        ImageView menu_button = layer1.findViewById(R.id.menu_button);
        this.context = context;

        back_button.setOnClickListener(v -> {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        });

        forward_button.setOnClickListener(v -> {
            if (webView.canGoForward()) {
                webView.goForward();
            }
        });

        home_button.setOnClickListener(v -> {
            String HOME_URL = DashboardUtils.getHome_URL();
            if (HOME_URL.startsWith("http") || HOME_URL.endsWith("com")) {
                webView.loadUrl(HOME_URL);
            } else {
                webView.loadUrl("https://google.com/search?q=" + HOME_URL);
            }
        });


        if (DashboardUtils.getBOTTLE_OPENER() && !DashboardUtils.getUnfocusBoolean()) {
            home_button.setOnLongClickListener(view -> {
                setBottleOpener(floatView);
                return false;
            });
        }


        refresh_button.setOnClickListener(v -> {
            if (webView.getUrl() != null) {
                webView.loadUrl(webView.getUrl());
            }
        });

        menu_button.setOnClickListener(v -> {
            if (!isDrawerOpen) {
                openMenu(menu_button, layer2);
            } else {
                closeMenu(menu_button, layer2);
            }
        });


    }

    private void setBottleOpener(ViewGroup floatView) {

        floatView.findViewById(R.id.bottle_opener_layout).setVisibility(View.VISIBLE);


        floatView.findViewById(R.id.exit_button).setOnClickListener(view -> {
            floatView.findViewById(R.id.bottle_opener_layout).setVisibility(View.GONE);
        });

        floatView.findViewById(R.id.open_bottle).setOnClickListener(view -> {
            EditText bottle_input = floatView.findViewById(R.id.bottle_opener_input);

            if(bottle_input.getText().toString().isEmpty()){
                Toast.makeText(context, "Input is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(bottle_input.getText().toString());
            if (launchIntent != null) {
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launchIntent);
            } else {
                // Handle the case where the target app is not installed
                Toast.makeText(context, "Target app is not installed", Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void openMenu(ImageView menu_button, LinearLayout layer2) {
        isDrawerOpen = true;
        rotateAnimator = ObjectAnimator.ofFloat(menu_button, "rotation", 0f, 90f);
        rotateAnimator.setDuration(500);

        layer2.setTranslationY(-layer2.getHeight());
        layer2.setVisibility(View.VISIBLE);

        translateYAnimator = ObjectAnimator.ofFloat(layer2, "translationY", -layer2.getHeight(), 0);
        scaleYAnimator = ObjectAnimator.ofFloat(layer2, "scaleY", 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateYAnimator, scaleYAnimator);
        animatorSet.setDuration(500);

        rotateAnimator.start();
        animatorSet.start();
    }

    private void closeMenu(ImageView menu_button, LinearLayout layer2) {
        isDrawerOpen = false;
        rotateAnimator = ObjectAnimator.ofFloat(menu_button, "rotation", 90f, 0f);
        rotateAnimator.setDuration(500);

        layer2.setTranslationY(-layer2.getHeight());
        layer2.setVisibility(View.VISIBLE);

        translateYAnimator = ObjectAnimator.ofFloat(layer2, "translationY", 0, -layer2.getHeight());
        scaleYAnimator = ObjectAnimator.ofFloat(layer2, "scaleY", 1f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateYAnimator, scaleYAnimator);
        animatorSet.setDuration(500);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                layer2.setVisibility(View.GONE);
            }
        });
        rotateAnimator.start();
        animatorSet.start();
    }
}
