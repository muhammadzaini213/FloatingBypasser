package com.ibnucoding.iceloating.dashboard.setup;

import android.content.res.Resources;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.dashboard.CheckService;

public class AddList {

    private static final int SWITCH_DATA = 1;
    private static final int STRING_DATA = 2;
    private static final int STATUS_DATA = 3;
    private static final int NON_CHANGEABLE_DATA = 4;

    /*
    You can add more list for recyclerview here,
    layout sets are placed in res/layout/adapter_xxxx.

    SWITCH_LAYOUT is an layout with switch and you can handle the switch listener
    in listeners/SwitchListener based on where it placed.

    STRING_LAYOUT is an layout that handles string data.

    STATUS_LAYOUT handles any status settings like positions or permissions.

    NON_CHANGEABLE_LAYOUT only used for showing something and doest had listeners.

    Just remember, some layout had part that hidden so to make it easier,
    use "unused" string, you can check that on strings, layout.
     */
    public AddList(java.util.List<DashboardData> dataList, Resources resources, CheckService checkService) {

        dataList.add(new DashboardData(
                resources.getString(R.string.home_url_title),
                resources.getString(R.string.home_url_desc),
                resources.getString(R.string.home_url_default),
                false,
                STRING_DATA));

        dataList.add(new DashboardData(
                resources.getString(R.string.adjust_window_title),
                resources.getString(R.string.adjust_window_desc),
                resources.getString(R.string.int_unused),
                false,
                STATUS_DATA));

        dataList.add(new DashboardData(
                resources.getString(R.string.terminal_title),
                resources.getString(R.string.terminal_desc),
                resources.getString(R.string.int_unused),
                false,
                STATUS_DATA));

        dataList.add(new DashboardData(
                resources.getString(R.string.permission_status_title),
                resources.getString(R.string.permission_status_desc),
                resources.getString(R.string.int_unused),
                false,
                STATUS_DATA));

        dataList.add(new DashboardData(
                resources.getString(R.string.float_unfocused_title),
                resources.getString(R.string.float_unfocused_desc),
                resources.getString(R.string.int_unused),
                false,
                SWITCH_DATA));

        dataList.add(new DashboardData(
                resources.getString(R.string.hidden_mode_title),
                resources.getString(R.string.hidden_mode_desc),
                resources.getString(R.string.int_unused),
                false,
                SWITCH_DATA));

        dataList.add(new DashboardData(
                resources.getString(R.string.anti_obscure_title),
                resources.getString(R.string.anti_obscure_desc),
                resources.getString(R.string.int_unused),
                false,
                SWITCH_DATA));

        dataList.add(new DashboardData(
                resources.getString(R.string.use_vibration_title),
                resources.getString(R.string.use_vibration_desc),
                resources.getString(R.string.int_unused),
                false, SWITCH_DATA));

    }

    public void setAdapter(DashboardAdapter dashboardAdapter, RecyclerView recyclerView, java.util.List<DashboardData> dataList) {
        recyclerView.setAdapter(dashboardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        dashboardAdapter.notifyItemInserted(dataList.size());
    }
}
