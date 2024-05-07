package com.mzfreeapp.floatingbrowser.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mzfreeapp.floatingbrowser.R;
import com.mzfreeapp.floatingbrowser.dashboard.listeners.ClickListener;
import com.mzfreeapp.floatingbrowser.dashboard.listeners.OnEnterListener;
import com.mzfreeapp.floatingbrowser.dashboard.listeners.SwitchListener;
import com.mzfreeapp.floatingbrowser.dashboard.setup.AddList;
import com.mzfreeapp.floatingbrowser.dashboard.setup.DashboardAdapter;
import com.mzfreeapp.floatingbrowser.dashboard.setup.DashboardData;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements DashboardAdapter.OnItemClickListener,
        DashboardAdapter.OnSwitchChangeListener, DashboardAdapter.OnStringEntered {
    CheckService checkService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ConstraintLayout menu_layout = view.findViewById(R.id.dashboard_layout);
        RecyclerView recyclerView = menu_layout.findViewById(R.id.dashboard_recyclerview);
        Button activate_button = view.findViewById(R.id.activate_button);

        final java.util.List<DashboardData> dataList = new ArrayList<>();
        final java.util.List<Boolean> switchStates = new ArrayList<>();

        checkService = new CheckService(getContext(), getResources());

        /*
         * If you need to add more layout go to dashboard/setup/AddList.java
         * and remember, all items position started from zero and every method is connected.
         * so if you already set onItemClick on position 1,
         * be sure to not place onSwitchChange on position 1, too.
         */
        AddList list = new AddList(dataList, getResources(), checkService);

        for (int i = 0; i < dataList.size(); i++) {
            switchStates.add(false);
        }

        checkService.checkAndStopService(getContext());
        DashboardAdapter dashboardAdapter = new DashboardAdapter(dataList, switchStates, this, this, this);
        list.setAdapter(dashboardAdapter, recyclerView, dataList);

        /*
        This button logic is checking if the service is active first or not
        if the service active, it will stop that service then activate it again.
        */
        activate_button.setOnClickListener(v -> {
            if (checkService.isPermissionGranted()) {
                checkService.checkAndStopService(getContext());
                checkService.startServices();
            } else {
                checkService.checkOverlayPermission();
            }
        });
        return view;


    }



    /*
    * onItemClick, onSwitchChange, and onEnter is part of RecyclerView listener,
    * this method gives a functionality for dashboard recyclerview items.
    * onItemClick handle clicks from STATUS_LAYOUT.
    * onSwitchChange handle switch changes from SWITCH_LAYOUT.
    * onEnter handle entered items from STRING_LAYOUT.
    * You can add more listener on dashboard/listeners/...
    */
    @Override
    public void onItemClick(int position) {
        new ClickListener(position, getResources(), getContext(), checkService);
    }

    @Override
    public void onSwitchChange(int position, boolean isChecked) {
        new SwitchListener(position, isChecked);
    }

    @Override
    public void onEnter(int position, String enteredString) {
        new OnEnterListener(position, enteredString);
    }


}

