package com.ibnucoding.iceloating.freezer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.home.HomeActivity;

public class FreezerFragment extends Fragment {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freezer_edit, container, false);
        view.findViewById(R.id.exit_button).setOnClickListener(v -> HomeActivity.hListener.dashboardFragment());

        EditText text_freezer_edit = view.findViewById(R.id.text_freezer);

        sp = requireActivity().getSharedPreferences("FLOATING_BROWSER", Activity.MODE_PRIVATE);
        editor = sp.edit();

        loadText(text_freezer_edit);

        view.findViewById(R.id.save_button).setOnClickListener(view1 -> {
            String text = text_freezer_edit.getText().toString();

            if (!text.isEmpty()) {
                editor.putString("FREEZER_TEXT", text).apply();
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
            } else {
                editor.putString("FREEZER_TEXT", "").apply();

            }
        });

        return view;
    }

    private void loadText(EditText textFreezerEdit) {
        textFreezerEdit.setText(sp.getString("FREEZER_TEXT", ""));
    }


}
