package com.example.newform.reyclerview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.newform.R;
import com.example.newform.models.AlunoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.viewholder.AlunosViewHolder;

import java.util.List;

public class AlunosAdapter extends AdapterDefault<AlunoModel, AlunosViewHolder> {

    public AlunosAdapter(Activity activity, List<AlunoModel> list) {
        super(activity, list, null);
    }

    @NonNull
    @Override
    public AlunosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_aluno, parent, false);

        return new AlunosViewHolder(view);
    }
}
