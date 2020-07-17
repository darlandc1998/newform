package com.example.newform.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.models.PlanoModel;

import java.util.ArrayList;
import java.util.List;

public class MatriculaModalidadeDAO extends AbstractDAO {

    public static String[]
    colunas = {
            MatriculaModalidadeModel.COLUNA_ID_MATRICULA,
            MatriculaModalidadeModel.COLUNA_MODALIDADE,
            MatriculaModalidadeModel.COLUNA_GRADUACAO,
            MatriculaModalidadeModel.COLUNA_DATA_INICIO,
            MatriculaModalidadeModel.COLUNA_DATA_FIM,
            MatriculaModalidadeModel.COLUNA_PLANO,
            MatriculaModalidadeModel.COLUNA_ATIVO,
            MatriculaModalidadeModel.COLUNA_ID_SERVER,
    };

    public MatriculaModalidadeDAO(Context context){
        db_helper = new DBOpenHelper(context);
    }

    private MatriculaModalidadeModel cursorToStructure(final Cursor cursor) {
        MatriculaModalidadeModel model = new MatriculaModalidadeModel();
        model.setIdMatricula(getCursorLong(cursor, MatriculaModalidadeModel.COLUNA_ID_MATRICULA));
        model.setModalidade(getCursorString(cursor, MatriculaModalidadeModel.COLUNA_MODALIDADE));
        model.setGraduacao(getCursorString(cursor, MatriculaModalidadeModel.COLUNA_GRADUACAO));
        model.setDtInicio(getCursorDate(cursor, MatriculaModalidadeModel.COLUNA_DATA_INICIO));
        model.setPlano(getCursorString(cursor, MatriculaModalidadeModel.COLUNA_PLANO));
        model.setAtivo(getCursorInteger(cursor, MatriculaModalidadeModel.COLUNA_ATIVO));
        model.setIdServer(getCursorLong(cursor, MatriculaModalidadeModel.COLUNA_ID_SERVER));
        return model;
    }

    public List<MatriculaModalidadeModel> select(){
        List<MatriculaModalidadeModel> matriculasModalidades = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(MatriculaModalidadeModel.TABELA_NOME, colunas, " ativo = 1 ", null, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                matriculasModalidades.add(cursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } finally {
            Close();
        }

        return matriculasModalidades;
    }

    public List<MatriculaModalidadeModel> selectForRegistration(String idRegistration){
        List<MatriculaModalidadeModel> modalidades = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(MatriculaModalidadeModel.TABELA_NOME, colunas, " ativo = 1 and id_matricula = ?", new String[]{ idRegistration }, null, null, null);
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

    public List<MatriculaModalidadeModel> selectForRegistrationReturningPlanValue(String idRegistration){
        List<MatriculaModalidadeModel> modalidades = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.rawQuery("" +
                    " SELECT " + MatriculaModalidadeModel.TABELA_NOME + ".*, " + PlanoModel.TABELA_NOME + "." + PlanoModel.COLUNA_VALOR_MENSAL +
                    " FROM " + MatriculaModalidadeModel.TABELA_NOME +
                    " INNER JOIN " + PlanoModel.TABELA_NOME + " ON (" + PlanoModel.TABELA_NOME + "." + PlanoModel.COLUNA_PLANO + " = " + MatriculaModalidadeModel.TABELA_NOME + "." + MatriculaModalidadeModel.COLUNA_PLANO + ")" +
                    " WHERE " + MatriculaModalidadeModel.TABELA_NOME + "." + MatriculaModalidadeModel.COLUNA_ATIVO + " = 1 AND " + PlanoModel.TABELA_NOME + "." + PlanoModel.COLUNA_ATIVO + " = 1 AND " + MatriculaModalidadeModel.COLUNA_ID_MATRICULA + " = ? " +
                    " ORDER BY " + PlanoModel.TABELA_NOME + "." + PlanoModel.COLUNA_VALOR_MENSAL, new String[]{ idRegistration });

            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                MatriculaModalidadeModel modalidadeModel = cursorToStructure(cursor);
                modalidadeModel.setValorMensalPlano(getCursorDouble(cursor, PlanoModel.COLUNA_VALOR_MENSAL));

                modalidades.add(modalidadeModel);
                cursor.moveToNext();
            }

            cursor.close();
        } finally {
            Close();
        }
        return modalidades;
    }

    public long insertOrReplace(final MatriculaModalidadeModel model) {
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(MatriculaModalidadeModel.COLUNA_ID_MATRICULA, model.getIdMatricula());
            values.put(MatriculaModalidadeModel.COLUNA_MODALIDADE, model.getModalidade());
            values.put(MatriculaModalidadeModel.COLUNA_GRADUACAO, model.getGraduacao());
            values.put(MatriculaModalidadeModel.COLUNA_DATA_INICIO, getDateAnsiFormat(model.getDtInicio()));
            values.put(MatriculaModalidadeModel.COLUNA_PLANO, model.getPlano());
            values.put(MatriculaModalidadeModel.COLUNA_ATIVO, 1);
            values.put(MatriculaModalidadeModel.COLUNA_ID_SERVER, model.getIdServer());


            idCreated = db.replace(MatriculaModalidadeModel.TABELA_NOME, null, values);

        }
        finally {
            Close();
        }

        return idCreated;
    }

    public long delete(MatriculaModalidadeModel model){
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(MatriculaModalidadeModel.COLUNA_ATIVO, 0);

            idCreated = db.update(MatriculaModalidadeModel.TABELA_NOME, values,   MatriculaModalidadeModel.COLUNA_ID_MATRICULA + " = ? and " + MatriculaModalidadeModel.COLUNA_MODALIDADE +  " = ? and " + MatriculaModalidadeModel.COLUNA_GRADUACAO + " = ? and " + MatriculaModalidadeModel.COLUNA_PLANO + " = ? ", new String[]{model.getIdMatricula().toString(), model.getModalidade(), model.getGraduacao(), model.getPlano()});
        }
        finally {
            Close();
        }

        return idCreated;
    }

    public long updateIdServer(MatriculaModalidadeModel model){
        long idUpdated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(MatriculaModalidadeModel.COLUNA_ID_SERVER, model.getIdServer());

            idUpdated = db.update(MatriculaModalidadeModel.TABELA_NOME, values,   MatriculaModalidadeModel.COLUNA_ID_MATRICULA + " = ? and " + MatriculaModalidadeModel.COLUNA_MODALIDADE +  " = ? and " + MatriculaModalidadeModel.COLUNA_GRADUACAO + " = ? and " + MatriculaModalidadeModel.COLUNA_PLANO + " = ? ", new String[]{model.getIdMatricula().toString(), model.getModalidade(), model.getGraduacao(), model.getPlano()});
        }
        finally {
            Close();
        }

        return idUpdated;
    }

}
