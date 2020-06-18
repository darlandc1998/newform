package com.example.newform.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class UtilNumber {

    public static DecimalFormat getDecimalFormat() {
        return new DecimalFormat("###,###,##0.00");
    }

    public static DecimalFormat getDecimalFormatInteger() {
        return new DecimalFormat("###,###,##0");
    }

    public static boolean isDouble(String s) {
        if (s == null) { return false; }
        if (s.trim().equals("")) { return false; }

        try {
            Double.parseDouble(s.trim());
            return true;
        }
        catch(NumberFormatException e) { }

        return false;
    }

    public static boolean isInteger(Double result){
        if (result % 1 == 0){
            return true;
        } else {
            return  false;
        }
    }

    public static boolean isDigit(String s) {
        return s != null && !s.trim().equals("") && s.matches("[0-9]+");
    }

    public static Double roundDouble(Double valor, int casas) {
        try{
            return new BigDecimal(valor.toString()).setScale(casas, BigDecimal.ROUND_HALF_UP).doubleValue();
        }catch (NumberFormatException e){
            return 0d;
        }
    }

    public static Double roundDouble(Double valor, int casas, int roundingMode) {
        try{
            return new BigDecimal(valor.toString()).setScale(casas, roundingMode).doubleValue();
        }catch (NumberFormatException e){
            return 0d;
        }
    }

    public static Float roundDouble(Float valor, int casas) {
        try{
            return new BigDecimal(valor.toString()).setScale(casas, BigDecimal.ROUND_HALF_UP).floatValue();
        }catch (NumberFormatException e){
            return 0f;
        }
    }

    public static Float roundDouble(Float valor, int casas, int roundingMode) {
        try{
            return new BigDecimal(valor.toString()).setScale(casas, roundingMode).floatValue();
        }catch (NumberFormatException e){
            return 0f;
        }
    }

    public static Double calcularExpressaoMatematica(String expressao) throws Exception {
        String[] expr = expressao.split(" ");
        int i = 0;
        double operLeft = Double.valueOf(expr[i++]);
        while (i < expr.length) {
            String operator = expr[i++];
            double operRight = Double.valueOf(expr[i++]);
            switch (operator) {
                case "*":
                    operLeft = operLeft * operRight;
                    break;
                case "/":
                    operLeft = operLeft / operRight;
                    break;
                case "+": case "-":
                    while (i < expr.length) {
                        String operator2 = expr[i++];
                        if (operator2.equals("+") || operator2.equals("-")) {
                            i--;
                            break;
                        }
                        if (operator2.equals("*")) {
                            operRight = operRight * Double.valueOf(expr[i++]);
                        }
                        if (operator2.equals("/")) {
                            operRight = operRight / Double.valueOf(expr[i++]);
                        }
                    }
                    if (operator.equals("+")) {
                        operLeft = operLeft + operRight;
                    }
                    else {
                        operLeft = operLeft - operRight;
                    }
                    break;
            }
        }

        if (operLeft == Double.NaN || operLeft == Double.POSITIVE_INFINITY || operLeft == Double.NEGATIVE_INFINITY) {
            operLeft = 0d;
        }

        return operLeft;
    }

    public static Double parcelaFinanciamentoJuros(double valor, double meses, double taxa) {
        taxa = taxa / 100;
        return roundDouble((taxa / (1 - (Math.pow((1 + taxa), (meses * -1))))) * valor, 2);
    }

    public static double threeGauge(double valorTotal, double valor){
        return ((valor-1)*100)/(valorTotal-1);
    }
}
