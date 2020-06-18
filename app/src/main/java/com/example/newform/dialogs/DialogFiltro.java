package com.example.newform.dialogs;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import com.example.newform.R;
import com.example.newform.adapter.ArrayAdapterDefault;
import com.example.newform.interfaces.GenericCode;
import java.util.ArrayList;
import java.util.List;


public class DialogFiltro<T> implements TextWatcher {

    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    private List<T> list;
    private List<T> listAll;

    private EditText edtSearch;
    private ListView listView;
    private MenuItem menuDelete;
    private GenericCode.DialogFiltro<T> searchCode;

    private Context context;

    public DialogFiltro(Context context, List<T> list) {
        this(context, list, null);
    }

    public DialogFiltro(Context context, final List<T> list, final GenericCode.DialogFiltro<T> searchCode) {
        this.context = context;
        this.list = list;
        this.listAll = new ArrayList<>(list);
        this.searchCode = searchCode;

        builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Toolbar titleBar = (Toolbar) inflater.inflate(R.layout.dialog_bar_search, null);
        titleBar.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

        edtSearch = (EditText) titleBar.findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(this);

        listView = new ListView(context);
        listView.setCacheColorHint(0);

        builder.setCustomTitle(titleBar);
        builder.setView(listView);

        menuDelete = titleBar.getMenu().add(R.string.limpar).setIcon(R.drawable.ic_cancel_white_24dp).setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                edtSearch.setText("");
                return true;
            }
        }).setVisible(false);
    }

    public void show() {
        dialog = builder.create();
        dialog.show();

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //Esse comando já é pra vir com o teclado aberto -> dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void close() {
        dialog.dismiss();
        dialog = null;
    }


    public void setAdapter(@NonNull ArrayAdapter<T> adapter) {
        listView.setAdapter(adapter);
    }


    public void setHint(@NonNull String hint) {
        edtSearch.setHint(hint);
    }

    public void setWidth(int width) {
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setHeight(int height) {
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, height);
    }

    public void setSize(Integer width, Integer height) {
        Activity activity = (Activity) context;

        if (width == null || activity.getWindow().getDecorView().getWidth() < width) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

        if (height == null || activity.getWindow().getDecorView().getHeight() < height) {
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

        dialog.getWindow().setLayout(width, height);
    }

    @Override
    public void afterTextChanged(Editable s) {
        String busca = s.toString().toLowerCase();

        list.clear();
        if (busca.equals("")) {
            list.addAll(listAll);
            menuDelete.setVisible(false);
        }
        else {
            if (searchCode != null) {
                searchCode.code(list, listAll, busca);
            }
            else {
                for (T object : listAll) {
                    if (object.toString().toLowerCase().contains(busca)) {
                        list.add(object);
                    }
                }
            }
            menuDelete.setVisible(true);
        }

        ((ArrayAdapterDefault) listView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

}
