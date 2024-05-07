package com.mzfreeapp.floatingbrowser.dashboard.listeners;

import com.mzfreeapp.floatingbrowser.dashboard.DashboardUtils;

public class SwitchListener {

    public SwitchListener(int position, boolean isChecked) {

        if (position == 4) {
            DashboardUtils.setUnfocusBoolean(isChecked);
        } else if (position == 5) {
            DashboardUtils.setHiddenMode(isChecked);
        } else if (position == 6) {
            DashboardUtils.setAntiObscure(isChecked);
        }

    }
}
