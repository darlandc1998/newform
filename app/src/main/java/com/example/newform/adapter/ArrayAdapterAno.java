package com.example.newform.adapter;

import android.app.Activity;

import com.example.newform.enums.AnoEnum;
import com.example.newform.interfaces.GenericCode;

import java.util.List;

public class ArrayAdapterAno extends ArrayAdapterDefault<AnoEnum> {

    public ArrayAdapterAno(Activity activity, List<AnoEnum> objects) {
        super(activity, objects);
    }

    public ArrayAdapterAno(Activity activity, List<AnoEnum> objects, GenericCode.AdapterClick<AnoEnum> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterAno(Activity activity, int resource, List<AnoEnum> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterAno(Activity activity, int resource, List<AnoEnum> objects, GenericCode.AdapterClick<AnoEnum> click) {
        super(activity, resource, objects, click);
    }
}
