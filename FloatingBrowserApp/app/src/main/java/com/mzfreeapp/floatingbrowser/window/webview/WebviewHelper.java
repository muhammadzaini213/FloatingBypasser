package com.mzfreeapp.floatingbrowser.window.webview;

import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mzfreeapp.floatingbrowser.R;
import com.mzfreeapp.floatingbrowser.dashboard.DashboardUtils;

public class WebviewHelper {

    WebView webView;
    EditText searchTextField;

    public void setWebView(ViewGroup floatView) {
        webView = floatView.findViewById(R.id.floating_webview);
        ConstraintLayout textFieldContainer = floatView.findViewById(R.id.text_field_container);
        searchTextField = textFieldContainer.findViewById(R.id.url_text_field);

        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new CustomWebViewClient());
        webSettings.setDefaultFontSize(12);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setUserAgentString("");
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowContentAccess(true);
        webView.setKeepScreenOn(true);


        /*
        Getting the value of HOME_URl from onEnterListener
        DashboardUtils can handle that, so don't worry.
         */
        String HOME_URL = DashboardUtils.getHome_URL();
        if (HOME_URL.startsWith("http") || HOME_URL.endsWith("com")) {
            webView.loadUrl(HOME_URL);
        } else {
            webView.loadUrl("https://google.com/search?q=" + HOME_URL);
        }

        searchTextField.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                String url = searchTextField.getText().toString();
                if (url.startsWith("http") || url.endsWith("com")) {
                    webView.loadUrl(url);
                } else {
                    webView.loadUrl("https://google.com/search?q=" + url);
                }
                return true;
            }
            return false;
        });

        WebviewKeyboardInput keyboardInput = new WebviewKeyboardInput();
        keyboardInput.init(webView, searchTextField);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            /*
            This CustomWebViewClient inject javascript code to websites,
            So the FloatingKeyboard can communicate with website and write.
             */
            if (!searchTextField.isFocused()) {
                searchTextField.setText(url);
            }
            webView.evaluateJavascript("var selectedEditableElement = null;\n" +
                    "\n" +
                    "var editableElements = document.querySelectorAll('input, textarea');\n" +
                    "\n" +
                    "editableElements.forEach(function(element) {\n" +
                    "    element.addEventListener('click', function() {\n" +
                    "        selectedEditableElement = element;\n" +
                    "    });\n" +
                    "});", null);
        }
    }
}
