package com.mzfreeapp.floatingbrowser.dashboard.listeners;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.mzfreeapp.floatingbrowser.R;
import com.mzfreeapp.floatingbrowser.adjustwindow.AdjustWindowListener;
import com.mzfreeapp.floatingbrowser.dashboard.CheckService;
import com.mzfreeapp.floatingbrowser.terminal.TerminalListener;

public class ClickListener {
    Resources resources;
    Context context;

    public static AdjustWindowListener alistener;

    public static void setAdjustScreenListener(AdjustWindowListener listener){
        alistener = listener;
    }

    public static TerminalListener tlistener;

    public static void setTerminalListener(TerminalListener listener){
        tlistener = listener;
    }
    public ClickListener(int position, Resources resources, Context context, CheckService checkService) {
        this.resources = resources;
        this.context = context;

        switch (position) {
            case 1:
                alistener.onAdjustWindowOpen();
                break;
            case 2:
                tlistener.openTerminal();
                break;
            case 3:
                if (checkService.isPermissionGranted()) {
                    Toast.makeText(context, resources.getString(R.string.permission_granted)
                            , Toast.LENGTH_SHORT).show();
                } else {
                    checkService.checkOverlayPermission();
                }
                break;
        }
    }

}
