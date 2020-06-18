package com.example.newform.reyclerview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.newform.R;
import com.example.newform.models.GraduacaoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.viewholder.GraduacoesViewHolder;

import java.util.List;

public class GraduacoesAdapter extends AdapterDefault<GraduacaoModel, GraduacoesViewHolder> {

    private List<GraduacaoModel> listExclude;

    public GraduacoesAdapter(Activity activity, List<GraduacaoModel> list, List<GraduacaoModel> listExclude) {
        super(activity, list, null);

        this.listExclude = listExclude;
    }

    @NonNull
    @Override
    public GraduacoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_graduacao, parent, false);
        return new GraduacoesViewHolder(view, listExclude);
    }
}
