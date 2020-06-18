package com.example.newform.reyclerview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.newform.R;
import com.example.newform.models.FaturaMatriculaModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.viewholder.FaturasViewHolder;

import java.util.List;

public class FaturasAdapter extends AdapterDefault<FaturaMatriculaModel, FaturasViewHolder> {

    public FaturasAdapter(Activity activity, List<FaturaMatriculaModel> list) {
        super(activity, list, null);
    }

    @NonNull
    @Override
    public FaturasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fatura, parent, false);

        return new FaturasViewHolder(view);
    }
}
