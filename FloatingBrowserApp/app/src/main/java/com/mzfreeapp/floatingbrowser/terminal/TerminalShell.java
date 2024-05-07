package com.mzfreeapp.floatingbrowser.terminal;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mzfreeapp.floatingbrowser.terminal.setup.TerminalAdapter;
import com.mzfreeapp.floatingbrowser.terminal.setup.TerminalData;

import java.util.List;

public class TerminalShell {
    private final int NORMAL_SHELL = 1;
    List<TerminalData> shellList;
    Resources resources;
    TerminalAdapter terminalAdapter;

    Handler handler;

    public TerminalShell(List<TerminalData> shellList, Resources resources, TerminalAdapter terminalAdapter, RecyclerView recyclerView) {
        this.shellList = shellList;
        this.resources = resources;
        this.terminalAdapter = terminalAdapter;
        recyclerView.setAdapter(terminalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        handler = new Handler();
    }
    public void startShell(){
        addShell("loading data...", Color.GREEN, NORMAL_SHELL, 0);
//        addShell("installing camera system...", Color.GREEN, NORMAL_SHELL, 1500);
//        addShell("installing audio system...", Color.GREEN, NORMAL_SHELL, 3000);
        addShell("system preparation complete: 0 error", Color.GREEN, NORMAL_SHELL, 5000);
        addShell("initializing shell...", Color.GREEN, NORMAL_SHELL, 6500);
//        addShell("write /start to activate", Color.WHITE, NORMAL_SHELL, 8000);
    }
    public void addInputShell(String input) {
        if(!input.isEmpty()) {
            addShell("> " + input, Color.WHITE, NORMAL_SHELL, 0);
            terminalAdapter.notifyItemInserted(shellList.size());
        }
    }

    private void addShell(String input, int color, int shellType, int delay) {
        handler.postDelayed(() -> {
            shellList.add(new TerminalData(input, color, shellType));
            terminalAdapter.notifyItemInserted(shellList.size());
        }, delay);
    }
}
