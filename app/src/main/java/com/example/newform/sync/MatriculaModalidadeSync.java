package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.MatriculaModalidadeAPI;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.models.RespostaModel;


public final class MatriculaModalidadeSync {

    public static Long sync(MatriculaModalidadeModel matriculaModalidade){
        RespostaModel resposta = MatriculaModalidadeAPI.postMatriculaModalidade(matriculaModalidade);
        if (resposta != null){
            Log.i(ServiceSync.LOG_ID, resposta.getMensagem());
        }
        return resposta != null ? resposta.getCodigo() : null;
    }

}
