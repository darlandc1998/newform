package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.ModalidadeAPI;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.models.RespostaModel;

public final class ModalidadeSync {

    public static Long sync(final ModalidadeModel modalidade){
        RespostaModel resposta = ModalidadeAPI.postModalidade(modalidade);
        if (resposta != null){
            Log.i(ServiceSync.LOG_ID, resposta.getMensagem());
        }
        return resposta != null ? resposta.getCodigo() : null;
    }

}
