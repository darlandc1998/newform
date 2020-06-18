package com.example.newform.reyclerview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.newform.R;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.viewholder.MatriculaModalidadeViewHolder;
import java.util.List;


public class MatriculaModalidadeAdapter extends AdapterDefault<MatriculaModalidadeModel, MatriculaModalidadeViewHolder> {


    public MatriculaModalidadeAdapter(Activity activity, List<MatriculaModalidadeModel> list) {
        super(activity, list, null);
    }

    @NonNull
    @Override
    public MatriculaModalidadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_matricula_modalidade, parent, false);

        return new MatriculaModalidadeViewHolder(view);
    }
}
