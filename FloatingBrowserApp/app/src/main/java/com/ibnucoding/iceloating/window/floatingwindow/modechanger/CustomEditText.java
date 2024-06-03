package com.ibnucoding.iceloating.window.floatingwindow.modechanger;

import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Set long click listener
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isTextSelected()) {
                    copySelectedText();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean isTextSelected() {
        int selStart = getSelectionStart();
        int selEnd = getSelectionEnd();
        return selStart != selEnd;
    }

    private void copySelectedText() {
        int min = 0;
        int max = getText().length();
        if (isFocused()) {
            final int selStart = getSelectionStart();
            final int selEnd = getSelectionEnd();
            min = Math.max(0, Math.min(selStart, selEnd));
            max = Math.max(0, Math.max(selStart, selEnd));
        }

        CharSequence selectedText = getText().subSequence(min, max);
        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(android.content.ClipData.newPlainText("selected text", selectedText));
        Toast.makeText(getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}
