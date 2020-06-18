package com.example.newform.reyclerview.viewholder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.newform.R;
import com.example.newform.models.AlunoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.ViewHolderDefault;

import java.util.List;

public class AlunosViewHolder extends ViewHolderDefault<AlunoModel> {

    private TextView txtNome;
    private TextView txtContatos;
    private TextView txtEmail;
    private TextView txtEndereco;

    public AlunosViewHolder(View view) {
        super(view);

        txtNome = view.findViewById(R.id.txtNome);
        txtContatos = view.findViewById(R.id.txtContatos);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtEndereco = view.findViewById(R.id.txtEndereco);
    }

    @Override
    public void bind(AlunoModel object, List<AlunoModel> list, AdapterDefault<AlunoModel, ViewHolderDefault<AlunoModel>> adapter, Activity activity, View.OnClickListener onClick) {
        super.bind(object, list, adapter, activity, onClick);
        
        String endereco = object.getEndereco() != null ? object.getEndereco() : "";
        String contatos = object.getTelefone() != null ? object.getTelefone() : "";
        
        if (object.getCelular() != null && !object.getCelular().trim().isEmpty()) {

            if (!contatos.trim().isEmpty()){
                contatos += activity.getString(R.string.ou);
            }

            contatos += object.getCelular();
        }

        if (object.getCidade() != null){

            if (!endereco.trim().isEmpty()){
                endereco += " - ";
            }

            endereco += object.getCidade() + " - " + object.getEstado() + " - " + object.getPais();
        }

        txtNome.setText(object.getAluno());
        txtEmail.setText(object.getEmail());
        txtEndereco.setText(endereco);
        txtContatos.setText(contatos);
    }
}
