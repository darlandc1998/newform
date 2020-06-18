package com.example.newform.utils;


import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class UtilMask {
    public static String CPF_MASK       = "###.###.###-##";
    public static String CELULAR_MASK   = "(##) #### #####";
    public static String CEP_MASK       = "#####-###";
    public static String CNPJ_MASK      = "##.###.###/####-##";
    public static String RG_MASK        = "##.###.###-#";
    public static String IE_MASK        = "###.###.###.###"  ;
    public static String DATA_MASK      = "##/##/####";


    private static final String ABRE_PARENTESES = "(";
    private static final String FECHA_PARENTESES = ") ";
    private static final String SEPARADOR = "-";

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll(" ", "")
                .replaceAll(",", "");
    }

    public static boolean isASign(char c) {
        if (c == '.' || c == '-' || c == '/' || c == '(' || c == ')' || c == ',' || c == ' ') {
            return true;
        } else {
            return false;
        }
    }

    public static String mask(String mask, String text) {
        int i = 0;
        String mascara = "";
        for (char m : mask.toCharArray()) {
            if (m != '#') {
                mascara += m;
                continue;
            }
            try {
                mascara += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }

        return mascara;
    }

    public static TextWatcher insert(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = UtilMask.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                int index = 0;
                for (int i = 0; i < mask.length(); i++) {
                    char m = mask.charAt(i);
                    if (m != '#') {
                        if (index == str.length() && str.length() < old.length()) {
                            continue;
                        }
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(index);
                    } catch (Exception e) {
                        break;
                    }

                    index++;
                }

                if (mascara.length() > 0) {
                    char last_char = mascara.charAt(mascara.length() - 1);
                    boolean hadSign = false;
                    while (isASign(last_char) && str.length() == old.length()) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                        last_char = mascara.charAt(mascara.length() - 1);
                        hadSign = true;
                    }

                    if (mascara.length() > 0 && hadSign) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                    }
                }

                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void afterTextChanged(Editable s) {}
        };
    }

    public static TextWatcher insertPhone(final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = UtilMask.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                int index = 0;


                for (int i = 0; i < str.length(); i++) {
                    if ((str.length() <= 9)&&(str.charAt(0)!='0')) {
                        int position;
                        if (str.length()!=9){
                            position = 4;
                        } else {
                            position = 5;
                        }

                        if (i == position) {
                            if (index == str.length() && str.length() < old.length()) {
                                continue;
                            }
                            mascara += SEPARADOR;
                        }

                    } else if (str.length()>3) {

                        int posParentesesAb   = 0;
                        int posParentesesFech = 2;
                        int posSeparador      = 6;

                        if (str.charAt(0)=='0'){
                            posParentesesFech = 3;
                            posSeparador = 7;
                            //SetMaxLenghtEditText
                            InputFilter[] FilterArray = new InputFilter[1];
                            FilterArray[0] = new InputFilter.LengthFilter(16);
                            ediTxt.setFilters(FilterArray);
                        } else {
                            InputFilter[] FilterArray = new InputFilter[1];
                            FilterArray[0] = new InputFilter.LengthFilter(15);
                            ediTxt.setFilters(FilterArray);
                        }

                        if (str.length()==11){
                            posSeparador = 7;
                        }

                        if (str.length()==12){
                            posSeparador = 8;
                        }


                        if (i == posParentesesAb) {
                            if (index == str.length() && str.length() < old.length()) {
                                continue;
                            }
                            mascara += ABRE_PARENTESES;
                        }

                        if (i == posParentesesFech) {
                            if (index == str.length() && str.length() < old.length()) {
                                continue;
                            }
                            mascara += FECHA_PARENTESES;
                        }

                        if (i == posSeparador) {
                            if (index == str.length() && str.length() < old.length()) {
                                continue;
                            }
                            mascara += SEPARADOR;
                        }

                    }

                    try {
                        mascara += str.charAt(index);
                    } catch (Exception e) {
                        break;
                    }

                    index++;
                }


                if (mascara.length() > 0) {
                    char last_char = mascara.charAt(mascara.length() - 1);
                    boolean hadSign = false;
                    while (isASign(last_char) && str.length() == old.length()) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                        last_char = mascara.charAt(mascara.length() - 1);
                        hadSign = true;
                    }

                    if (mascara.length() > 0 && hadSign) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                    }
                }

                isUpdating = true;


                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

}