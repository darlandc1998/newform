package com.example.newform.reyclerview;

import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class ViewHolderDefault<Classe> extends RecyclerView.ViewHolder {

    private Classe object;
    private final View itemView;

    public ViewHolderDefault(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bind(Classe object, List<Classe> list, AdapterDefault<Classe, ViewHolderDefault<Classe>> adapter, Activity activity, View.OnClickListener onClick) {
        this.object = object;

        if (onClick != null) {
            getItemView().setOnClickListener(onClick);
        }
    }

    public Classe getObject() {
        return object;
    }

    public void setObject(Classe object) {
        this.object = object;
    }

    public View getItemView() {
        return itemView;
    }
}
