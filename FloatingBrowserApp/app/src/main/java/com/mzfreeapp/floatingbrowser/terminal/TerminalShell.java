package com.mzfreeapp.floatingbrowser.terminal;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mzfreeapp.floatingbrowser.terminal.setup.TerminalAdapter;
import com.mzfreeapp.floatingbrowser.terminal.setup.TerminalData;

import java.util.List;

public class TerminalShell {
    private final int NORMAL_SHELL = 1;
    List<TerminalData> shellList;
    Resources resources;
    TerminalAdapter terminalAdapter;

    Handler handler;

    FirebaseDatabase database;
    RecyclerView recyclerView;
    String onlineCommand;

    public TerminalShell(List<TerminalData> shellList, Resources resources, TerminalAdapter terminalAdapter, RecyclerView recyclerView) {
        this.shellList = shellList;
        this.resources = resources;
        this.recyclerView = recyclerView;
        this.terminalAdapter = terminalAdapter;
        database = FirebaseDatabase.getInstance();
        recyclerView.setAdapter(terminalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        onlineScript = "there are no online script";
        onlineCommand = "unrecognized command";
        handler = new Handler();
    }

    public void startShell() {
        addShell("loading data...", Color.GREEN, NORMAL_SHELL, 0);
        addShell("system preparation complete: 0 error", Color.GREEN, NORMAL_SHELL, 2000);
        addShell("initializing shell...", Color.GREEN, NORMAL_SHELL, 4500);
    }

    public void addInputShell(String input) {
        if (!input.isEmpty()) {
            addShell("> " + input, Color.WHITE, NORMAL_SHELL, 0);
            scriptAction(input);
        }
    }

    String onlineScript;

    private void scriptAction(String input) {
        if (input.startsWith("web ")) {
            String commandType = input.substring("web ".length());
            if (commandType.startsWith("js ")) {
                String js = commandType.substring("js ".length());
                addShell("injecting: " + js, Color.WHITE, NORMAL_SHELL, 300);
                webView.evaluateJavascript(js, null);
            } else if (commandType.startsWith("load ")) {
                String loadWeb = commandType.substring("load ".length());
                webView.loadUrl(loadWeb);
            } else if (commandType.startsWith("view ")) {
                String methodType = commandType.substring("view ".length());
                switch (methodType) {
                    case "open":
                        webView.setVisibility(View.VISIBLE);
                        break;
                    case "close":
                        webView.setVisibility(View.GONE);
                        break;
                    default:
                        addShell("err: " + methodType + " method not found in view", Color.WHITE, NORMAL_SHELL, 300);
                }
            } else {
                addShell("err: " + commandType + " command not found in web", Color.WHITE, NORMAL_SHELL, 300);
            }
        } else if (input.startsWith("get ")) {
            String commandType = input.substring("get ".length());
            if (!commandType.isEmpty()) {
                DatabaseReference scriptReference = database.getReference(commandType);
                scriptReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String script = dataSnapshot.getValue(String.class);
                            assert script != null;
                            webView.evaluateJavascript(script, null);
                            onlineScript = commandType;
                            addShell("installing " + commandType + "...", Color.WHITE, NORMAL_SHELL, 300);
                            addShell(commandType + " tools installed successfully", Color.WHITE, NORMAL_SHELL, 500);
                        } else {
                            addShell(commandType + " command might not found in database", Color.WHITE, NORMAL_SHELL, 300);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        addShell(commandType + " command are not found in database", Color.WHITE, NORMAL_SHELL, 300);
                    }
                });
            }

        } else if (input.startsWith(onlineScript)) {
            String commandType = input.substring(onlineScript.length());
            onlineCommand = commandType;
            String[] commandArray = commandType.split(" ");
//            addShell(commandArray[2], Color.GREEN, NORMAL_SHELL, 100);
//            addShell(commandArray[3], Color.GREEN, NORMAL_SHELL, 100);
//            addShell(commandArray[4], Color.GREEN, NORMAL_SHELL, 100);
//            addShell(commandArray[5], Color.GREEN, NORMAL_SHELL, 100);

            if (commandArray.length >= 5) {
                webView.evaluateJavascript(commandArray[1] + "('" + commandArray[2] + "','" + commandArray[3] + "','" + commandArray[4] + "','" + commandArray[5]+"')", null);
            } else if (commandArray.length >= 4) {
                webView.evaluateJavascript(commandArray[1] + "('" + commandArray[2] + "','" + commandArray[3] + "','" + commandArray[4] + "')", null);
            } else if (commandArray.length >= 3) {
                webView.evaluateJavascript(commandArray[1] + "('" + commandArray[2] + "','" + commandArray[3] + "')", null);
            } else {
                webView.evaluateJavascript(commandType + "()", null);
            }

        } else if (input.equals("onlinescript")) {
            addShell("current online script:" + onlineScript, Color.WHITE, NORMAL_SHELL, 300);
        } else {
            addShell(input + " command not found", Color.WHITE, NORMAL_SHELL, 300);
        }
    }

    private void addShell(String input, int color, int shellType, int delay) {
        handler.postDelayed(() -> {
            shellList.add(new TerminalData(input, color, shellType));
            terminalAdapter.notifyItemInserted(shellList.size());
            recyclerView.scrollToPosition(terminalAdapter.getItemCount() - 1);
        }, delay);
    }

    WebView webView;

    public void setWebView(WebView webView) {
        this.webView = webView;
        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient() {
                                     String url;

                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                         return super.shouldOverrideUrlLoading(view, request);
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         addShell(url + " loaded", Color.GREEN, NORMAL_SHELL, 300);
                                     }

                                     @Override
                                     public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                                         super.onReceivedHttpError(view, request, errorResponse);
                                     }
                                 }
        );
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage.message().startsWith("red: ")) {
                    String console = consoleMessage.message().substring("red: ".length());
                    addShell(console, Color.RED, NORMAL_SHELL, 500);
                } else if (consoleMessage.message().startsWith("blue: ")) {
                    String console = consoleMessage.message().substring("blue: ".length());
                    addShell(console, Color.BLUE, NORMAL_SHELL, 500);
                } else if (consoleMessage.message().startsWith("green: ")) {
                    String console = consoleMessage.message().substring("green: ".length());
                    addShell(console, Color.GREEN, NORMAL_SHELL, 500);
                } else if (consoleMessage.message().startsWith("white: ")) {
                    String console = consoleMessage.message().substring("white: ".length());
                    addShell(console, Color.WHITE, NORMAL_SHELL, 500);
                } else {
//                    if (consoleMessage.message().startsWith("Uncaught ReferenceError: ")) {
//                        addShell("err: " + onlineCommand + " is not recognized in " + onlineScript, Color.WHITE, NORMAL_SHELL, 500);
//                    }
                    addShell("err: " + consoleMessage.message(), Color.WHITE, NORMAL_SHELL, 500);
                }
                return super.onConsoleMessage(consoleMessage);
            }
        });

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
    }
}


