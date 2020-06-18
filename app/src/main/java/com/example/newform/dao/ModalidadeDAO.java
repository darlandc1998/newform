package com.example.newform.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.ModalidadeModel;

import java.util.ArrayList;
import java.util.List;

public class ModalidadeDAO extends AbstractDAO {

    private final String[]
            colunas = {
            ModalidadeModel.COLUNA_MODALIDADE,
            ModalidadeModel.COLUNA_ATIVO
    };

    public ModalidadeDAO (Context context) {
        db_helper = new DBOpenHelper(context);
    }

    private ModalidadeModel cursorToStructure(final Cursor cursor) {
        return new ModalidadeModel(getCursorString(cursor, ModalidadeModel.COLUNA_MODALIDADE), getCursorInteger(cursor, ModalidadeModel.COLUNA_ATIVO));
    }

    public List<ModalidadeModel> select(){
        List<ModalidadeModel> modalidades = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(ModalidadeModel.TABELA_NOME, colunas, " ativo = 1 ", null, null, null, ModalidadeModel.COLUNA_MODALIDADE);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                modalidades.add(cursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } finally {
            Close();
        }

        return modalidades;
    }


    public long insertOrReplace(final ModalidadeModel model) {
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(ModalidadeModel.COLUNA_MODALIDADE, model.getModalidade());
            values.put(ModalidadeModel.COLUNA_ATIVO, 1);

            idCreated = db.replace(ModalidadeModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return idCreated;
    }

    public long delete(ModalidadeModel model){
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(ModalidadeModel.COLUNA_ATIVO, 0);

            idCreated = db.update(ModalidadeModel.TABELA_NOME, values, ModalidadeModel.COLUNA_MODALIDADE + "= ?", new String[]{model.getModalidade()});
        }
        finally {
            Close();
        }

        return idCreated;
    }

}
