package com.example.newform.adapter;

import android.app.Activity;

import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.PlanoModel;

import java.util.List;

public class ArrayAdapterPlano extends ArrayAdapterDefault<PlanoModel> {
    public ArrayAdapterPlano(Activity activity, List<PlanoModel> objects) {
        super(activity, objects);
    }

    public ArrayAdapterPlano(Activity activity, List<PlanoModel> objects, GenericCode.AdapterClick<PlanoModel> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterPlano(Activity activity, int resource, List<PlanoModel> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterPlano(Activity activity, int resource, List<PlanoModel> objects, GenericCode.AdapterClick<PlanoModel> click) {
        super(activity, resource, objects, click);
    }
}
