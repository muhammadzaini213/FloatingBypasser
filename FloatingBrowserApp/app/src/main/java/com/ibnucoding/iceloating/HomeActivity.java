package com.ibnucoding.iceloating;

import static com.ibnucoding.iceloating.dashboard.listeners.ClickListener.setAdjustScreenListener;
import static com.ibnucoding.iceloating.dashboard.listeners.ClickListener.setTerminalListener;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ibnucoding.iceloating.adjustwindow.AdjustWindowFragment;
import com.ibnucoding.iceloating.adjustwindow.AdjustWindowListener;
import com.ibnucoding.iceloating.dashboard.DashboardFragment;
import com.ibnucoding.iceloating.terminal.TerminalFragment;
import com.ibnucoding.iceloating.terminal.TerminalListener;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AdView adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AdView adView2 = findViewById(R.id.adView2);

        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);

        /*Starting the DashboardFragment, go to package dashboard/DashboardFragment.java,
         * this is the core of this app, so please don't change it */
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();

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


    public void onbackPressed() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
