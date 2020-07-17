package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.MatriculaAPI;
import com.example.newform.models.MatriculaModel;
import com.example.newform.models.RespostaModel;


public final class MatriculaSync {

    public static Long sync(MatriculaModel matricula){
        RespostaModel resposta = MatriculaAPI.postMatricula(matricula);
        if (resposta != null){
            Log.i(ServiceSync.LOG_ID, resposta.getMensagem());
        }
        return resposta != null ? resposta.getCodigo() : null;
    }

}
