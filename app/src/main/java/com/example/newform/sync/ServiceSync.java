package com.example.newform.sync;

import android.content.Context;
import com.example.newform.dao.AlunoDAO;
import com.example.newform.dao.ModalidadeDAO;
import com.example.newform.enums.SharedEnum;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.utils.UtilSharedPreferences;

public final class ServiceSync {

    public static final String LOG_ID = "SYNC";

    public static void Sync(Context context){
        AlunoDAO alunoDAO = new AlunoDAO(context);
        ModalidadeDAO modalidadeDAO = new ModalidadeDAO(context);

        Long idUsuario = UtilSharedPreferences.getLong(context, SharedEnum.CODIGO_ALUNO.toString(), -1L);

        if (idUsuario.equals(-1L)){
            return;
        }

        //Alunos
        for (AlunoModel aluno: alunoDAO.select()){
            aluno.setIdUsuario(idUsuario);
            AlunoSync.sync(aluno);
        }

        //Modalidades
        for (ModalidadeModel modalidade: modalidadeDAO.select()){
            modalidade.setIdUsuario(idUsuario);
            ModalidadeSync.sync(modalidade);
        }

    }

}
