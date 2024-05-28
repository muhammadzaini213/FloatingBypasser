package com.ibnucoding.iceloating.dashboard.listeners;

import com.ibnucoding.iceloating.dashboard.DashboardUtils;

public class OnEnterListener {
    public OnEnterListener(int position, String enteredString) {
        switch (position) {
            case 0:
                DashboardUtils.setHome_URL(enteredString);
        }
    }
}
