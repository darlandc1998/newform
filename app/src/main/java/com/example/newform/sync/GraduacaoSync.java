package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.GraduacaoAPI;
import com.example.newform.models.GraduacaoModel;
import com.example.newform.models.RespostaModel;

public final class GraduacaoSync {

    public static Long sync(final GraduacaoModel graduacao){
        RespostaModel resposta = GraduacaoAPI.postGraduacao(graduacao);
        if (resposta != null){
            Log.i(ServiceSync.LOG_ID, resposta.getMensagem());
        }
        return resposta != null ? resposta.getCodigo() : null;
    }

}
