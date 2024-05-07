package com.mzfreeapp.floatingbrowser;

import static com.mzfreeapp.floatingbrowser.dashboard.listeners.ClickListener.setAdjustScreenListener;
import static com.mzfreeapp.floatingbrowser.dashboard.listeners.ClickListener.setTerminalListener;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.mzfreeapp.floatingbrowser.adjustwindow.AdjustWindowFragment;
import com.mzfreeapp.floatingbrowser.adjustwindow.AdjustWindowListener;
import com.mzfreeapp.floatingbrowser.dashboard.DashboardFragment;
import com.mzfreeapp.floatingbrowser.terminal.TerminalFragment;
import com.mzfreeapp.floatingbrowser.terminal.TerminalListener;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

    public void onBackPressed() {
        super.onBackPressed();
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
