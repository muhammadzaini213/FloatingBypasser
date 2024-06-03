package com.ibnucoding.iceloating.dashboard.listeners;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.adjustwindow.Utils;
import com.ibnucoding.iceloating.dashboard.DashboardUtils;

public class SwitchListener {
InterstitialAd mInterstitialAd;
Activity activity;
    public SwitchListener(int position, boolean isChecked, Activity activity, InterstitialAd mIntersititialAd) {
        this.activity = activity;
        this.mInterstitialAd = mIntersititialAd;

        switch (position){
            case 5:
                DashboardUtils.setUnfocusBoolean(isChecked);
                showAd();
                break;
            case 6:
                DashboardUtils.setHiddenMode(isChecked);
                showAd();
                break;
            case 7:
                DashboardUtils.setAntiObscure(isChecked);
                showAd();
                break;
            case 8:
                DashboardUtils.setUSE_VIBRATION(isChecked);
                showAd();
                break;
            case 9:
                showAd();
                DashboardUtils.setDOUBLE_SAFETY(isChecked);
                DashboardUtils.setAntiObscureSafety(isChecked);
                break;

            case 10:
                showAd();
                if(Utils.getAofeohofw()){
                    DashboardUtils.setBOTTLE_OPENER(isChecked);
                } else {
                    Toast.makeText(activity, activity.getString(R.string.bottle_opener_nonpremium), Toast.LENGTH_LONG).show();
                }
                break;
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
