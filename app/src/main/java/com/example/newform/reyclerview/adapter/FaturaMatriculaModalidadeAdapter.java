package com.example.newform.reyclerview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.newform.R;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.viewholder.FaturaMatriculaModalidadeViewHolder;

import java.util.List;

public class FaturaMatriculaModalidadeAdapter extends AdapterDefault<MatriculaModalidadeModel, FaturaMatriculaModalidadeViewHolder> {

    public FaturaMatriculaModalidadeAdapter(Activity activity, List<MatriculaModalidadeModel> list) {
        super(activity, list, null);
    }

    @NonNull
    @Override
    public FaturaMatriculaModalidadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fatura_matricula_modalidade, parent, false);

        return new FaturaMatriculaModalidadeViewHolder(view);
    }
}
