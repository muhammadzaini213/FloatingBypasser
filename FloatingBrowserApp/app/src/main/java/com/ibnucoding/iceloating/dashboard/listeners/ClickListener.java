package com.ibnucoding.iceloating.dashboard.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.adjustwindow.AdjustWindowListener;
import com.ibnucoding.iceloating.dashboard.CheckService;
import com.ibnucoding.iceloating.terminal.TerminalListener;

public class ClickListener {
    Resources resources;
    Context context;

    public static AdjustWindowListener alistener;

    public static void setAdjustScreenListener(AdjustWindowListener listener) {
        alistener = listener;
    }

    public static TerminalListener tlistener;

    public static void setTerminalListener(TerminalListener listener) {
        tlistener = listener;
    }

    Activity activity;
    InterstitialAd mInterstitialAd;

    public ClickListener(int position, Resources resources, Context context, CheckService checkService, Activity activity, InterstitialAd interstitialAd) {
        this.resources = resources;
        this.context = context;
        this.mInterstitialAd = interstitialAd;
        this.activity = activity;
        switch (position) {
            case 1:
                showAd();
//                alistener.onAdjustWindowOpen();
                Toast.makeText(context, resources.getString(R.string.adjust_window_title) +" "+
                                resources.getString(R.string.only_in_premium)
                        , Toast.LENGTH_SHORT).show();
                break;
            case 2:
                showAd();
                Toast.makeText(context,  resources.getString(R.string.terminal_title) +" "+
                                resources.getString(R.string.only_in_premium)
                        , Toast.LENGTH_SHORT).show();
//                tlistener.openTerminal();
                break;
            case 3:
                showAd();
                if (checkService.isPermissionGranted()) {
                    Toast.makeText(context, resources.getString(R.string.permission_granted)
                            , Toast.LENGTH_SHORT).show();
                } else {
                    checkService.checkOverlayPermission();
                }
                break;
        }
    }

    private void showAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.

                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.

                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    mInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                }
            });

        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

}
