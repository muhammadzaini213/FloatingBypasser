package com.mzfreeapp.floatingbrowser.dashboard.setup;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mzfreeapp.floatingbrowser.R;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SWITCH_DATA = 1;
    private static final int STRING_DATA = 2;
    private static final int STATUS_DATA = 3;
    private static final int NON_CHANGEABLE_DATA = 4;
    private final List<DashboardData> dashboardList;
    private final OnItemClickListener itemClickListener;
    private final OnSwitchChangeListener switchChangeListener;
    private final List<Boolean> switchStates;

    private final OnStringEntered stringEnteredListener;

    /*This class is an adapter for recyclerview,
    you can check create new class for view holder if your want,
    but you need some modification on some part of this code.

    Luckily you can just follow what i do in those view holder class
    and get going.
     */
    public DashboardAdapter(List<DashboardData> dashboardList, List<Boolean> switchStates, OnItemClickListener itemClickListener, OnSwitchChangeListener switchChangeListener, OnStringEntered stringEnteredListener) {
        this.dashboardList = dashboardList;
        this.switchStates = switchStates;
        this.itemClickListener = itemClickListener;
        this.switchChangeListener = switchChangeListener;
        this.stringEnteredListener = stringEnteredListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        switch (viewType) {
            case SWITCH_DATA:
                view = inflater.inflate(R.layout.adapter_switch_layout, parent, false);
                return new SwitchViewHolder(view);

            case STRING_DATA:
                view = inflater.inflate(R.layout.adapter_string_layout, parent, false);
                return new StringViewHolder(view);

            case STATUS_DATA:
                view = inflater.inflate(R.layout.adapter_status_layout, parent, false);
                return new StatusViewHolder(view);

            case NON_CHANGEABLE_DATA:
                view = inflater.inflate(R.layout.adapter_non_changeable_layout, parent, false);
                return new NonChangeAbleViewHolder(view);

            default:
                throw new IllegalArgumentException("Error: Argument non found");
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DashboardData dashboardData = dashboardList.get(position);
        String dashboardTitle = dashboardData.getDashboardTitle();
        String dashboardDesc = dashboardData.getDashboardDesc();
        String stringEdittext = dashboardData.getStringEditText();
        boolean switchStatus = dashboardData.switchStatus();

        int viewType = dashboardData.getViewType();
        switch (viewType) {
            case SWITCH_DATA:
                SwitchViewHolder switchViewHolder = (SwitchViewHolder) holder;
                switchViewHolder.switch_title.setText(dashboardTitle);
                switchViewHolder.switch_description.setText(dashboardDesc);
                switchViewHolder.switch_itself.setChecked(switchStates.get(position)); // Update switch state from list

                // Set switch change listener
                switchViewHolder.switch_itself.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    switchStates.set(position, isChecked); // Update switch state in the list
                    if (switchChangeListener != null) {
                        switchChangeListener.onSwitchChange(position, isChecked);
                    }
                });

                break;

            case STRING_DATA:
                StringViewHolder stringViewHolder = (StringViewHolder) holder;
                stringViewHolder.string_title.setText(dashboardTitle);
                stringViewHolder.string_description.setText(dashboardDesc);
                stringViewHolder.string_edittext.setText(stringEdittext);


                break;

            case STATUS_DATA:
                StatusViewHolder statusViewHolder = (StatusViewHolder) holder;
                statusViewHolder.status_title.setText(dashboardTitle);
                statusViewHolder.status_description.setText(dashboardDesc);

                break;

            case NON_CHANGEABLE_DATA:
                NonChangeAbleViewHolder nonChangeAbleViewHolder = (NonChangeAbleViewHolder) holder;
                nonChangeAbleViewHolder.non_changeable_text.setText(dashboardDesc);
                break;
        }
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return dashboardList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnStringEntered {
        void onEnter(int position, String enteredString);
    }

    public interface OnSwitchChangeListener {
        void onSwitchChange(int position, boolean isChecked);
    }

    private class SwitchViewHolder extends RecyclerView.ViewHolder {

        TextView switch_title, switch_description;
        SwitchCompat switch_itself;

        public SwitchViewHolder(@NonNull View itemView) {
            super(itemView);

            ConstraintLayout switch_item = itemView.findViewById(R.id.switch_item);
            LinearLayout switch_text_layout = switch_item.findViewById(R.id.switch_text_layout);
            switch_title = switch_text_layout.findViewById(R.id.switch_title);
            switch_description = switch_text_layout.findViewById(R.id.switch_description);
            switch_itself = switch_item.findViewById(R.id.switch_itself);

        }
    }

    class StringViewHolder extends RecyclerView.ViewHolder {
        TextView string_title, string_description;
        EditText string_edittext;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            ConstraintLayout string_item = itemView.findViewById(R.id.string_item);
            LinearLayout string_text_layout = string_item.findViewById(R.id.string_text_layout);
            string_title = string_text_layout.findViewById(R.id.string_title);
            string_description = string_text_layout.findViewById(R.id.string_description);
            string_edittext = string_item.findViewById(R.id.string_edittext);

            string_edittext.setImeOptions(EditorInfo.IME_ACTION_DONE);

            string_edittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int position = getAdapterPosition();
                    stringEnteredListener.onEnter(position, string_edittext.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            string_edittext.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && stringEnteredListener != null) {
                        stringEnteredListener.onEnter(position, string_edittext.getText().toString());
                        return true; // Consume the event
                    }
                }
                return false; // Let the system handle the event
            });
        }
    }

    class StatusViewHolder extends RecyclerView.ViewHolder {
        TextView status_title, status_description;

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);
            ConstraintLayout status_item = itemView.findViewById(R.id.status_item);
            LinearLayout status_text_layout = status_item.findViewById(R.id.status_text_layout);
            status_title = status_text_layout.findViewById(R.id.status_title);
            status_description = status_text_layout.findViewById(R.id.status_description);

        }
    }

    class NonChangeAbleViewHolder extends RecyclerView.ViewHolder {
        TextView non_changeable_text;

        public NonChangeAbleViewHolder(@NonNull View itemView) {
            super(itemView);
            non_changeable_text = itemView.findViewById(R.id.non_changeable_text);
        }
    }


}



