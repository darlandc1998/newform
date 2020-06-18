package com.example.newform.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.FaturaMatriculaModel;
import com.example.newform.models.MatriculaModel;

import java.util.ArrayList;
import java.util.List;

public class FaturaMatriculaDAO extends AbstractDAO {

    public static String[]
    colunas = {
            FaturaMatriculaModel.COLUNA_ID_MATRICULA,
            FaturaMatriculaModel.COLUNA_DATA_VENCIMENTO,
            FaturaMatriculaModel.COLUNA_VALOR,
            FaturaMatriculaModel.COLUNA_DATA_PAGAMENTO,
            FaturaMatriculaModel.COLUNA_DATA_CANCELAMENTO,
            FaturaMatriculaModel.COLUNA_ATIVO,
    };

    public FaturaMatriculaDAO(Context context){
        db_helper = new DBOpenHelper(context);
    }

    private FaturaMatriculaModel cursorToStructure(final Cursor cursor) {
        FaturaMatriculaModel model = new FaturaMatriculaModel();
        model.setIdMatricula(getCursorLong(cursor, FaturaMatriculaModel.COLUNA_ID_MATRICULA));
        model.setDtVencimento(getCursorDate(cursor, FaturaMatriculaModel.COLUNA_DATA_VENCIMENTO));
        model.setValor(getCursorDouble(cursor, FaturaMatriculaModel.COLUNA_VALOR));
        model.setDtPagamento(getCursorDate(cursor, FaturaMatriculaModel.COLUNA_DATA_PAGAMENTO));
        model.setDtCancelamento(getCursorDate(cursor, FaturaMatriculaModel.COLUNA_DATA_CANCELAMENTO));
        model.setAtivo(getCursorInteger(cursor, FaturaMatriculaModel.COLUNA_ATIVO));
        return model;
    }

    public List<FaturaMatriculaModel> selectAllWithStudent(){
        List<FaturaMatriculaModel> faturas = new ArrayList<>();

        try {
            Open();

            Cursor cursor = db.rawQuery("" +
                         " SELECT " + FaturaMatriculaModel.TABELA_NOME + ".*, " + AlunoModel.TABELA_NOME + "." + AlunoModel.COLUNA_ALUNO +
                         " FROM " + FaturaMatriculaModel.TABELA_NOME +
                         " INNER JOIN " + MatriculaModel.TABELA_NOME + " ON (" + MatriculaModel.TABELA_NOME + "." + MatriculaModel.COLUNA_ID + " = " + FaturaMatriculaModel.TABELA_NOME + "." + FaturaMatriculaModel.COLUNA_ID_MATRICULA + ")" +
                         " INNER JOIN " + AlunoModel.TABELA_NOME + " ON (" + AlunoModel.TABELA_NOME + "." + AlunoModel.COLUNA_ID + " = " + MatriculaModel.TABELA_NOME + "." + MatriculaModel.COLUNA_ID_ALUNO + ")" +
                         " WHERE " + FaturaMatriculaModel.TABELA_NOME + "." + FaturaMatriculaModel.COLUNA_ATIVO + " = 1 " +
                         " ORDER BY " + FaturaMatriculaModel.TABELA_NOME + "." + FaturaMatriculaModel.COLUNA_DATA_VENCIMENTO,
            null);

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                FaturaMatriculaModel fatura = cursorToStructure(cursor);
                fatura.setAluno(getCursorString(cursor, AlunoModel.COLUNA_ALUNO));
                faturas.add(fatura);

                cursor.moveToNext();
            }

            cursor.close();
        } finally {
            Close();
        }

        return faturas;
    }

    public long insertOrReplace(final FaturaMatriculaModel model) {
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(FaturaMatriculaModel.COLUNA_ID_MATRICULA, model.getIdMatricula());
            values.put(FaturaMatriculaModel.COLUNA_DATA_VENCIMENTO, getDateAnsiFormat(model.getDtVencimento()));
            values.put(FaturaMatriculaModel.COLUNA_VALOR, model.getValor());
            values.put(FaturaMatriculaModel.COLUNA_ATIVO, 1);


            idCreated = db.replace(FaturaMatriculaModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return idCreated;
    }

}
