package com.ibnucoding.iceloating;

import static com.ibnucoding.iceloating.dashboard.listeners.ClickListener.setAdjustScreenListener;
import static com.ibnucoding.iceloating.dashboard.listeners.ClickListener.setTerminalListener;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ibnucoding.iceloating.adjustwindow.AdjustWindowFragment;
import com.ibnucoding.iceloating.adjustwindow.AdjustWindowListener;
import com.ibnucoding.iceloating.adjustwindow.Utils;
import com.ibnucoding.iceloating.dashboard.DashboardFragment;
import com.ibnucoding.iceloating.terminal.TerminalFragment;
import com.ibnucoding.iceloating.terminal.TerminalListener;

public class HomeActivity extends AppCompatActivity {


    private FirebaseFirestore db;

    private InterstitialAd mInterstitialAd;

    private static final String TAG = "ACTIVATE_FLOATING";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance();

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AdView adView2 = findViewById(R.id.adView2);

        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);

        AdRequest adRequest3 = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-9202355295382068/5188209811", adRequest3,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        loadAd();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });


        /*Starting the LoadingFragment, go to package dashboard/DashboardFragment.java,
         * this is the core of this app, so please don't change it */


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new LoadingFragment()).commit();


        /*
        This listener used for changing fragments
         */
        adjustScreenListener();

        setTerminalListener(new TerminalListener() {
            @Override
            public void openTerminal() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new TerminalFragment()).commit();
            }

            @Override
            public void onBackButton() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        Utils.setAofeohofw(sharedPreferences.getBoolean("aofeohofw", false));

        readDb();

    }

    private void readDb() {
        SharedPreferences sharedPreferences = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        DocumentReference schoolDocRef = db.collection("users").document("nibras");
        schoolDocRef.get().addOnCompleteListener(task -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            loadAd();
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Document exists, handle the retrieved data
                    boolean active = Boolean.TRUE.equals(document.getBoolean("active"));
                    if (active) {
                        editor.putBoolean("aofeohofw", true).apply();
                        Utils.setAofeohofw(true);
                        Toast.makeText(this, getString(R.string.welcome_premium), Toast.LENGTH_LONG).show();
                    } else {
                        editor.putBoolean("aofeohofw", false).apply();
                        Utils.setAofeohofw(false);
                        Toast.makeText(this, getString(R.string.welcome_free), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, getString(R.string.connection_lost), Toast.LENGTH_SHORT).show();
                    readDb();
                }
            } else {
                Toast.makeText(this, getString(R.string.connection_lost), Toast.LENGTH_SHORT).show();
                readDb();
            }
        });
    }

    private void adjustScreenListener() {
        setAdjustScreenListener(new AdjustWindowListener() {
            @Override
            public void onAdjustWindowOpen() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new AdjustWindowFragment())
                        .commit();
            }

            @Override
            public void onBackButton() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();

            }


        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void loadAd(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(HomeActivity.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d(TAG, "Ad was clicked.");

                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Log.d(TAG, "Ad dismissed fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    // Called when ad fails to show.
                    Log.e(TAG, "Ad failed to show fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d(TAG, "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d(TAG, "Ad showed fullscreen content.");
                    AdRequest adRequest3 = new AdRequest.Builder().build();

                }
            });

        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

}
