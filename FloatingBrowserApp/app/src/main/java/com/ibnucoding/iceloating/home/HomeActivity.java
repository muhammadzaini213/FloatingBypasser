package com.ibnucoding.iceloating.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.AdView;
import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.adjustwindow.AdjustWindowFragment;
import com.ibnucoding.iceloating.adjustwindow.Utils;
import com.ibnucoding.iceloating.dashboard.DashboardFragment;
import com.ibnucoding.iceloating.freezer.FreezerFragment;
import com.ibnucoding.iceloating.terminal.TerminalFragment;

public class HomeActivity extends AppCompatActivity {

    public static HomeListener hListener;
    AdLoader adLoader;
    SharedPreferences sharedPreferences;

    public static void setHomeListener(HomeListener homeListener) {
        hListener = homeListener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AdView adView = findViewById(R.id.adView);
        AdView adView2 = findViewById(R.id.adView2);
        adLoader = new AdLoader();
        adLoader.loadOpenAppAd(this, this);
        adLoader.loadBannerAd(adView, adView2);

        sharedPreferences = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        Utils.setAofeohofw(sharedPreferences.getBoolean("aofeohofw", false));

        LinearLayout verification_container = findViewById(R.id.verification_container);

        Button free_mode_btn = findViewById(R.id.free_mode);
        free_mode_btn.setOnClickListener(view -> {
            new IceloatingFree(sharedPreferences, this);
            verification_container.setVisibility(View.GONE);
        });

        Button verificationbutton = findViewById(R.id.id_btn_verification);
        EditText id = findViewById(R.id.id_textfield);

        VerifyUserId verifyUserId = new VerifyUserId(adLoader, id, sharedPreferences, this);
        verifyUserId.activateVerificationButton(verificationbutton, id);


        setHomeListener(new HomeListener() {
            @Override
            public void dashboardFragment() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            }

            @Override
            public void adjustWindowFragment() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new AdjustWindowFragment()).commit();
            }

            @Override
            public void terminalFragment() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new TerminalFragment()).commit();
            }

            @Override
            public void freezerFragment() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new FreezerFragment()).commit();
            }
        });
    }

    private void startIceloatingFreee() {
        HomeActivity.hListener.dashboardFragment();
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
