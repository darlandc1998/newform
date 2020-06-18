package com.example.newform.dao;

import android.content.Context;
import android.database.Cursor;

import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.CidadeModel;

import java.util.ArrayList;
import java.util.List;

public class CidadeDAO extends AbstractDAO {

    private final String[]
    colunas = {
            CidadeModel.COLUNA_CIDADE,
            CidadeModel.COLUNA_ESTADO,
            CidadeModel.COLUNA_PAIS,
    };

    public CidadeDAO(final Context context){
        db_helper = new DBOpenHelper(context);
    }

    private CidadeModel cursorToStructure(final Cursor cursor) {
        CidadeModel model = new CidadeModel();
        model.setCidade(getCursorString(cursor, CidadeModel.COLUNA_CIDADE));
        model.setEstado(getCursorString(cursor, CidadeModel.COLUNA_ESTADO));
        model.setPais(getCursorString(cursor, CidadeModel.COLUNA_PAIS));
        return model;
    }

    public List<CidadeModel> selectAll(){
        List<CidadeModel> cidades = new ArrayList<>();

        try {
            Open();

            Cursor cursor = db.query(CidadeModel.TABELA_NOME, colunas, null, null, null ,null, CidadeModel.COLUNA_CIDADE);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                cidades.add(cursorToStructure(cursor));
                cursor.moveToNext();
            }

            cursor.close();
        } finally {
            Close();
        }

        return cidades;
    }

}
