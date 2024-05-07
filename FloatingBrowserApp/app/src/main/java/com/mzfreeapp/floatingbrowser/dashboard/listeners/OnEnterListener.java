package com.mzfreeapp.floatingbrowser.dashboard.listeners;

import com.mzfreeapp.floatingbrowser.dashboard.DashboardUtils;

public class OnEnterListener {
    public OnEnterListener(int position, String enteredString) {
        switch (position) {
            case 0:
                DashboardUtils.setHome_URL(enteredString);

        }
    }
}
