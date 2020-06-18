package com.example.newform.adapter;

import android.app.Activity;

import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.MatriculaModel;

import java.util.List;

public class ArrayAdapterMatricula extends ArrayAdapterDefault<MatriculaModel> {

    public ArrayAdapterMatricula(Activity activity, List<MatriculaModel> objects) {
        super(activity, objects);
    }

    public ArrayAdapterMatricula(Activity activity, List<MatriculaModel> objects, GenericCode.AdapterClick<MatriculaModel> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterMatricula(Activity activity, int resource, List<MatriculaModel> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterMatricula(Activity activity, int resource, List<MatriculaModel> objects, GenericCode.AdapterClick<MatriculaModel> click) {
        super(activity, resource, objects, click);
    }
}
