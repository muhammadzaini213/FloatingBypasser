package com.mzfreeapp.floatingbrowser.window.floatingkeyboard.helper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.mzfreeapp.floatingbrowser.R;
import com.mzfreeapp.floatingbrowser.window.webview.WebviewListener;


public class KeyboardItems {

    static WebviewListener wlistener;
    LinearLayout row_number, row_1, row_2, row_3;
    LinearLayout row_symbol, row_1_shift, row_2_shift, row_3_shift;

    public static void setWebviewListener(WebviewListener listener) {
        wlistener = listener;
    }

    /*
    Set the clickListener and listener for keyboard items,
    those input would be sent to WebViewHelper class.
     */
    public void init(ViewGroup keyboardView) {
        LinearLayout keyboard_layout = keyboardView.findViewById(R.id.keyboard_layout);

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

        clear_btn.setOnClickListener(v -> wlistener.clear());
        space_btn.setOnClickListener(v -> wlistener.space());
        enter_btn.setOnClickListener(v -> wlistener.enter());
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

}
