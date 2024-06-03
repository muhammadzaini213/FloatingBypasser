package com.ibnucoding.iceloating;

import static com.ibnucoding.iceloating.dashboard.listeners.ClickListener.setAdjustScreenListener;
import static com.ibnucoding.iceloating.dashboard.listeners.ClickListener.setFreezerListener;
import static com.ibnucoding.iceloating.dashboard.listeners.ClickListener.setTerminalListener;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.ibnucoding.iceloating.freezer.FreezerFragment;
import com.ibnucoding.iceloating.freezer.FreezerListener;
import com.ibnucoding.iceloating.terminal.TerminalFragment;
import com.ibnucoding.iceloating.terminal.TerminalListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private InterstitialAd mInterstitialAd;

    private static final String TAG = "ACTIVATE_FLOATING";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        Utils.setAofeohofw(sharedPreferences.getBoolean("aofeohofw", false));
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AdView adView2 = findViewById(R.id.adView2);

        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);

        AdRequest adRequest3 = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-9202355295382068/5188209811", adRequest3,
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

        findViewById(R.id.free_version).setOnClickListener(view -> {
            startIceloatingFree();
            findViewById(R.id.verification_container).setVisibility(View.GONE);
        });

        findViewById(R.id.id_btn_verification).setOnClickListener(view -> {
            EditText id = findViewById(R.id.id_textfield);
            String id_string = id.getText().toString();
            if (!id_string.isEmpty()) {
                readDb(id_string);
                Button button = findViewById(R.id.id_btn_verification);
                button.setText("Loading...");
                findViewById(R.id.id_btn_verification).setOnClickListener(view1 -> {
                });
            } else {
                Toast.makeText(this, "Id kosong", Toast.LENGTH_LONG).show();
            }
        });


        if (sharedPreferences.getBoolean("isVerified", false)) {
            readDb(sharedPreferences.getString("id", "n"));
            Button button = findViewById(R.id.id_btn_verification);
            button.setText("Loading...");
            findViewById(R.id.id_btn_verification).setOnClickListener(view1 -> {
            });
        }

    }

    private void startIceloatingFreee() {
          /*
        This listener used for changing fragments
         */
        adjustScreenListener();
        terminalListener();
        freezerListener();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
        requestDNDPermission();
    }

    private void startIceloatingFree() {
        /*
        This listener used for changing fragments
         */
        sharedPreferences.edit().putBoolean("aofeohofw", false).apply();
        adjustScreenListener();
        terminalListener();
        freezerListener();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
        Toast.makeText(this, getString(R.string.welcome_free), Toast.LENGTH_LONG).show();
        requestDNDPermission();
    }

    private void freezerListener() {
        setFreezerListener(new FreezerListener() {
            @Override
            public void onFreezerOpen() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new FreezerFragment()).commit();
            }

            @Override
            public void onBackButton() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();

            }
        });
    }

    private void terminalListener() {
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

    private void readDb(String id_text) {
        SharedPreferences sharedPreferences = getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        DocumentReference schoolDocRef = db.collection("users").document(id_text);
        schoolDocRef.get().addOnCompleteListener(task -> {
            loadAd();
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Document exists, handle the retrieved data
                    boolean active = Boolean.TRUE.equals(document.getBoolean("active"));
                    boolean block = Boolean.TRUE.equals(document.getBoolean("blocked"));
                    String build_id = document.getString("build_id");

                    if (!block) {
                        if (Objects.equals(build_id, "null")) {
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("build_id", Build.ID);
                            schoolDocRef.update(userData);
                        } else if (!build_id.equals(Build.ID)) {
                            Toast.makeText(this, "Akun dipakai di device lain", Toast.LENGTH_LONG).show();
                            Button button = findViewById(R.id.id_btn_verification);
                            button.setText("Verifikasi");
                            findViewById(R.id.id_btn_verification).setOnClickListener(view -> {
                                EditText id = findViewById(R.id.id_textfield);
                                String id_string = id.getText().toString();
                                if (!id_string.isEmpty()) {
                                    readDb(id_string);
                                    button.setText("Loading...");
                                    findViewById(R.id.id_btn_verification).setOnClickListener(view1 -> {
                                    });
                                } else {
                                    Toast.makeText(this, "Id kosong", Toast.LENGTH_LONG).show();
                                }

                            });
                            return;
                        }
                        if (active) {
                            editor.putBoolean("aofeohofw", true).apply();
                            editor.putBoolean("isVerified", true).apply();
                            editor.putString("id", id_text).apply();
                            startIceloatingFreee();
                            Utils.setAofeohofw(true);
                            Toast.makeText(this, getString(R.string.welcome_premium), Toast.LENGTH_LONG).show();
                            findViewById(R.id.verification_container).setVisibility(View.GONE);

                        } else {
                            Toast.makeText(this, "Akun tidak aktif", Toast.LENGTH_LONG).show();

                            Button button = findViewById(R.id.id_btn_verification);
                            button.setText("Verifikasi");
                            findViewById(R.id.id_btn_verification).setOnClickListener(view -> {
                                EditText id = findViewById(R.id.id_textfield);
                                String id_string = id.getText().toString();
                                if (!id_string.isEmpty()) {
                                    readDb(id_string);
                                    button.setText("Loading...");
                                    findViewById(R.id.id_btn_verification).setOnClickListener(view1 -> {
                                    });
                                } else {
                                    Toast.makeText(this, "Id kosong", Toast.LENGTH_LONG).show();
                                }

                            });
                        }

                    } else {
                        Toast.makeText(this, "Akun anda diblokir, mohon selesaikan pembayaran", Toast.LENGTH_LONG).show();

                        editor.clear().apply();
                        editor.putBoolean("aofeohofw", false).apply();
                        editor.putBoolean("isVerified", false).apply();
                        Utils.setAofeohofw(false);
                        Button button = findViewById(R.id.id_btn_verification);
                        button.setText("Verifikasi");
                        findViewById(R.id.id_btn_verification).setOnClickListener(view -> {
                            EditText id = findViewById(R.id.id_textfield);
                            String id_string = id.getText().toString();
                            if (!id_string.isEmpty()) {
                                readDb(id_string);
                                button.setText("Loading...");
                                findViewById(R.id.id_btn_verification).setOnClickListener(view1 -> {
                                });
                            } else {
                                Toast.makeText(this, "Id kosong", Toast.LENGTH_LONG).show();
                            }

                        });

                    }
                } else {
                    Toast.makeText(this, "Id tidak ditemukan", Toast.LENGTH_SHORT).show();
                    Button button = findViewById(R.id.id_btn_verification);
                    button.setText("Verifikasi");
                    findViewById(R.id.id_btn_verification).setOnClickListener(view -> {

                        EditText id = findViewById(R.id.id_textfield);
                        String id_string = id.getText().toString();
                        if (!id_string.isEmpty()) {
                            readDb(id_string);
                            button.setText("Loading...");
                            findViewById(R.id.id_btn_verification).setOnClickListener(view1 -> {
                            });
                        } else {
                            Toast.makeText(this, "Id kosong", Toast.LENGTH_LONG).show();
                        }

                    });

                }
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                Button button = findViewById(R.id.id_btn_verification);
                button.setText("Verifikasi");
                findViewById(R.id.id_btn_verification).setOnClickListener(view -> {
                    EditText id = findViewById(R.id.id_textfield);
                    String id_string = id.getText().toString();
                    if (!id_string.isEmpty()) {
                        readDb(id_string);
                        button.setText("Loading...");
                        findViewById(R.id.id_btn_verification).setOnClickListener(view1 -> {
                        });
                    } else {
                        Toast.makeText(this, "Id kosong", Toast.LENGTH_LONG).show();
                    }

                });

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

    private void loadAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(HomeActivity.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
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


    private void requestDNDPermission() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (!notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Please grant Do Not Disturb access permission.", Toast.LENGTH_SHORT).show();
        }
    }


}
