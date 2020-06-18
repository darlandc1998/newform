package com.example.newform.utils;

public final class UtilString {

    public static String charEmail(){
        return "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    }

    public static boolean validateEmail(String email){
        return !((!email.matches(charEmail())) && (!email.trim().isEmpty()));
    }

}
