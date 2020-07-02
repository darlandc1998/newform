package com.example.newform.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class UtilSharedPreferences {

    private static final String KEY_PREFERENCES = "KEY_NEW_FORM";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    public static void clear(Context context){
        SharedPreferences.Editor editor = getEditor(context);
        editor.clear();
        editor.apply();
    }

    public static void putString(Context context, String key, String value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        editor.apply();
    }

    public static void putBoolean(Context context, String key, Boolean value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putInteger(Context context, String key, Integer value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putLong(Context context, String key, Long value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putLong(key, value);
        editor.apply();
    }

    public static void putFloat(Context context, String key, Float value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putFloat(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key, String def){
        return getSharedPreferences(context).getString(key, def);
    }

    public static Boolean getBoolean(Context context, String key, Boolean def){
        return getSharedPreferences(context).getBoolean(key, def);
    }

    public static Integer getInteger(Context context, String key, Integer def){
        return getSharedPreferences(context).getInt(key, def);
    }

    public static Long getLong(Context context, String key, Long def){
        return getSharedPreferences(context).getLong(key, def);
    }

    public static Float getFloat(Context context, String key, Float def){
        return getSharedPreferences(context).getFloat(key, def);
    }

}
