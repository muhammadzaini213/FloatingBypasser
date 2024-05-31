package com.ibnucoding.iceloating.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.dashboard.listeners.ClickListener;
import com.ibnucoding.iceloating.dashboard.listeners.OnEnterListener;
import com.ibnucoding.iceloating.dashboard.listeners.SwitchListener;
import com.ibnucoding.iceloating.dashboard.setup.AddList;
import com.ibnucoding.iceloating.dashboard.setup.DashboardAdapter;
import com.ibnucoding.iceloating.dashboard.setup.DashboardData;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements DashboardAdapter.OnItemClickListener,
        DashboardAdapter.OnSwitchChangeListener, DashboardAdapter.OnStringEntered {

    private InterstitialAd mInterstitialAd;
    private static final String TAG = "ACTIVATE_FLOATING";
    CheckService checkService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(requireContext(),"ca-app-pub-9202355295382068/3143107779", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });


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

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(requireActivity());
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
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
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
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
                        }
                    });

                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
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
        new ClickListener(position, getResources(), getContext(), checkService, getActivity(), mInterstitialAd);
    }

    @Override
    public void onSwitchChange(int position, boolean isChecked) {
        new SwitchListener(position, isChecked, getActivity(), mInterstitialAd);
    }

    @Override
    public void onEnter(int position, String enteredString) {
        new OnEnterListener(position, enteredString);
    }


}

