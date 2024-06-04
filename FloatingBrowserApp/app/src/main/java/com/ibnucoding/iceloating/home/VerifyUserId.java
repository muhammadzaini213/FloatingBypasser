package com.ibnucoding.iceloating.home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.adjustwindow.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VerifyUserId {
    AdLoader adLoader;
    EditText id;
    Activity activity;
    SharedPreferences sharedPreferences;
    Button verificationButton;

    public VerifyUserId(AdLoader adLoader, EditText id, SharedPreferences sharedPreferences, Activity activity) {
        this.adLoader = adLoader;
        this.id = id;
        this.sharedPreferences = sharedPreferences;
        this.activity = activity;
        this.verificationButton = activity.findViewById(R.id.id_btn_verification);
    }

    public void readDb() {
        String id_text = id.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userDocRef = db.collection("users").document(id_text);

        userDocRef.get().addOnCompleteListener(task -> {
            adLoader.showOpenAppAd(activity);
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    handleDocument(activity, sharedPreferences, editor, document, id_text);
                } else {
                    showToast(activity, "Id tidak ditemukan");
                    activateVerificationButton();
                }
            } else {
                showToast(activity, "Error");
                activateVerificationButton();
            }
        });
    }

    private void handleDocument(Activity activity, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, DocumentSnapshot document, String id_text) {
        boolean active = Boolean.TRUE.equals(document.getBoolean("active"));
        boolean block = Boolean.TRUE.equals(document.getBoolean("blocked"));
        String build_id = document.getString("build_id");

        if (!block) {
            handleActiveUser(editor, document, id_text, active, build_id);
        } else {
            showToast(activity, "Akun anda diblokir, mohon selesaikan pembayaran");
            clearUserPreferences(editor);
            activateVerificationButton();
        }
    }

    private void handleActiveUser(SharedPreferences.Editor editor, DocumentSnapshot document, String id_text, boolean active, String build_id) {
        if (Objects.equals(build_id, "null")) {
            updateBuildId(document.getReference());
        } else if (!build_id.equals(Build.ID)) {
            showToast(activity, "Akun dipakai di device lain");
            activateVerificationButton();
            return;
        }

        if (active) {
            verifyUser(editor, id_text);

        } else {
            showToast(activity, "Akun tidak aktif");
            activateVerificationButton();
        }
    }

    private void updateBuildId(DocumentReference userDocRef) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("build_id", Build.ID);
        userDocRef.update(userData);
    }

    private void verifyUser(SharedPreferences.Editor editor, String id_text) {
        editor.putBoolean("aofeohofw", true).apply();
        editor.putBoolean("isVerified", true).apply();
        editor.putString("id", id_text).apply();
        Utils.setAofeohofw(true);
        showToast(activity, activity.getString(R.string.welcome_premium));
        activity.findViewById(R.id.verification_container).setVisibility(View.GONE);
        HomeActivity.hListener.dashboardFragment();
    }

    private void clearUserPreferences(SharedPreferences.Editor editor) {
        editor.clear().apply();
        editor.putBoolean("aofeohofw", false).apply();
        editor.putBoolean("isVerified", false).apply();
        Utils.setAofeohofw(false);
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private void activateVerificationButton() {
        activateVerificationButton(verificationButton, id);
    }

    public void activateVerificationButton(Button verificationbutton, EditText id) {
        verificationbutton.setText("Verifikasi");
        verificationbutton.setOnClickListener(view -> {
            String id_string = id.getText().toString();
            if (!id_string.isEmpty()) {
                readDb();
                verificationbutton.setText("Loading...");
                verificationbutton.setOnClickListener(view1 -> {
                });
            } else {
                showToast(activity, "Id kosong");
            }

        });

    }
}
