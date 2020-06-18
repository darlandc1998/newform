package com.example.newform.reyclerview.viewholder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.example.newform.R;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.ViewHolderDefault;
import com.example.newform.utils.UtilDate;
import java.util.List;


public class MatriculaModalidadeViewHolder extends ViewHolderDefault<MatriculaModalidadeModel> {


    private TextView txtMatriculaModalidade;
    private TextView txtMatriculaGraduacao;
    private TextView txtMatriculaDtInicio;
    private TextView txtMatriculaPlano;

    public MatriculaModalidadeViewHolder(View view) {
        super(view);

        txtMatriculaModalidade = view.findViewById(R.id.txtMatriculaModalidade);
        txtMatriculaGraduacao = view.findViewById(R.id.txtMatriculaGraduacao);
        txtMatriculaDtInicio = view.findViewById(R.id.txtMatriculaDtInicio);
        txtMatriculaPlano = view.findViewById(R.id.txtMatriculaPlano);

    }

    @Override
    public void bind(MatriculaModalidadeModel object, List<MatriculaModalidadeModel> list, AdapterDefault<MatriculaModalidadeModel, ViewHolderDefault<MatriculaModalidadeModel>> adapter, Activity activity, View.OnClickListener onClick) {
        super.bind(object, list, adapter, activity, onClick);

        txtMatriculaModalidade.setText(object.getModalidade());
        txtMatriculaGraduacao.setText(object.getGraduacao());
        txtMatriculaPlano.setText(object.getPlano());

        if (object.getDtInicio() != null){
            txtMatriculaDtInicio.setText(UtilDate.getDateFormat("dd/MM/yyyy").format(object.getDtInicio()));
        }

    }
}
