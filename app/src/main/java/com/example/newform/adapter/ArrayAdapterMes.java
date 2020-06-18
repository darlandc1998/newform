package com.example.newform.adapter;

import android.app.Activity;

import com.example.newform.enums.MesEnum;
import com.example.newform.interfaces.GenericCode;

import java.util.List;

public class ArrayAdapterMes extends ArrayAdapterDefault<MesEnum> {

    public ArrayAdapterMes(Activity activity, List<MesEnum> objects) {
        super(activity, objects);
    }

    public ArrayAdapterMes(Activity activity, List<MesEnum> objects, GenericCode.AdapterClick<MesEnum> click) {
        super(activity, objects, click);
    }

    public ArrayAdapterMes(Activity activity, int resource, List<MesEnum> objects) {
        super(activity, resource, objects);
    }

    public ArrayAdapterMes(Activity activity, int resource, List<MesEnum> objects, GenericCode.AdapterClick<MesEnum> click) {
        super(activity, resource, objects, click);
    }

}
