package com.ibnucoding.iceloating.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.adjustwindow.Utils;

public class IceloatingFree {

    public IceloatingFree(SharedPreferences sharedPreferences, Activity activity) {
        sharedPreferences.edit().putBoolean("aofeohofw", false).apply();
        Utils.setAofeohofw(false);
        HomeActivity.hListener.dashboardFragment();
        Toast.makeText(activity, activity.getString(R.string.welcome_free), Toast.LENGTH_LONG).show();
    }

}
