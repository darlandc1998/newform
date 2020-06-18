package com.example.newform.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.newform.database.DBOpenHelper;
import com.example.newform.models.AlunoModel;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends AbstractDAO {

    public static String[]
    colunas = {
            AlunoModel.COLUNA_ID,
            AlunoModel.COLUNA_ALUNO,
            AlunoModel.COLUNA_DATA_NASCIMENTO,
            AlunoModel.COLUNA_SEXO,
            AlunoModel.COLUNA_TELEFONE,
            AlunoModel.COLUNA_CELULAR,
            AlunoModel.COLUNA_EMAIL,
            AlunoModel.COLUNA_OBSERVACAO,
            AlunoModel.COLUNA_ENDERECO,
            AlunoModel.COLUNA_NUMERO,
            AlunoModel.COLUNA_COMPLEMENTO,
            AlunoModel.COLUNA_BAIRRO,
            AlunoModel.COLUNA_CIDADE,
            AlunoModel.COLUNA_ESTADO,
            AlunoModel.COLUNA_PAIS,
            AlunoModel.COLUNA_CEP,
            AlunoModel.COLUNA_ATIVO,
    };

    public AlunoDAO (Context context){
        db_helper = new DBOpenHelper(context);
    }

    private AlunoModel cursorToStructure(final Cursor cursor) {

        AlunoModel model = new AlunoModel();
        model.setId(getCursorLong(cursor, AlunoModel.COLUNA_ID));
        model.setAluno(getCursorString(cursor, AlunoModel.COLUNA_ALUNO));
        model.setDtNascimento(getCursorDate(cursor, AlunoModel.COLUNA_DATA_NASCIMENTO));
        model.setSexo(getCursorString(cursor, AlunoModel.COLUNA_SEXO));
        model.setTelefone(getCursorString(cursor, AlunoModel.COLUNA_TELEFONE));
        model.setCelular(getCursorString(cursor, AlunoModel.COLUNA_CELULAR));
        model.setEmail(getCursorString(cursor, AlunoModel.COLUNA_EMAIL));
        model.setObservacao(getCursorString(cursor, AlunoModel.COLUNA_OBSERVACAO));
        model.setEndereco(getCursorString(cursor, AlunoModel.COLUNA_ENDERECO));
        model.setNumero(getCursorString(cursor, AlunoModel.COLUNA_NUMERO));
        model.setComplemento(getCursorString(cursor, AlunoModel.COLUNA_COMPLEMENTO));
        model.setBairro(getCursorString(cursor, AlunoModel.COLUNA_BAIRRO));
        model.setCidade(getCursorString(cursor, AlunoModel.COLUNA_CIDADE));
        model.setEstado(getCursorString(cursor, AlunoModel.COLUNA_ESTADO));
        model.setPais(getCursorString(cursor, AlunoModel.COLUNA_PAIS));
        model.setCep(getCursorString(cursor, AlunoModel.COLUNA_CEP));
        model.setAtivo(getCursorInteger(cursor, AlunoModel.COLUNA_ATIVO));
        return model;
    }

    public List<AlunoModel> select(){
        List<AlunoModel> alunos = new ArrayList<>();
        try {
           Open();

            Cursor cursor = db.query(AlunoModel.TABELA_NOME, colunas, "ativo = 1", null, null, null, AlunoModel.COLUNA_ALUNO);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                alunos.add(cursorToStructure(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } finally {
            Close();
        }

        return alunos;
    }

    public long insertOrUpdate(final AlunoModel model) {
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(AlunoModel.COLUNA_ALUNO, model.getAluno());
            values.put(AlunoModel.COLUNA_DATA_NASCIMENTO, getDateAnsiFormat(model.getDtNascimento()));
            values.put(AlunoModel.COLUNA_SEXO, model.getSexo());
            values.put(AlunoModel.COLUNA_TELEFONE, model.getTelefone());
            values.put(AlunoModel.COLUNA_CELULAR, model.getCelular());
            values.put(AlunoModel.COLUNA_EMAIL, model.getEmail());
            values.put(AlunoModel.COLUNA_OBSERVACAO, model.getObservacao());
            values.put(AlunoModel.COLUNA_ENDERECO, model.getEndereco());
            values.put(AlunoModel.COLUNA_NUMERO, model.getNumero());
            values.put(AlunoModel.COLUNA_COMPLEMENTO, model.getComplemento());
            values.put(AlunoModel.COLUNA_BAIRRO, model.getBairro());
            values.put(AlunoModel.COLUNA_CIDADE, model.getCidade());
            values.put(AlunoModel.COLUNA_ESTADO, model.getEstado());
            values.put(AlunoModel.COLUNA_PAIS, model.getPais());
            values.put(AlunoModel.COLUNA_CEP, model.getCep());
            values.put(AlunoModel.COLUNA_ATIVO, 1);

            if (model.getId() != null) {
                idCreated = db.update(AlunoModel.TABELA_NOME, values, AlunoModel.COLUNA_ID + "= ?", new String[]{model.getId().toString()});
            } else {
                idCreated = db.insert(AlunoModel.TABELA_NOME, null, values);
            }
        }
        finally {
            Close();
        }

        return idCreated;
    }

    public long delete(AlunoModel model){
        long idCreated = 0;

        try  {
            Open();

            ContentValues values = new ContentValues();
            values.put(AlunoModel.COLUNA_ATIVO, 0);

            idCreated = db.update(AlunoModel.TABELA_NOME, values, AlunoModel.COLUNA_ID + "= ?", new String[]{model.getId().toString()});
        }
        finally {
            Close();
        }

        return idCreated;
    }

}
