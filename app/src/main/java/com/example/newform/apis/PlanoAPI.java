package com.example.newform.apis;

import com.example.newform.apis.endpoints.GenericEndPoint;
import com.example.newform.apis.endpoints.PlanoEndPoint;
import com.example.newform.models.PlanoModel;
import com.example.newform.models.RespostaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PlanoAPI {

    public static void getPlanos(Long idUsuairo, Callback<List<PlanoModel>> callback) {
        PlanoEndPoint endPoint = GenericEndPoint.retrofit.create(PlanoEndPoint.class);
        Call<List<PlanoModel>> call = endPoint.getPlanos(idUsuairo);
        call.enqueue(callback);
    }

    public static void getPlano(String nome, Long idUsuairo, Callback<PlanoModel> callback) {
        PlanoEndPoint endPoint = GenericEndPoint.retrofit.create(PlanoEndPoint.class);
        Call<PlanoModel> call = endPoint.getPlano(nome, idUsuairo);
        call.enqueue(callback);
    }

    public static RespostaModel postPlano(PlanoModel plano){
        PlanoEndPoint endPoint = GenericEndPoint.retrofit.create(PlanoEndPoint.class);
        Call<RespostaModel> call = endPoint.postPlano(plano);
        try {
            return call.execute().body();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
