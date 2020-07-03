package com.example.newform.apis.endpoints;

import com.example.newform.models.RespostaModel;
import com.example.newform.models.UsuarioModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsuarioEndPoint {

    @POST("api/usuario/inserir")
    Call<RespostaModel> postUsuario(@Body UsuarioModel usuario);

    @GET("api/usuario/buscar")
    Call<UsuarioModel> getUsuario(@Query("nome") final String nome, @Query("senha") final String senha);

}
