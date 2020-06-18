package com.example.newform.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.example.newform.R;

public class DialogDefault {

    public static void messageOk(Context context, String title, String message, Integer icon, String btnText, boolean tela, DialogInterface.OnClickListener click) {
        if (btnText == null) {
            btnText = context.getResources().getString(R.string.ok);
        }
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, click)
                .show();

        alertDialog.setCanceledOnTouchOutside(tela);
        alertDialog.setCancelable(tela);
    }

    public static void messageOk(Context context, String title, String message, Integer icon, String btnText, DialogInterface.OnClickListener click) {
        boolean tela = true;
        messageOk(context, title, message, icon, btnText, tela, click);
    }

    public static void dialogYesNo(Context context, String mensagem, DialogInterface.OnClickListener onClickListener) {
        dialogYesNo(context, mensagem, context.getString(R.string.sim), context.getString(R.string.nao), true, onClickListener);
    }

    public static void dialogYesNo(Context context, String mensagem, String btnPositive, String btnNegative, boolean cancelable, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(mensagem);
        builder.setPositiveButton(btnPositive, onClickListener);
        builder.setNegativeButton(btnNegative, onClickListener);

        AlertDialog alertDialog = builder.create();

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(cancelable);
        alertDialog.show();
    }

}
