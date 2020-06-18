package com.example.newform.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.MatriculaModel;
import com.example.newform.utils.UtilDate;

import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO extends AbstractDAO {

    public static String[]
    colunas = {
            MatriculaModel.COLUNA_ID,
            MatriculaModel.COLUNA_ID_ALUNO,
            MatriculaModel.COLUNA_DATA_MATRICULA,
            MatriculaModel.COLUNA_DIA_VENCIMENTO,
            MatriculaModel.COLUNA_DATA_ENCERRAMENTO,
            MatriculaModel.COLUNA_ATIVO,
    };

    public MatriculaDAO(Context context){
        db_helper = new DBOpenHelper(context);
    }

    private MatriculaModel cursorToStructure(final Cursor cursor) {
        MatriculaModel model = new MatriculaModel();
        model.setId(getCursorLong(cursor, MatriculaModel.COLUNA_ID));
        model.setIdAluno(getCursorLong(cursor, MatriculaModel.COLUNA_ID_ALUNO));
        model.setDiaVencimento(getCursorInteger(cursor, MatriculaModel.COLUNA_DIA_VENCIMENTO));
        model.setDtMatricula(getCursorDate(cursor, MatriculaModel.COLUNA_DATA_MATRICULA));
        model.setDtEncerramento(getCursorDate(cursor, MatriculaModel.COLUNA_DATA_ENCERRAMENTO));
        model.setAtivo(getCursorInteger(cursor, MatriculaModel.COLUNA_ATIVO));
        return model;
    }

    public List<MatriculaModel> selectWithStudentName(){
        List<MatriculaModel> matriculas = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.rawQuery("" +
                    " SELECT  matriculas.*, alunos." + AlunoModel.COLUNA_ALUNO +
                    " FROM " + MatriculaModel.TABELA_NOME + " AS matriculas " +
                    " INNER JOIN " + AlunoModel.TABELA_NOME + " AS alunos ON (alunos." + AlunoModel.COLUNA_ID + " = matriculas." + MatriculaModel.COLUNA_ID_ALUNO +")" +
                    " WHERE matriculas." + MatriculaModel.COLUNA_ATIVO + " = 1 " +
                    " ORDER BY alunos." + AlunoModel.COLUNA_ALUNO, null);

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                MatriculaModel matricula = cursorToStructure(cursor);
                matricula.setAluno(getCursorString(cursor, AlunoModel.COLUNA_ALUNO));

                matriculas.add(matricula);

                cursor.moveToNext();
            }

            cursor.close();
        } finally {
            Close();
        }
        return matriculas;
    }

    public MatriculaModel getObjectFromStudent(String idStudent){
        MatriculaModel matriculaModel = null;
        try {
            Open();

            Cursor cursor = db.query(MatriculaModel.TABELA_NOME, colunas, " ativo = 1 and id_aluno = ?", new String[]{ idStudent }, null, null, null);

            if (cursor.moveToFirst()) {
                matriculaModel = cursorToStructure(cursor);
            }

            cursor.close();
        } finally {
            Close();
        }

        return matriculaModel;
    }

    public long insertOrReplace(final MatriculaModel model) {
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(MatriculaModel.COLUNA_ID, model.getId());
            values.put(MatriculaModel.COLUNA_ID_ALUNO, model.getIdAluno());
            values.put(MatriculaModel.COLUNA_DATA_MATRICULA, getDateAnsiFormat(model.getDtMatricula()));
            values.put(MatriculaModel.COLUNA_DIA_VENCIMENTO, model.getDiaVencimento());
            values.put(MatriculaModel.COLUNA_ATIVO, 1);

            idCreated = db.replace(MatriculaModel.TABELA_NOME, null, values);

        }
        finally {
            Close();
        }

        return idCreated;
    }

    public long delete(MatriculaModel model){
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(MatriculaModel.COLUNA_ATIVO, 0);

            idCreated = db.update(MatriculaModel.TABELA_NOME, values, MatriculaModel.COLUNA_ID + "= ?", new String[]{model.getId().toString()});
        }
        finally {
            Close();
        }

        return idCreated;
    }

}
