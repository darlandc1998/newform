package com.example.newform.reyclerview.viewholder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.newform.R;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.ViewHolderDefault;
import com.example.newform.utils.UtilNumber;

import java.util.List;

@SuppressLint("SetTextI18n")
public class FaturaMatriculaModalidadeViewHolder extends ViewHolderDefault<MatriculaModalidadeModel> {

    private TextView txtFaturaModalidade;
    private TextView txtFaturaPlanoValor;
    private TextView txtFaturaGraduacao;
    private TextView txtFaturaPlano;

    public FaturaMatriculaModalidadeViewHolder(View view) {
        super(view);

        txtFaturaModalidade = view.findViewById(R.id.txtFaturaModalidade);
        txtFaturaPlanoValor = view.findViewById(R.id.txtFaturaPlanoValor);
        txtFaturaGraduacao = view.findViewById(R.id.txtFaturaGraduacao);
        txtFaturaPlano = view.findViewById(R.id.txtFaturaPlano);
    }

    @Override
    public void bind(MatriculaModalidadeModel object, List<MatriculaModalidadeModel> list, AdapterDefault<MatriculaModalidadeModel, ViewHolderDefault<MatriculaModalidadeModel>> adapter, Activity activity, View.OnClickListener onClick) {
        super.bind(object, list, adapter, activity, onClick);
        txtFaturaModalidade.setText(object.getModalidade());
        txtFaturaPlanoValor.setText(activity.getString(R.string.cifrao) + UtilNumber.getDecimalFormat().format(object.getValorMensalPlano()));
        txtFaturaGraduacao.setText(object.getGraduacao());
        txtFaturaPlano.setText(object.getPlano());
    }
}
