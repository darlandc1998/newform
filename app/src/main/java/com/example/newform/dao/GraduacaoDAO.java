package com.example.newform.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.GraduacaoModel;
import java.util.ArrayList;
import java.util.List;

public class GraduacaoDAO extends AbstractDAO {

    public static String[]
    colunas = {
            GraduacaoModel.COLUNA_MODALIDADE,
            GraduacaoModel.COLUNA_GRADUACAO,
            GraduacaoModel.COLUNA_ATIVO
    };

    public GraduacaoDAO(Context context){
        db_helper = new DBOpenHelper(context);
    }

    private GraduacaoModel cursorToStructure(final Cursor cursor) {
        return new GraduacaoModel(getCursorString(cursor, GraduacaoModel.COLUNA_MODALIDADE), getCursorString(cursor, GraduacaoModel.COLUNA_GRADUACAO), getCursorInteger(cursor, GraduacaoModel.COLUNA_ATIVO));
    }

    public List<GraduacaoModel> selectForModality(String modality){
        List<GraduacaoModel> graduacoes = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(GraduacaoModel.TABELA_NOME, colunas, " ativo = 1 and modalidade = ? ", new String[]{ modality }, null, null, GraduacaoModel.COLUNA_GRADUACAO);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                graduacoes.add(cursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } finally {
            Close();
        }

        return graduacoes;
    }

    public long insertOrReplace(final GraduacaoModel model) {
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(GraduacaoModel.COLUNA_MODALIDADE, model.getModalidade());
            values.put(GraduacaoModel.COLUNA_GRADUACAO, model.getGraduacao());
            values.put(GraduacaoModel.COLUNA_ATIVO, 1);

            idCreated = db.replace(GraduacaoModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return idCreated;
    }

    public long delete(GraduacaoModel model){
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(GraduacaoModel.COLUNA_ATIVO, 0);

            idCreated = db.update(GraduacaoModel.TABELA_NOME, values, GraduacaoModel.COLUNA_GRADUACAO + "= ? and " + GraduacaoModel.COLUNA_MODALIDADE + " = ? ", new String[]{model.getGraduacao(), model.getModalidade()});
        }
        finally {
            Close();
        }

        return idCreated;
    }

}
