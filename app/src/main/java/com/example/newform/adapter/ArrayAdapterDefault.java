package com.example.newform.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.newform.R;
import com.example.newform.interfaces.GenericCode;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class ArrayAdapterDefault<Classe> extends ArrayAdapter<Classe> implements Serializable {

    private Activity activity;
    private List<Classe> list;
    private int resource;
    private GenericCode.AdapterClick click;
    private GenericCode.GetViewDefault getViewCode;

    public ArrayAdapterDefault(Activity activity, List<Classe> objects) {
        this(activity, android.R.layout.simple_list_item_1, objects, null);
    }

    public ArrayAdapterDefault(Activity activity, List<Classe> objects, GenericCode.AdapterClick<Classe> click) {
        this(activity, android.R.layout.simple_list_item_1, objects, click);
    }

    public ArrayAdapterDefault(Activity activity, int resource, List<Classe> objects) {
        this(activity, resource, objects, null);
    }

    public ArrayAdapterDefault(Activity activity, int resource, List<Classe> objects, GenericCode.AdapterClick<Classe> click) {
        super(activity, resource, objects);
        this.activity   = activity;
        this.list       = objects;
        this.resource   = resource;
        this.click      = click;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        convertView.setBackgroundResource(R.drawable.selector_transparent_grey);
        convertView.setClickable(true);
        convertView.setFocusable(true);

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(getList().get(position).toString());

        final View finalConvertView = convertView;

        if (getViewCode != null) {
            getViewCode.code(position, convertView, parent);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(getList().get(position));
            }
        });

        return convertView;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<Classe> getList() {
        return list;
    }

    public void setList(List<Classe> list) {
        this.list = list;
    }

    public GenericCode.GetViewDefault getGetViewCode() {
        return getViewCode;
    }

    public void setGetViewCode(GenericCode.GetViewDefault getViewCode) {
        this.getViewCode = getViewCode;
    }
}
