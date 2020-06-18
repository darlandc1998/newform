package com.example.newform.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.PlanoModel;

import java.util.ArrayList;
import java.util.List;

public class PlanoDAO extends AbstractDAO {

    public static String[]
    colunas = {
            PlanoModel.COLUNA_MODALIDADE,
            PlanoModel.COLUNA_PLANO,
            PlanoModel.COLUNA_VALOR_MENSAL,
            PlanoModel.COLUNA_ATIVO,
    };

    public PlanoDAO(Context context){
        db_helper = new DBOpenHelper(context);
    }

    private PlanoModel cursorToStructure(final Cursor cursor) {
        PlanoModel model = new PlanoModel();
        model.setPlano(getCursorString(cursor, PlanoModel.COLUNA_PLANO));
        model.setValorMensal(getCursorDouble(cursor, PlanoModel.COLUNA_VALOR_MENSAL));
        model.setModalidade(getCursorString(cursor, PlanoModel.COLUNA_MODALIDADE));
        model.setAtivo(getCursorInteger(cursor, PlanoModel.COLUNA_ATIVO));
        return model;
    }

    public List<PlanoModel> select(){
        List<PlanoModel> planos = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(PlanoModel.TABELA_NOME, colunas, "ativo = 1", null, null, null, PlanoModel.COLUNA_PLANO);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                planos.add(cursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } finally {
            Close();
        }

        return planos;
    }

    public List<PlanoModel> selectForModality(String modality){
        List<PlanoModel> planos = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(PlanoModel.TABELA_NOME, colunas, " ativo = 1 and modalidade = ?", new String[]{ modality }, null, null, PlanoModel.COLUNA_PLANO);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                planos.add(cursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } finally {
            Close();
        }

        return planos;
    }

    public long insertOrReplace(final PlanoModel model) {
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(PlanoModel.COLUNA_PLANO, model.getPlano());
            values.put(PlanoModel.COLUNA_VALOR_MENSAL, model.getValorMensal());
            values.put(PlanoModel.COLUNA_MODALIDADE, model.getModalidade());
            values.put(PlanoModel.COLUNA_ATIVO, 1);

            idCreated = db.replace(PlanoModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return idCreated;
    }

    public long delete(PlanoModel model){
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(PlanoModel.COLUNA_ATIVO, 0);

            idCreated = db.update(PlanoModel.TABELA_NOME, values, PlanoModel.COLUNA_PLANO + " = ? and " + PlanoModel.COLUNA_VALOR_MENSAL + " = ? ", new String[]{model.getPlano(), model.getValorMensal().toString()});
        }
        finally {
            Close();
        }

        return idCreated;
    }

}
