package com.mzfreeapp.floatingbrowser.terminal;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mzfreeapp.floatingbrowser.R;
import com.mzfreeapp.floatingbrowser.dashboard.listeners.ClickListener;
import com.mzfreeapp.floatingbrowser.terminal.setup.TerminalAdapter;
import com.mzfreeapp.floatingbrowser.terminal.setup.TerminalData;

import java.util.ArrayList;
import java.util.List;


public class TerminalFragment extends Fragment {

    TerminalShell terminalShell;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terminal, container, false);

        ImageView exit_terminal = view.findViewById(R.id.exit_terminal);
        exit_terminal.setOnClickListener(v -> ClickListener.tlistener.onBackButton());
        RecyclerView shell_recyclerview = view.findViewById(R.id.shell_recyclerview);
        EditText shell_input = view.findViewById(R.id.shell_input);
        WebView terminal_webview = view.findViewById(R.id.terminal_webview);

        TextView send = view.findViewById(R.id.send);
        send.setOnClickListener(v -> {
            String input = shell_input.getText().toString();
            onEnter(input);
            shell_input.setText("");
        });
        shell_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String input = shell_input.getText().toString();
                    onEnter(input);
                    shell_input.setText("");
                    return true;
                }
                return false;
            }
        });

        List<TerminalData> shellList = new ArrayList<>();
        TerminalAdapter terminalAdapter = new TerminalAdapter(shellList, getContext());

        terminalShell = new TerminalShell(shellList, getResources(), terminalAdapter, shell_recyclerview);
        terminalShell.setWebView(terminal_webview);
        terminalShell.startShell();

        return view;
    }

    private void onEnter(String input) {
        terminalShell.addInputShell(input);
    }
}
