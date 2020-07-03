package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.ModalidadeAPI;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.models.RespostaModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ModalidadeSync {

    public static void sync(ModalidadeModel modalidade){
        ModalidadeAPI.postModalidade(modalidade, new Callback<RespostaModel>() {
            @Override
            public void onResponse(Call<RespostaModel> call, Response<RespostaModel> response) {
                Log.i(ServiceSync.LOG_ID, response.body().getMensagem());
            }

            @Override
            public void onFailure(Call<RespostaModel> call, Throwable t) {}
        });
    }

}
