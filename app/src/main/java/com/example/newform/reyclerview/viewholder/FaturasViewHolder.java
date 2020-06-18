package com.example.newform.reyclerview.viewholder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.newform.R;
import com.example.newform.models.FaturaMatriculaModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.ViewHolderDefault;
import com.example.newform.utils.UtilDate;
import com.example.newform.utils.UtilNumber;

import java.util.List;

@SuppressLint("SetTextI18n")
public class FaturasViewHolder extends ViewHolderDefault<FaturaMatriculaModel> {

    private TextView txtFaturaAluno;
    private TextView txtFaturaData;
    private TextView txtFaturaValor;

    public FaturasViewHolder(View view) {
        super(view);

        txtFaturaAluno = view.findViewById(R.id.txtFaturaAluno);
        txtFaturaData = view.findViewById(R.id.txtFaturaData);
        txtFaturaValor = view.findViewById(R.id.txtFaturaValor);
    }

    @Override
    public void bind(FaturaMatriculaModel object, List<FaturaMatriculaModel> list, AdapterDefault<FaturaMatriculaModel, ViewHolderDefault<FaturaMatriculaModel>> adapter, Activity activity, View.OnClickListener onClick) {
        super.bind(object, list, adapter, activity, onClick);
        txtFaturaAluno.setText(object.getAluno());
        txtFaturaData.setText(UtilDate.getDateFormat("dd 'de' MMMM 'de' YYYY").format(object.getDtVencimento()));
        txtFaturaValor.setText(activity.getString(R.string.cifrao) + UtilNumber.getDecimalFormat().format(object.getValor()));
    }
}
