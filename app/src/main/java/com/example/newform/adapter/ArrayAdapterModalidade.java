package com.example.newform.adapter;

import android.app.Activity;

import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.ModalidadeModel;

import java.util.List;

public class ArrayAdapterModalidade extends ArrayAdapterDefault<ModalidadeModel> {

    public ArrayAdapterModalidade(Activity activity, List<ModalidadeModel> objects) {
        super(activity, objects);
    }

    public ArrayAdapterModalidade(Activity activity, List<ModalidadeModel> objects, GenericCode.AdapterClick<ModalidadeModel> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterModalidade(Activity activity, int resource, List<ModalidadeModel> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterModalidade(Activity activity, int resource, List<ModalidadeModel> objects, GenericCode.AdapterClick<ModalidadeModel> click) {
        super(activity, resource, objects, click);
    }
}
