package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.PlanoAPI;
import com.example.newform.models.PlanoModel;
import com.example.newform.models.RespostaModel;

public final class PlanoSync {

    public static Long sync(final PlanoModel plano){
        RespostaModel resposta = PlanoAPI.postPlano(plano);
        if (resposta != null){
            Log.i(ServiceSync.LOG_ID, resposta.getMensagem());
        }
        return resposta != null ? resposta.getCodigo() : null;
    }

}
