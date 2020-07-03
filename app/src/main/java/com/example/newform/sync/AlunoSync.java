package com.example.newform.sync;

import android.util.Log;

import com.example.newform.apis.AlunoAPI;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.RespostaModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class AlunoSync {

    public static void sync(AlunoModel aluno){
        AlunoAPI.postAluno(aluno, new Callback<RespostaModel>() {
            @Override
            public void onResponse(Call<RespostaModel> call, Response<RespostaModel> response) {
                Log.i(ServiceSync.LOG_ID, response.body().getMensagem());
            }

            @Override
            public void onFailure(Call<RespostaModel> call, Throwable t) {}
        });
    }

}
