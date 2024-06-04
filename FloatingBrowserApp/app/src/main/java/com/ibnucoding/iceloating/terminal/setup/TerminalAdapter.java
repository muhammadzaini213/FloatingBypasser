package com.ibnucoding.iceloating.terminal.setup;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibnucoding.iceloating.R;

import java.util.List;

public class TerminalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL_SHELL = 1;
    private final List<TerminalData> shellList;
    Context context;

    public TerminalAdapter(List<TerminalData> shellList, Context context) {
        this.shellList = shellList;
        this.context = context;
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
                normalShellViewHolder.shellText.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        copyToClipboard(shellTextString);
                        return false;
                    }
                });
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

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
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
