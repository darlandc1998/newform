package com.example.newform.adapter;

import android.app.Activity;

import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.AlunoModel;
import java.util.List;

public class ArrayAdapterAluno extends ArrayAdapterDefault<AlunoModel> {

    public ArrayAdapterAluno(Activity activity, List<AlunoModel> objects) {
        super(activity, objects);
    }

    public ArrayAdapterAluno(Activity activity, List<AlunoModel> objects, GenericCode.AdapterClick<AlunoModel> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterAluno(Activity activity, int resource, List<AlunoModel> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterAluno(Activity activity, int resource, List<AlunoModel> objects, GenericCode.AdapterClick<AlunoModel> click) {
        super(activity, resource, objects, click);
    }

}
