package com.example.newform.reyclerview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.newform.R;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.viewholder.ModalidadesViewHolder;

import java.util.List;

public class ModalidadesAdapter extends AdapterDefault<ModalidadeModel, ModalidadesViewHolder> {

    public ModalidadesAdapter(Activity activity, List<ModalidadeModel> list) {
        super(activity, list, null);
    }

    @NonNull
    @Override
    public ModalidadesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_modalidade, parent, false);
        return new ModalidadesViewHolder(view);
    }
}
