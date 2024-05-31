package com.ibnucoding.iceloating.dashboard.listeners;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.ibnucoding.iceloating.dashboard.DashboardUtils;

public class SwitchListener {
InterstitialAd mInterstitialAd;
Activity activity;
    public SwitchListener(int position, boolean isChecked, Activity activity, InterstitialAd mIntersititialAd) {
        this.activity = activity;
        this.mInterstitialAd = mIntersititialAd;
        if (position == 4) {
            DashboardUtils.setUnfocusBoolean(isChecked);
            showAd();
        } else if (position == 5) {
            DashboardUtils.setHiddenMode(isChecked);
            showAd();
        } else if (position == 6) {
            DashboardUtils.setAntiObscure(isChecked);
            showAd();
        } else if (position == 7) {
            DashboardUtils.setUSE_VIBRATION(isChecked);
            showAd();
        } else if (position == 8) {
            showAd();
            DashboardUtils.setDOUBLE_SAFETY(isChecked);
        }

    }

    private void showAd(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
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
