package com.example.newform.reyclerview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.newform.R;
import com.example.newform.models.PlanoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.viewholder.PlanosViewHolder;

import java.util.List;

public class PlanosAdapter extends AdapterDefault<PlanoModel, PlanosViewHolder> {

    public PlanosAdapter(Activity activity, List<PlanoModel> list) {
        super(activity, list, null);
    }

    @NonNull
    @Override
    public PlanosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_plano, parent, false);
        return new PlanosViewHolder(view);
    }
}
