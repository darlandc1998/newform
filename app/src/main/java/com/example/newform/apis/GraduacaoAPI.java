package com.example.newform.apis;

import com.example.newform.apis.endpoints.GenericEndPoint;
import com.example.newform.apis.endpoints.GraduacaoEndPoint;
import com.example.newform.models.GraduacaoModel;
import com.example.newform.models.RespostaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class GraduacaoAPI {

    public static void getGraduacoes(Long idUsuairo, Callback<List<GraduacaoModel>> callback) {
        GraduacaoEndPoint endPoint = GenericEndPoint.retrofit.create(GraduacaoEndPoint.class);
        Call<List<GraduacaoModel>> call = endPoint.getGraduacoes(idUsuairo);
        call.enqueue(callback);
    }

    public static void getGraduacao(String nome, Long idUsuairo, Callback<GraduacaoModel> callback) {
        GraduacaoEndPoint endPoint = GenericEndPoint.retrofit.create(GraduacaoEndPoint.class);
        Call<GraduacaoModel> call = endPoint.getGraduacao(nome, idUsuairo);
        call.enqueue(callback);
    }

    public static RespostaModel postGraduacao(GraduacaoModel graduacao){
        GraduacaoEndPoint endPoint = GenericEndPoint.retrofit.create(GraduacaoEndPoint.class);
        Call<RespostaModel> call = endPoint.postGraduacao(graduacao);
        try {
            return call.execute().body();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
