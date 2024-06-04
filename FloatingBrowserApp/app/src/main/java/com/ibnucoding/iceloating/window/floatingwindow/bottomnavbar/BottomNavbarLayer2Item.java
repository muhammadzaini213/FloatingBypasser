package com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.dashboard.DashboardUtils;
import com.ibnucoding.iceloating.window.floatingbackground.StopForegroundListener;
import com.ibnucoding.iceloating.window.floatingkeyboard.FloatingKeyboardListener;
import com.ibnucoding.iceloating.window.floatingwindow.FloatingWindowListener;
import com.ibnucoding.iceloating.window.floatingwindow.helper.FloatingShowOrHideHelperSafety;

import java.util.Objects;

public class BottomNavbarLayer2Item {

    public static FloatingWindowListener mlistener;
    private static FloatingKeyboardListener klistener;
    private static StopForegroundListener slistener;
    int counter;
    private boolean isDekstopMode = false;
    private boolean isKeyboardActive = false;

    /*
    Layer2 items contains listener for communicating with other services
     */
    private boolean isSafetyActive;
    private boolean longPressActivated;
    private final Runnable longPressAction = new Runnable() {
        @Override
        public void run() {
            longPressActivated = true;
            mlistener.onDeactivateButtonLongPress();
            klistener.onDeactivateButtonLongPress();
            slistener.stopForeground();
        }
    };

    public static void setStopForegroundListener(StopForegroundListener listener) {
        slistener = listener;
    }

    public static void setFloatingWindowListener(FloatingWindowListener listener) {
        mlistener = listener;
    }

    public static void setKeyboardWindowListener(FloatingKeyboardListener listener) {
        klistener = listener;
    }

    public static void deactivateKeyboard() {
        klistener.onDeactivateButtonLongPress();
    }

    protected void layer2ClickListener(LinearLayout layer2, WebView webView, ViewGroup floatView) {
        ImageView hide_button = layer2.findViewById(R.id.hide_button);
        ImageView keyboard_button = layer2.findViewById(R.id.keyboard_button);
        ImageView dekstop_or_android_button = layer2.findViewById(R.id.dekstop_button);
        ImageView bypass_button = layer2.findViewById(R.id.bypass_button);
        ImageView deactivate_button = layer2.findViewById(R.id.deactivate_button);

        ImageView stop_bypass = floatView.findViewById(R.id.stop_bypass);
        ImageView show_floating = floatView.findViewById(R.id.show_floating);


        if (!DashboardUtils.getDOUBLE_SAFETY()) {
            stop_bypass.setOnClickListener(v -> {
                mlistener.onStopBypassButtonClick();
            });
        } else {
            stop_bypass.setOnClickListener(view -> {
                if (SafetyCounter.getCounter() < 7) {
                    SafetyCounter.setCounter(counter);
                    counter = SafetyCounter.getCounter();
                    return;
                }
                mlistener.onStopBypassButtonClick();

            });
        }


        if (DashboardUtils.getDOUBLE_SAFETY()) {
            show_floating.setOnLongClickListener(view -> {
                isSafetyActive = FloatingShowOrHideHelperSafety.getIsSafetyTouched();
                if (isSafetyActive) {
                    mlistener.onShowFloatingButtonClick();
                    FloatingShowOrHideHelperSafety.setIsSafetyTouched(false);
                    FloatingShowOrHideHelperSafety.setIsSafetyOn(false);
                }
                return false;
            });
        }

        show_floating.setOnClickListener(v -> {
            if (DashboardUtils.getDOUBLE_SAFETY() && DashboardUtils.getANTI_OBSCURE_SAFETY()) {

            } else {
                mlistener.onShowFloatingButtonClick();
            }
        });

        if (DashboardUtils.getANTI_OBSCURE_SAFETY()) {
            show_floating.setOnLongClickListener(view -> {
                mlistener.onShowFloatingButtonClick();
                return false;
            });
        }

        if (DashboardUtils.getANTI_OBSCURE_SAFETY() && DashboardUtils.getUSE_VIBRATION()) {
            show_floating.setOnLongClickListener(view -> {
                mlistener.onShowFloatingButtonClick();
                return false;
            });
        }


        hide_button.setOnClickListener(v -> {
            mlistener.onHideButtonClick();
            klistener.onHideButtonClick();

            isKeyboardActive = false;
            keyboard_button.setImageResource(R.drawable.button_keyboard);
        });

        keyboard_button.setOnClickListener(v -> {
            mlistener.onKeyboarButtonClick();
            klistener.onKeyboardButtonClick();

            if (isKeyboardActive) {
                ObjectAnimator translateY = ObjectAnimator.ofFloat(keyboard_button, "translationY", 100f, 0f);
                translateY.setDuration(300);
                translateY.start();

                keyboard_button.setImageResource(R.drawable.button_keyboard);

                isKeyboardActive = false;
            } else {
                ObjectAnimator translateY = ObjectAnimator.ofFloat(keyboard_button, "translationY", 100f, 0f);
                translateY.setDuration(300);
                translateY.start();

                keyboard_button.setImageResource(R.drawable.button_keyboard_hide);

                isKeyboardActive = true;
            }
        });

        dekstop_or_android_button.setOnClickListener(v -> {
            if (isDekstopMode) {
                ObjectAnimator translateY = ObjectAnimator.ofFloat(dekstop_or_android_button, "translationY", 100f, 0f);
                translateY.setDuration(300);
                translateY.start();

                webView.loadUrl(Objects.requireNonNull(webView.getUrl()));
                webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.6312.40 Mobile Safari/537.36");
                dekstop_or_android_button.setImageResource(R.drawable.button_android);

                isDekstopMode = false;
            } else {
                ObjectAnimator translateY = ObjectAnimator.ofFloat(dekstop_or_android_button, "translationY", 100f, 0f);
                translateY.setDuration(300);
                translateY.start();

                webView.loadUrl(Objects.requireNonNull(webView.getUrl()));
                webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");

                dekstop_or_android_button.setImageResource(R.drawable.button_dekstop);

                isDekstopMode = true;
            }
        });

        bypass_button.setOnClickListener(v -> {
            mlistener.onBypassButtonClick();
            isKeyboardActive = false;
        });

        deactivate_button.setOnClickListener(v -> {
            mlistener.onDeactivateButtonClick();
        });

        Handler handler = new Handler();

        deactivate_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Start a delayed action after 3 seconds
                handler.postDelayed(longPressAction, 1000);
                return true;
            }
        });

        deactivate_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.removeCallbacks(longPressAction);
                    if (longPressActivated) {
                        isKeyboardActive = false;
                        longPressActivated = false;
                    }
                }
                return false;
            }
        });


    }

}
