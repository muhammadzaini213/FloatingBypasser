package com.ibnucoding.iceloating.window.webview;

import android.webkit.WebView;
import android.widget.EditText;

import com.ibnucoding.iceloating.window.floatingkeyboard.helper.KeyboardItems;

public class WebviewKeyboardInput {
    /*
    Listens to all KeyboardView inputs and sent that to websites as an input
     */
    public void init(WebView webView, EditText searchTextField) {
        KeyboardItems.setWebviewListener(new WebviewListener() {
            @Override
            public void nbr_1() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '1'; }", null);
                } else {
                    searchTextField.append("1");
                }
            }

            @Override
            public void nbr_2() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '2'; }", null);
                } else {
                    searchTextField.append("2");
                }
            }

            @Override
            public void nbr_3() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '3'; }", null);
                } else {
                    searchTextField.append("3");
                }
            }

            @Override
            public void nbr_4() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '4'; }", null);
                } else {
                    searchTextField.append("4");
                }
            }

            @Override
            public void nbr_5() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '5'; }", null);
                } else {
                    searchTextField.append("5");
                }
            }

            @Override
            public void nbr_6() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '6'; }", null);
                } else {
                    searchTextField.append("6");
                }
            }

            @Override
            public void nbr_7() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '7'; }", null);
                } else {
                    searchTextField.append("7");
                }

            }

            @Override
            public void nbr_8() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '8'; }", null);
                } else {
                    searchTextField.append("8");
                }
            }

            @Override
            public void nbr_9() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '9'; }", null);
                } else {
                    searchTextField.append("9");
                }
            }

            @Override
            public void nbr_0() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '0'; }", null);
                } else {
                    searchTextField.append("0");
                }
            }

            @Override
            public void q_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'q'; }", null);
                } else {
                    searchTextField.append("q");
                }
            }

            @Override
            public void w_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'w'; }", null);
                } else {
                    searchTextField.append("w");
                }
            }

            @Override
            public void e_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'e'; }", null);
                } else {
                    searchTextField.append("e");
                }
            }

            @Override
            public void r_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'r'; }", null);
                } else {
                    searchTextField.append("r");
                }
            }

            @Override
            public void t_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 't'; }", null);
                } else {
                    searchTextField.append("t");
                }
            }

            @Override
            public void y_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'y'; }", null);
                } else {
                    searchTextField.append("y");
                }
            }

            @Override
            public void u_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'u'; }", null);
                } else {
                    searchTextField.append("u");
                }
            }

            @Override
            public void i_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'i'; }", null);
                } else {
                    searchTextField.append("i");
                }
            }

            @Override
            public void o_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'o'; }", null);
                } else {
                    searchTextField.append("o");
                }
            }

            @Override
            public void p_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'p'; }", null);
                } else {
                    searchTextField.append("p");
                }
            }

            @Override
            public void a_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'a'; }", null);
                } else {
                    searchTextField.append("a");
                }
            }

            @Override
            public void s_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 's'; }", null);
                } else {
                    searchTextField.append("s");
                }
            }

            @Override
            public void d_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'd'; }", null);
                } else {
                    searchTextField.append("d");
                }
            }

            @Override
            public void f_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'f'; }", null);
                } else {
                    searchTextField.append("f");
                }
            }

            @Override
            public void g_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'g'; }", null);
                } else {
                    searchTextField.append("g");
                }
            }

            @Override
            public void h_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'h'; }", null);
                } else {
                    searchTextField.append("h");
                }
            }

            @Override
            public void j_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'j'; }", null);
                } else {
                    searchTextField.append("j");
                }
            }

            @Override
            public void k_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'k'; }", null);
                } else {
                    searchTextField.append("k");
                }
            }

            @Override
            public void l_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'l'; }", null);
                } else {
                    searchTextField.append("l");
                }
            }

            @Override
            public void z_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'z'; }", null);
                } else {
                    searchTextField.append("z");
                }
            }

            @Override
            public void x_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'x'; }", null);
                } else {
                    searchTextField.append("x");
                }
            }

            @Override
            public void c_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'c'; }", null);
                } else {
                    searchTextField.append("c");
                }
            }

            @Override
            public void v_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'v'; }", null);
                } else {
                    searchTextField.append("v");
                }
            }

            @Override
            public void b_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'b'; }", null);
                } else {
                    searchTextField.append("b");
                }
            }

            @Override
            public void n_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'n'; }", null);
                } else {
                    searchTextField.append("n");
                }
            }

            @Override
            public void m_noShift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'm'; }", null);
                } else {
                    searchTextField.append("m");
                }
            }

            @Override
            public void del() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value = selectedEditableElement.value.slice(0, -1); }", null);
                } else {
                    String text = searchTextField.getText().toString();
                    if (!text.isEmpty()) {
                        // Remove the last character
                        CharSequence newText = text.subSequence(0, text.length() - 1);
                        searchTextField.setText(newText);
                    }
                }
            }

            @Override
            public void clear() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value = ''; }", null);
                } else {
                    searchTextField.setText("");
                }
            }

            @Override
            public void space() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += ' '; }", null);
                } else {
                    searchTextField.append(" ");
                }
            }

            @Override
            public void enter() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.dispatchEvent(new KeyboardEvent('keydown', {key: 'Enter'})); }", null);
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ document.querySelector('form').submit(); }", null);

                } else {
                    String url = searchTextField.getText().toString();
                    if (url.startsWith("http") || url.endsWith("com")) {
                        webView.loadUrl(url);
                    } else {
                        webView.loadUrl("https://google.com/search?q=" + url);
                    }
                }
            }

            @Override
            public void dot() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '.'; }", null);
                } else {
                    searchTextField.append(".");
                }
            }

            @Override
            public void comma() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += ','; }", null);
                } else {
                    searchTextField.append(",");
                }
            }

            @Override
            public void slash() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '/'; }", null);
                } else {
                    searchTextField.append("/");
                }
            }

            @Override
            public void question() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '?'; }", null);
                } else {
                    searchTextField.append("?");
                }
            }

            @Override
            public void at() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '@'; }", null);
                } else {
                    searchTextField.append("@");
                }
            }

            @Override
            public void bracket_open() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '('; }", null);
                } else {
                    searchTextField.append("(");
                }
            }

            @Override
            public void bracket_close() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += ')'; }", null);
                } else {
                    searchTextField.append(")");
                }
            }

            @Override
            public void cross() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'x'; }", null);
                } else {
                    searchTextField.append("x");
                }
            }

            @Override
            public void plus() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '+'; }", null);
                } else {
                    searchTextField.append("+");
                }
            }

            @Override
            public void minus() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '-'; }", null);
                } else {
                    searchTextField.append("-");
                }
            }

            @Override
            public void q_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'Q'; }", null);
                } else {
                    searchTextField.append("Q");
                }
            }

            @Override
            public void w_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'W'; }", null);
                } else {
                    searchTextField.append("W");
                }
            }

            @Override
            public void e_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'E'; }", null);
                } else {
                    searchTextField.append("E");
                }
            }

            @Override
            public void r_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'R'; }", null);
                } else {
                    searchTextField.append("R");
                }
            }

            @Override
            public void t_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'T'; }", null);
                } else {
                    searchTextField.append("T");
                }
            }

            @Override
            public void y_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'Y'; }", null);
                } else {
                    searchTextField.append("Y");
                }
            }

            @Override
            public void u_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'U'; }", null);
                } else {
                    searchTextField.append("U");
                }
            }

            @Override
            public void i_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'I'; }", null);
                } else {
                    searchTextField.append("I");
                }
            }

            @Override
            public void o_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'O'; }", null);
                } else {
                    searchTextField.append("O");
                }
            }

            @Override
            public void p_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'P'; }", null);
                } else {
                    searchTextField.append("P");
                }
            }

            @Override
            public void a_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'A'; }", null);
                } else {
                    searchTextField.append("A");
                }
            }

            @Override
            public void s_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'S'; }", null);
                } else {
                    searchTextField.append("S");
                }
            }

            @Override
            public void d_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'D'; }", null);
                } else {
                    searchTextField.append("D");
                }
            }

            @Override
            public void f_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'F'; }", null);
                } else {
                    searchTextField.append("F");
                }
            }

            @Override
            public void g_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'G'; }", null);
                } else {
                    searchTextField.append("G");
                }
            }

            @Override
            public void h_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'H'; }", null);
                } else {
                    searchTextField.append("H");
                }
            }

            @Override
            public void j_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'J'; }", null);
                } else {
                    searchTextField.append("J");
                }
            }

            @Override
            public void k_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'K'; }", null);
                } else {
                    searchTextField.append("K");
                }
            }

            @Override
            public void l_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'L'; }", null);
                } else {
                    searchTextField.append("L");
                }
            }

            @Override
            public void z_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'Z'; }", null);
                } else {
                    searchTextField.append("Z");
                }
            }

            @Override
            public void x_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'X'; }", null);
                } else {
                    searchTextField.append("X");
                }
            }

            @Override
            public void c_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'C'; }", null);
                } else {
                    searchTextField.append("C");
                }
            }

            @Override
            public void v_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'V'; }", null);
                } else {
                    searchTextField.append("V");
                }
            }

            @Override
            public void b_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'B'; }", null);
                } else {
                    searchTextField.append("B");
                }
            }

            @Override
            public void n_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'N'; }", null);
                } else {
                    searchTextField.append("N");
                }
            }

            @Override
            public void m_shift() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'M'; }", null);
                } else {
                    searchTextField.append("M");
                }
            }

            @Override
            public void sym_1() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '~'; }", null);
                } else {
                    searchTextField.append("~");
                }
            }

            @Override
            public void sym_2() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '`'; }", null);
                } else {
                    searchTextField.append("`");
                }
            }

            @Override
            public void sym_3() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '|'; }", null);
                } else {
                    searchTextField.append("|");
                }
            }

            @Override
            public void sym_4() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '•'; }", null);
                } else {
                    searchTextField.append("•");
                }
            }

            @Override
            public void sym_5() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '√'; }", null);
                } else {
                    searchTextField.append("√");
                }
            }

            @Override
            public void sym_6() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += 'π'; }", null);
                } else {
                    searchTextField.append("π");
                }
            }

            @Override
            public void sym_7() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '÷'; }", null);
                } else {
                    searchTextField.append("÷");
                }
            }

            @Override
            public void sym_8() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '∆'; }", null);
                } else {
                    searchTextField.append("∆");
                }
            }

            @Override
            public void sym_9() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '£'; }", null);
                } else {
                    searchTextField.append("£");
                }
            }

            @Override
            public void sym_10() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '¢'; }", null);
                } else {
                    searchTextField.append("¢");
                }
            }

            @Override
            public void sym_11() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '€'; }", null);
                } else {
                    searchTextField.append("€");
                }
            }

            @Override
            public void sym_12() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '¥'; }", null);
                } else {
                    searchTextField.append("¥");
                }
            }

            @Override
            public void sym_13() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '^'; }", null);
                } else {
                    searchTextField.append("^");
                }
            }

            @Override
            public void sym_14() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '°'; }", null);
                } else {
                    searchTextField.append("°");
                }
            }

            @Override
            public void sym_15() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '='; }", null);
                } else {
                    searchTextField.append("=");
                }
            }

            @Override
            public void sym_16() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '{'; }", null);
                } else {
                    searchTextField.append("{");
                }
            }

            @Override
            public void sym_17() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '}'; }", null);
                } else {
                    searchTextField.append("}");
                }
            }

            @Override
            public void sym_18() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '\\'; }", null);
                } else {
                    searchTextField.append("\\");
                }
            }

            @Override
            public void sym_19() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '%'; }", null);
                } else {
                    searchTextField.append("%");
                }
            }

            @Override
            public void sym_20() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '©'; }", null);
                } else {
                    searchTextField.append("©");
                }
            }

            @Override
            public void sym_21() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '®'; }", null);
                } else {
                    searchTextField.append("®");
                }
            }

            @Override
            public void sym_22() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '™'; }", null);
                } else {
                    searchTextField.append("™");
                }
            }

            @Override
            public void sym_23() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '✓'; }", null);
                } else {
                    searchTextField.append("✓");
                }
            }

            @Override
            public void sym_24() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '['; }", null);
                } else {
                    searchTextField.append("[");
                }
            }

            @Override
            public void sym_25() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += ']'; }", null);
                } else {
                    searchTextField.append("]");
                }
            }

            @Override
            public void sym_26() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '<'; }", null);
                } else {
                    searchTextField.append("<");
                }
            }

            @Override
            public void sym_27() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '>'; }", null);
                } else {
                    searchTextField.append(">");
                }
            }

            @Override
            public void sym_28() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '#'; }", null);
                } else {
                    searchTextField.append("#");
                }
            }

            @Override
            public void sym_29() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '$'; }", null);
                } else {
                    searchTextField.append("$");
                }
            }

            @Override
            public void sym_30() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '_'; }", null);
                } else {
                    searchTextField.append("_");
                }
            }

            @Override
            public void sym_31() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '&'; }", null);
                } else {
                    searchTextField.append("&");
                }
            }

            @Override
            public void sym_32() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '*'; }", null);
                } else {
                    searchTextField.append("*");
                }
            }

            @Override
            public void sym_33() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '\"'; }", null);
                } else {
                    searchTextField.append("\"");
                }
            }

            @Override
            public void sym_34() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '\''; }", null);
                } else {
                    searchTextField.append("\'");
                }
            }

            @Override
            public void sym_35() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += ':'; }", null);
                } else {
                    searchTextField.append(":");
                }
            }

            @Override
            public void sym_36() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += ';'; }", null);
                } else {
                    searchTextField.append(";");
                }
            }

            @Override
            public void sym_37() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '!'; }", null);
                } else {
                    searchTextField.append("!");
                }
            }

            @Override
            public void sym_38() {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '#'; }", null);
                } else {
                    searchTextField.append("#");
                }
            }

            @Override
            public void paste(String pasted) {
                if (!searchTextField.isFocused()) {
                    webView.evaluateJavascript("if (selectedEditableElement) " +
                            "{ selectedEditableElement.value += '" +
                            pasted +
                            "'; }", null);
                } else {
                    searchTextField.append(pasted);
                }
            }
        });
    }

}
