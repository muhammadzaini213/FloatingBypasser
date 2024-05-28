package com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar;

import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.ibnucoding.iceloating.R;

public class BottomNavbarItems {

    ViewGroup floatView;

    public void setOnClick(ViewGroup floatView) {
        this.floatView = floatView;
        WebView webView = floatView.findViewById(R.id.floating_webview);
        LinearLayout bottom_navbar = floatView.findViewById(R.id.bottom_navigation_bar);
        LinearLayout layer1 = bottom_navbar.findViewById(R.id.layer1);
        LinearLayout layer2 = bottom_navbar.findViewById(R.id.layer2);

        BottomNavbarLayer1Item layer1Item = new BottomNavbarLayer1Item();
        BottomNavbarLayer2Item layer2Item = new BottomNavbarLayer2Item();
        layer1Item.layer1ClickListener(layer1, layer2, webView);
        layer2Item.layer2ClickListener(layer2, webView, floatView);

    }


}
