package com.mzfreeapp.floatingbrowser.terminal.setup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mzfreeapp.floatingbrowser.R;

import java.util.List;

public class TerminalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<TerminalData> shellList;

    private static final int NORMAL_SHELL = 1;

    public TerminalAdapter(List<TerminalData> shellList) {
        this.shellList = shellList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int shellType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (shellType) {
            case NORMAL_SHELL:
                view = inflater.inflate(R.layout.adapter_normal_shell, parent, false);
                return new NormalShellViewHolder(view);
            default:
                throw new IllegalArgumentException("Unknown view type: " + shellType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TerminalData terminalData = shellList.get(position);
        String shellTextString = terminalData.getShellText();
        int shellTextColor = terminalData.getColor();


        int shellType = terminalData.getShellType();
        switch (shellType) {

            case NORMAL_SHELL:
                NormalShellViewHolder normalShellViewHolder = (NormalShellViewHolder) holder;
                normalShellViewHolder.shellText.setText(shellTextString);
                normalShellViewHolder.shellText.setTextColor(shellTextColor);
                break;
            default:
                // Handle unknown shell types gracefully
                break;
        }


    }

    @Override
    public int getItemCount() {
        return shellList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return shellList.get(position).getShellType();
    }

    // View holder for normal shell
    class NormalShellViewHolder extends RecyclerView.ViewHolder {
        TextView shellText;

        NormalShellViewHolder(@NonNull View itemView) {
            super(itemView);
            shellText = itemView.findViewById(R.id.shell_text);
        }

    }

}
