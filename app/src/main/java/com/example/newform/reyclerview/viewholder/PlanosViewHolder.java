package com.example.newform.reyclerview.viewholder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.newform.R;
import com.example.newform.models.PlanoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.ViewHolderDefault;
import com.example.newform.utils.UtilNumber;

import java.util.List;

public class PlanosViewHolder extends ViewHolderDefault<PlanoModel> {

    private TextView txtPlano;
    private TextView txtPlanoValor;
    private TextView txtPlanoModalidade;

    public PlanosViewHolder(View view) {
        super(view);

        txtPlano = view.findViewById(R.id.txtPlano);
        txtPlanoValor = view.findViewById(R.id.txtPlanoValor);
        txtPlanoModalidade = view.findViewById(R.id.txtPlanoModalidade);
    }

    @Override
    public void bind(PlanoModel object, List<PlanoModel> list, AdapterDefault<PlanoModel, ViewHolderDefault<PlanoModel>> adapter, Activity activity, View.OnClickListener onClick) {
        super.bind(object, list, adapter, activity, onClick);

        txtPlano.setText(object.getPlano());
        txtPlanoValor.setText(activity.getString(R.string.cifrao) + UtilNumber.getDecimalFormat().format(object.getValorMensal()));
        txtPlanoModalidade.setText(object.getModalidade());
    }
}
