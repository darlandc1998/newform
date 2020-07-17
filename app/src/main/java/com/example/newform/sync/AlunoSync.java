package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.AlunoAPI;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.RespostaModel;

public final class AlunoSync {

    public static Long sync(AlunoModel aluno){
        RespostaModel resposta = AlunoAPI.postAluno(aluno);

        if (resposta != null){
            Log.i(ServiceSync.LOG_ID, resposta.getMensagem());
        }

        return resposta != null ? resposta.getCodigo() : null;
    }

}
