package com.example.newform.reyclerview.viewholder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.newform.R;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.ViewHolderDefault;

import java.util.List;

public class ModalidadesViewHolder extends ViewHolderDefault<ModalidadeModel> {

    private TextView txtModalidade;

    public ModalidadesViewHolder(View view) {
        super(view);

        txtModalidade = view.findViewById(R.id.txtModalidade);
    }

    @Override
    public void bind(ModalidadeModel object, List<ModalidadeModel> list, AdapterDefault<ModalidadeModel, ViewHolderDefault<ModalidadeModel>> adapter, Activity activity, View.OnClickListener onClick) {
        super.bind(object, list, adapter, activity, onClick);
        txtModalidade.setText(object.getModalidade());
    }
}
