package com.example.newform.adapter;

import android.app.Activity;
import com.example.newform.models.CidadeModel;
import com.example.newform.interfaces.GenericCode;

import java.util.List;

public class ArrayAdapterCidade extends ArrayAdapterDefault<CidadeModel> {

    public ArrayAdapterCidade(Activity activity, List<CidadeModel> objects) {
        super(activity, objects);
    }

    public ArrayAdapterCidade(Activity activity, List<CidadeModel> objects, GenericCode.AdapterClick<CidadeModel> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterCidade(Activity activity, int resource, List<CidadeModel> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterCidade(Activity activity, int resource, List<CidadeModel> objects, GenericCode.AdapterClick<CidadeModel> click) {
        super(activity, resource, objects, click);
    }
}
