package com.example.newform.adapter;

import android.app.Activity;

import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.GraduacaoModel;

import java.util.List;

public class ArrayAdapterGraduacao extends ArrayAdapterDefault<GraduacaoModel> {
    public ArrayAdapterGraduacao(Activity activity, List<GraduacaoModel> objects) {
        super(activity, objects);
    }

    public ArrayAdapterGraduacao(Activity activity, List<GraduacaoModel> objects, GenericCode.AdapterClick<GraduacaoModel> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterGraduacao(Activity activity, int resource, List<GraduacaoModel> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterGraduacao(Activity activity, int resource, List<GraduacaoModel> objects, GenericCode.AdapterClick<GraduacaoModel> click) {
        super(activity, resource, objects, click);
    }
}
