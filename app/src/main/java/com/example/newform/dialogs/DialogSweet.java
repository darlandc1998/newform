package com.example.newform.dialogs;

import android.app.Activity;
import android.graphics.Color;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogSweet {

    public static final int PROGRESS_TYPE = SweetAlertDialog.PROGRESS_TYPE;

    public static SweetAlertDialog show(Activity context, String title, int type){
        return show(context, title, null, type);
    }

    public static SweetAlertDialog show(Activity context, String title, String color, int type){
        SweetAlertDialog dialog = new SweetAlertDialog(context, type);
        dialog.getProgressHelper().setBarColor(Color.parseColor(color == null ? "#795548" : color));
        dialog.setTitleText(title);
        dialog.setCancelable(false);
        return dialog;
    }

}
