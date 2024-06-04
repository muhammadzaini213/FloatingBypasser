package com.ibnucoding.iceloating.home;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdLoader {
    private static final String TAG = "ACTIVATE_FLOATING";
    private InterstitialAd mInterstitialAd;

    public void loadOpenAppAd(Context context, Activity activity) {
        AdRequest adRequest3 = new AdRequest.Builder().build();
        InterstitialAd.load(context, "ca-app-pub-9202355295382068/5188209811", adRequest3,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        showOpenAppAd(activity);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }

    public void loadBannerAd(AdView adView, AdView adView2) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);
    }

    public void showOpenAppAd(Activity activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        }
    }


}
