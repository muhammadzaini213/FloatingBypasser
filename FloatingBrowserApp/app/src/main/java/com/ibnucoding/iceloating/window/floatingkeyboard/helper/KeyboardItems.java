package com.ibnucoding.iceloating.window.floatingkeyboard.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.ibnucoding.iceloating.R;
import com.ibnucoding.iceloating.window.webview.Counter;
import com.ibnucoding.iceloating.window.webview.WebviewListener;


public class KeyboardItems {

    View symbols;
    static WebviewListener wlistener;
    LinearLayout row_number, row_1, row_2, row_3;
    LinearLayout row_symbol, row_1_shift, row_2_shift, row_3_shift;

    Context context;

    public static void setWebviewListener(WebviewListener listener) {
        wlistener = listener;
    }

    /*
    Set the clickListener and listener for keyboard items,
    those input would be sent to WebViewHelper class.
     */

    public void init(ViewGroup keyboardView, Context context) {
        this.context = context;
        LinearLayout keyboard_layout = keyboardView.findViewById(R.id.keyboard_layout);

        symbols = keyboard_layout.findViewById(R.id.symbols);

        row_number = keyboard_layout.findViewById(R.id.row_number);
        row_1 = keyboard_layout.findViewById(R.id.row_1);
        row_2 = keyboard_layout.findViewById(R.id.row_2);
        row_3 = keyboard_layout.findViewById(R.id.row_3);

        row_symbol = keyboard_layout.findViewById(R.id.row_symbol);
        row_1_shift = keyboard_layout.findViewById(R.id.row_1_shift);
        row_2_shift = keyboard_layout.findViewById(R.id.row_2_shift);
        row_3_shift = keyboard_layout.findViewById(R.id.row_3_shift);

        LinearLayout row_4 = keyboard_layout.findViewById(R.id.row_4);

        setRowNumber(row_number);
        setRow1(row_1);
        setRow2(row_2);
        setRow3(row_3);

        setRowSymbol(row_symbol);
        setRow1Shift(row_1_shift);
        setRow2Shift(row_2_shift);
        setRow3Shift(row_3_shift);

        setRow4(row_4);

        symbolsMode();

    }



    private void setRowNumber(LinearLayout row_number) {
        AppCompatButton btn_1 = row_number.findViewById(R.id.btn_1);
        AppCompatButton btn_2 = row_number.findViewById(R.id.btn_2);
        AppCompatButton btn_3 = row_number.findViewById(R.id.btn_3);
        AppCompatButton btn_4 = row_number.findViewById(R.id.btn_4);
        AppCompatButton btn_5 = row_number.findViewById(R.id.btn_5);
        AppCompatButton btn_6 = row_number.findViewById(R.id.btn_6);
        AppCompatButton btn_7 = row_number.findViewById(R.id.btn_7);
        AppCompatButton btn_8 = row_number.findViewById(R.id.btn_8);
        AppCompatButton btn_9 = row_number.findViewById(R.id.btn_9);
        AppCompatButton btn_0 = row_number.findViewById(R.id.btn_0);

        btn_1.setOnClickListener(v -> wlistener.nbr_1());
        btn_2.setOnClickListener(v -> wlistener.nbr_2());
        btn_3.setOnClickListener(v -> wlistener.nbr_3());
        btn_4.setOnClickListener(v -> wlistener.nbr_4());
        btn_5.setOnClickListener(v -> wlistener.nbr_5());
        btn_6.setOnClickListener(v -> wlistener.nbr_6());
        btn_7.setOnClickListener(v -> wlistener.nbr_7());
        btn_8.setOnClickListener(v -> wlistener.nbr_8());
        btn_9.setOnClickListener(v -> wlistener.nbr_9());
        btn_0.setOnClickListener(v -> wlistener.nbr_0());
    }

    private void setRow1(LinearLayout row_1) {
        AppCompatButton q_btn = row_1.findViewById(R.id.q_btn);
        AppCompatButton w_btn = row_1.findViewById(R.id.w_btn);
        AppCompatButton e_btn = row_1.findViewById(R.id.e_btn);
        AppCompatButton r_btn = row_1.findViewById(R.id.r_btn);
        AppCompatButton t_btn = row_1.findViewById(R.id.t_btn);
        AppCompatButton y_btn = row_1.findViewById(R.id.y_btn);
        AppCompatButton u_btn = row_1.findViewById(R.id.u_btn);
        AppCompatButton i_btn = row_1.findViewById(R.id.i_btn);
        AppCompatButton o_btn = row_1.findViewById(R.id.o_btn);
        AppCompatButton p_btn = row_1.findViewById(R.id.p_btn);

        q_btn.setOnClickListener(v -> wlistener.q_noShift());
        w_btn.setOnClickListener(v -> wlistener.w_noShift());
        e_btn.setOnClickListener(v -> wlistener.e_noShift());
        r_btn.setOnClickListener(v -> wlistener.r_noShift());
        t_btn.setOnClickListener(v -> wlistener.t_noShift());
        y_btn.setOnClickListener(v -> wlistener.y_noShift());
        u_btn.setOnClickListener(v -> wlistener.u_noShift());
        i_btn.setOnClickListener(v -> wlistener.i_noShift());
        o_btn.setOnClickListener(v -> wlistener.o_noShift());
        p_btn.setOnClickListener(v -> wlistener.p_noShift());

    }

    private void setRow2(LinearLayout row_2) {
        AppCompatButton a_btn = row_2.findViewById(R.id.a_btn);
        AppCompatButton s_btn = row_2.findViewById(R.id.s_btn);
        AppCompatButton d_btn = row_2.findViewById(R.id.d_btn);
        AppCompatButton f_btn = row_2.findViewById(R.id.f_btn);
        AppCompatButton g_btn = row_2.findViewById(R.id.g_btn);
        AppCompatButton h_btn = row_2.findViewById(R.id.h_btn);
        AppCompatButton j_btn = row_2.findViewById(R.id.j_btn);
        AppCompatButton k_btn = row_2.findViewById(R.id.k_btn);
        AppCompatButton l_btn = row_2.findViewById(R.id.l_btn);

        a_btn.setOnClickListener(v -> wlistener.a_noShift());
        s_btn.setOnClickListener(v -> wlistener.s_noShift());
        d_btn.setOnClickListener(v -> wlistener.d_noShift());
        f_btn.setOnClickListener(v -> wlistener.f_noShift());
        g_btn.setOnClickListener(v -> wlistener.g_noShift());
        h_btn.setOnClickListener(v -> wlistener.h_noShift());
        j_btn.setOnClickListener(v -> wlistener.j_noShift());
        k_btn.setOnClickListener(v -> wlistener.k_noShift());
        l_btn.setOnClickListener(v -> wlistener.l_noShift());
    }

    private void setRow3(LinearLayout row_3) {
        AppCompatButton shift_btn = row_3.findViewById(R.id.shift_btn);
        AppCompatButton z_btn = row_3.findViewById(R.id.z_btn);
        AppCompatButton x_btn = row_3.findViewById(R.id.x_btn);
        AppCompatButton c_btn = row_3.findViewById(R.id.c_btn);
        AppCompatButton v_btn = row_3.findViewById(R.id.v_btn);
        AppCompatButton b_btn = row_3.findViewById(R.id.b_btn);
        AppCompatButton n_btn = row_3.findViewById(R.id.n_btn);
        AppCompatButton m_btn = row_3.findViewById(R.id.m_btn);
        AppCompatButton del_btn = row_3.findViewById(R.id.del_btn);

        shift_btn.setOnClickListener(v -> {
            shiftMode();
        });
        z_btn.setOnClickListener(v -> wlistener.z_noShift());
        x_btn.setOnClickListener(v -> wlistener.x_noShift());
        c_btn.setOnClickListener(v -> wlistener.c_noShift());
        v_btn.setOnClickListener(v -> wlistener.v_noShift());
        b_btn.setOnClickListener(v -> wlistener.b_noShift());
        n_btn.setOnClickListener(v -> wlistener.n_noShift());
        m_btn.setOnClickListener(v -> wlistener.m_noShift());
        del_btn.setOnClickListener(v -> wlistener.del());

    }

    private void setRowSymbol(LinearLayout row_symbol) {
        AppCompatButton btn_dot = row_symbol.findViewById(R.id.btn_dot);
        AppCompatButton btn_comma = row_symbol.findViewById(R.id.btn_comma);
        AppCompatButton btn_slash = row_symbol.findViewById(R.id.btn_slash);
        AppCompatButton btn_question = row_symbol.findViewById(R.id.btn_question);
        AppCompatButton btn_at = row_symbol.findViewById(R.id.btn_at);
        AppCompatButton btn_bracket_open = row_symbol.findViewById(R.id.btn_bracket_open);
        AppCompatButton btn_bracket_close = row_symbol.findViewById(R.id.btn_bracket_close);
        AppCompatButton btn_cross = row_symbol.findViewById(R.id.btn_cross);
        AppCompatButton btn_plus = row_symbol.findViewById(R.id.btn_plus);
        AppCompatButton btn_minus = row_symbol.findViewById(R.id.btn_minus);

        btn_dot.setOnClickListener(v -> wlistener.dot());
        btn_comma.setOnClickListener(v -> wlistener.comma());
        btn_slash.setOnClickListener(v -> wlistener.slash());
        btn_question.setOnClickListener(v -> wlistener.question());
        btn_at.setOnClickListener(v -> wlistener.at());
        btn_bracket_open.setOnClickListener(v -> wlistener.bracket_open());
        btn_bracket_close.setOnClickListener(v -> wlistener.bracket_close());
        btn_cross.setOnClickListener(v -> wlistener.cross());
        btn_plus.setOnClickListener(v -> wlistener.plus());
        btn_minus.setOnClickListener(v -> wlistener.minus());

    }

    private void setRow1Shift(LinearLayout row_1_shift) {
        AppCompatButton q_btn_shift = row_1_shift.findViewById(R.id.q_btn_shift);
        AppCompatButton w_btn_shift = row_1_shift.findViewById(R.id.w_btn_shift);
        AppCompatButton e_btn_shift = row_1_shift.findViewById(R.id.e_btn_shift);
        AppCompatButton r_btn_shift = row_1_shift.findViewById(R.id.r_btn_shift);
        AppCompatButton t_btn_shift = row_1_shift.findViewById(R.id.t_btn_shift);
        AppCompatButton y_btn_shift = row_1_shift.findViewById(R.id.y_btn_shift);
        AppCompatButton u_btn_shift = row_1_shift.findViewById(R.id.u_btn_shift);
        AppCompatButton i_btn_shift = row_1_shift.findViewById(R.id.i_btn_shift);
        AppCompatButton o_btn_shift = row_1_shift.findViewById(R.id.o_btn_shift);
        AppCompatButton p_btn_shift = row_1_shift.findViewById(R.id.p_btn_shift);

        q_btn_shift.setOnClickListener(v -> wlistener.q_shift());
        w_btn_shift.setOnClickListener(v -> wlistener.w_shift());
        e_btn_shift.setOnClickListener(v -> wlistener.e_shift());
        r_btn_shift.setOnClickListener(v -> wlistener.r_shift());
        t_btn_shift.setOnClickListener(v -> wlistener.t_shift());
        y_btn_shift.setOnClickListener(v -> wlistener.y_shift());
        u_btn_shift.setOnClickListener(v -> wlistener.u_shift());
        i_btn_shift.setOnClickListener(v -> wlistener.i_shift());
        o_btn_shift.setOnClickListener(v -> wlistener.o_shift());
        p_btn_shift.setOnClickListener(v -> wlistener.p_shift());
    }

    private void setRow2Shift(LinearLayout row_2_shift) {
        AppCompatButton a_btn_shift = row_2_shift.findViewById(R.id.a_btn_shift);
        AppCompatButton s_btn_shift = row_2_shift.findViewById(R.id.s_btn_shift);
        AppCompatButton d_btn_shift = row_2_shift.findViewById(R.id.d_btn_shift);
        AppCompatButton f_btn_shift = row_2_shift.findViewById(R.id.f_btn_shift);
        AppCompatButton g_btn_shift = row_2_shift.findViewById(R.id.g_btn_shift);
        AppCompatButton h_btn_shift = row_2_shift.findViewById(R.id.h_btn_shift);
        AppCompatButton j_btn_shift = row_2_shift.findViewById(R.id.j_btn_shift);
        AppCompatButton k_btn_shift = row_2_shift.findViewById(R.id.k_btn_shift);
        AppCompatButton l_btn_shift = row_2_shift.findViewById(R.id.l_btn_shift);

        a_btn_shift.setOnClickListener(v -> wlistener.a_shift());
        s_btn_shift.setOnClickListener(v -> wlistener.s_shift());
        d_btn_shift.setOnClickListener(v -> wlistener.d_shift());
        f_btn_shift.setOnClickListener(v -> wlistener.f_shift());
        g_btn_shift.setOnClickListener(v -> wlistener.g_shift());
        h_btn_shift.setOnClickListener(v -> wlistener.h_shift());
        j_btn_shift.setOnClickListener(v -> wlistener.j_shift());
        k_btn_shift.setOnClickListener(v -> wlistener.k_shift());
        l_btn_shift.setOnClickListener(v -> wlistener.l_shift());
    }

    private void setRow3Shift(LinearLayout row_3_shift) {
        AppCompatButton shrink_btn = row_3_shift.findViewById(R.id.btn_shrink);
        AppCompatButton z_btn_shift = row_3_shift.findViewById(R.id.z_btn_shift);
        AppCompatButton x_btn_shift = row_3_shift.findViewById(R.id.x_btn_shift);
        AppCompatButton c_btn_shift = row_3_shift.findViewById(R.id.c_btn_shift);
        AppCompatButton v_btn_shift = row_3_shift.findViewById(R.id.v_btn_shift);
        AppCompatButton b_btn_shift = row_3_shift.findViewById(R.id.b_btn_shift);
        AppCompatButton n_btn_shift = row_3_shift.findViewById(R.id.n_btn_shift);
        AppCompatButton m_btn_shift = row_3_shift.findViewById(R.id.m_btn_shift);
        AppCompatButton del_btn_shift = row_3_shift.findViewById(R.id.del_btn_shift);

        shrink_btn.setOnClickListener(v -> {
            nonShiftMode();
        });
        z_btn_shift.setOnClickListener(v -> wlistener.z_shift());
        x_btn_shift.setOnClickListener(v -> wlistener.x_shift());
        c_btn_shift.setOnClickListener(v -> wlistener.c_shift());
        v_btn_shift.setOnClickListener(v -> wlistener.v_shift());
        b_btn_shift.setOnClickListener(v -> wlistener.b_shift());
        n_btn_shift.setOnClickListener(v -> wlistener.n_shift());
        m_btn_shift.setOnClickListener(v -> wlistener.m_shift());
        del_btn_shift.setOnClickListener(v -> wlistener.del());
    }

    private void setRow4(LinearLayout row_4) {
        AppCompatButton clear_btn = row_4.findViewById(R.id.clear_btn);
        AppCompatButton space_btn = row_4.findViewById(R.id.space_btn);
        AppCompatButton enter_btn = row_4.findViewById(R.id.enter_btn);
        AppCompatButton symbol_btn = row_4.findViewById(R.id.open_symbol_btn);
        AppCompatButton alphabet_btn = row_4.findViewById(R.id.open_alphabet_btn);
        AppCompatButton paste_btn = row_4.findViewById(R.id.paste_btn);

        clear_btn.setOnClickListener(v -> wlistener.clear());
        space_btn.setOnClickListener(v -> wlistener.space());
        enter_btn.setOnClickListener(v -> wlistener.enter());
        symbol_btn.setOnClickListener(view -> {
            openSymbol();
            symbol_btn.setVisibility(View.GONE);
            alphabet_btn.setVisibility(View.VISIBLE);
        });

        alphabet_btn.setOnClickListener(view -> {
            openAlphabet();
            symbol_btn.setVisibility(View.VISIBLE);
            alphabet_btn.setVisibility(View.GONE);
        });

        paste_btn.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

            if(counter < 7){
                Counter.addCount();
                counter = Counter.getCounter();
            } else {
                Toast.makeText(context, "Gunakan aplikasi premium untuk salinan yang tak terbatas!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (clipboard.hasPrimaryClip()) {
                ClipData clip = clipboard.getPrimaryClip();
                if (clip != null && clip.getItemCount() > 0) {
                    ClipData.Item item = clip.getItemAt(0);
                    CharSequence pasteData = item.getText();
                    if (pasteData != null) {
                        wlistener.paste(pasteData.toString());
                    }
                }
            }
            else {
                Toast.makeText(context, "Clipboard kosong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    int counter = 0;

    private void openSymbol() {
        symbols.setVisibility(View.VISIBLE);

        row_number.setVisibility(View.GONE);
        row_1.setVisibility(View.GONE);
        row_2.setVisibility(View.GONE);
        row_3.setVisibility(View.GONE);

        row_symbol.setVisibility(View.GONE);
        row_1_shift.setVisibility(View.GONE);
        row_2_shift.setVisibility(View.GONE);
        row_3_shift.setVisibility(View.GONE);
    }

    private void openAlphabet() {
        symbols.setVisibility(View.GONE);

        row_number.setVisibility(View.VISIBLE);
        row_1.setVisibility(View.VISIBLE);
        row_2.setVisibility(View.VISIBLE);
        row_3.setVisibility(View.VISIBLE);

        row_symbol.setVisibility(View.GONE);
        row_1_shift.setVisibility(View.GONE);
        row_2_shift.setVisibility(View.GONE);
        row_3_shift.setVisibility(View.GONE);
    }
    private void shiftMode() {
        row_number.setVisibility(View.GONE);
        row_1.setVisibility(View.GONE);
        row_2.setVisibility(View.GONE);
        row_3.setVisibility(View.GONE);

        row_symbol.setVisibility(View.VISIBLE);
        row_1_shift.setVisibility(View.VISIBLE);
        row_2_shift.setVisibility(View.VISIBLE);
        row_3_shift.setVisibility(View.VISIBLE);

    }

    private void nonShiftMode() {
        row_number.setVisibility(View.VISIBLE);
        row_1.setVisibility(View.VISIBLE);
        row_2.setVisibility(View.VISIBLE);
        row_3.setVisibility(View.VISIBLE);

        row_symbol.setVisibility(View.GONE);
        row_1_shift.setVisibility(View.GONE);
        row_2_shift.setVisibility(View.GONE);
        row_3_shift.setVisibility(View.GONE);
    }

    private void symbolsMode() {
        symbols.findViewById(R.id.btn_sym_1).setOnClickListener(view -> wlistener.sym_1());
        symbols.findViewById(R.id.btn_sym_2).setOnClickListener(view -> wlistener.sym_2());
        symbols.findViewById(R.id.btn_sym_3).setOnClickListener(view -> wlistener.sym_3());
        symbols.findViewById(R.id.btn_sym_4).setOnClickListener(view -> wlistener.sym_4());
        symbols.findViewById(R.id.btn_sym_5).setOnClickListener(view -> wlistener.sym_5());
        symbols.findViewById(R.id.btn_sym_6).setOnClickListener(view -> wlistener.sym_6());
        symbols.findViewById(R.id.btn_sym_7).setOnClickListener(view -> wlistener.sym_7());
        symbols.findViewById(R.id.btn_sym_8).setOnClickListener(view -> wlistener.sym_8());
        symbols.findViewById(R.id.btn_sym_9).setOnClickListener(view -> wlistener.sym_9());
        symbols.findViewById(R.id.btn_sym_10).setOnClickListener(view -> wlistener.sym_10());
        symbols.findViewById(R.id.btn_sym_11).setOnClickListener(view -> wlistener.sym_11());
        symbols.findViewById(R.id.btn_sym_12).setOnClickListener(view -> wlistener.sym_12());
        symbols.findViewById(R.id.btn_sym_13).setOnClickListener(view -> wlistener.sym_13());
        symbols.findViewById(R.id.btn_sym_14).setOnClickListener(view -> wlistener.sym_14());
        symbols.findViewById(R.id.btn_sym_15).setOnClickListener(view -> wlistener.sym_15());
        symbols.findViewById(R.id.btn_sym_16).setOnClickListener(view -> wlistener.sym_16());
        symbols.findViewById(R.id.btn_sym_17).setOnClickListener(view -> wlistener.sym_17());
        symbols.findViewById(R.id.btn_sym_18).setOnClickListener(view -> wlistener.sym_18());
        symbols.findViewById(R.id.btn_sym_19).setOnClickListener(view -> wlistener.sym_19());
        symbols.findViewById(R.id.btn_sym_20).setOnClickListener(view -> wlistener.sym_20());
        symbols.findViewById(R.id.btn_sym_21).setOnClickListener(view -> wlistener.sym_21());
        symbols.findViewById(R.id.btn_sym_22).setOnClickListener(view -> wlistener.sym_22());
        symbols.findViewById(R.id.btn_sym_23).setOnClickListener(view -> wlistener.sym_23());
        symbols.findViewById(R.id.btn_sym_24).setOnClickListener(view -> wlistener.sym_24());
        symbols.findViewById(R.id.btn_sym_25).setOnClickListener(view -> wlistener.sym_25());
        symbols.findViewById(R.id.btn_sym_26).setOnClickListener(view -> wlistener.sym_26());
        symbols.findViewById(R.id.btn_sym_27).setOnClickListener(view -> wlistener.sym_27());
        symbols.findViewById(R.id.btn_sym_28).setOnClickListener(view -> wlistener.sym_28());
        symbols.findViewById(R.id.btn_sym_29).setOnClickListener(view -> wlistener.sym_29());
        symbols.findViewById(R.id.btn_sym_30).setOnClickListener(view -> wlistener.sym_30());
        symbols.findViewById(R.id.btn_sym_31).setOnClickListener(view -> wlistener.sym_31());
        symbols.findViewById(R.id.btn_sym_32).setOnClickListener(view -> wlistener.sym_32());
        symbols.findViewById(R.id.btn_sym_33).setOnClickListener(view -> wlistener.sym_33());
        symbols.findViewById(R.id.btn_sym_34).setOnClickListener(view -> wlistener.sym_34());
        symbols.findViewById(R.id.btn_sym_35).setOnClickListener(view -> wlistener.sym_35());
        symbols.findViewById(R.id.btn_sym_36).setOnClickListener(view -> wlistener.sym_36());
        symbols.findViewById(R.id.btn_sym_37).setOnClickListener(view -> wlistener.sym_37());
        symbols.findViewById(R.id.btn_sym_38).setOnClickListener(view -> wlistener.sym_38());

    }

}
