package com.example.newform.interfaces;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GenericCode implements Serializable {

    public interface BooleanCode {
        void booleanCode(boolean param);
    }

    public interface IntegerCode {
        void code(Integer param);
    }

    public interface LongCode {
        void code(Long param);
    }

    public interface StringCode {
        void code(String param);
    }

    public interface DateCode {
        void code(Date date);
    }

    public interface IntCode {
        void code(int param);
    }

    public interface HashMapCode {
        void code(String param1, String param2);
    }

    public interface StringDialogCode {
        void code(AlertDialog dialog, String param);
    }

    public interface DoubleDialogCode {
        void code(AlertDialog dialog, Double param);
    }

    public interface AdapterClick<T>{
        void click(T object);
    }

    public interface AdapterChildClick<T>{
        void click(View view, T object, ArrayAdapter<T> adapter);
    }

    public interface GetViewDefault<T>{
        void code(int position, View convertView, ViewGroup parent);
    }

    public interface DialogFiltro<T>{
        void code(List<T> listaNova, List<T> listaCompleta, String busca);
    }

    public interface code {
        void code();
    }

    public interface AdapterLoadResults<T> {
        List<T> code(int begin, int end, int order);
    }

}
