package com.example.newform.apis.endpoints;

import com.example.newform.models.PlanoModel;
import com.example.newform.models.RespostaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PlanoEndPoint {

    @POST("api/plano/inserir")
    Call<RespostaModel> postPlano(@Body PlanoModel planoModel);

    @GET("api/plano/buscar")
    Call<PlanoModel> getPlano(@Query("nome") final String nome, @Query("id_usuario") final Long idUsuario);

    @GET("api/plano/buscar")
    Call<List<PlanoModel>> getPlanos(@Query("id_usuario") final Long idUsuario);

}
