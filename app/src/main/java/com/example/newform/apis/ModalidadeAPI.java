package com.example.newform.apis;

import com.example.newform.apis.endpoints.GenericEndPoint;
import com.example.newform.apis.endpoints.ModalidadeEndPoint;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.models.RespostaModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class ModalidadeAPI {

    public static void getModalidades(Long idUsuairo, Callback<List<ModalidadeModel>> callback) {
        ModalidadeEndPoint endPoint = GenericEndPoint.retrofit.create(ModalidadeEndPoint.class);
        Call<List<ModalidadeModel>> call = endPoint.getModalidades(idUsuairo);
        call.enqueue(callback);
    }

    public static void getModalidade(String nome, Long idUsuairo, Callback<ModalidadeModel> callback) {
        ModalidadeEndPoint endPoint = GenericEndPoint.retrofit.create(ModalidadeEndPoint.class);
        Call<ModalidadeModel> call = endPoint.getModalidade(nome, idUsuairo);
        call.enqueue(callback);
    }

    public static RespostaModel postModalidade(ModalidadeModel modalidade){
        ModalidadeEndPoint endPoint = GenericEndPoint.retrofit.create(ModalidadeEndPoint.class);
        Call<RespostaModel> call = endPoint.postModalidade(modalidade);
        try {
            return call.execute().body();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
