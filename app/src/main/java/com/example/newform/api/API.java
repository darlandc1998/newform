package com.example.newform.api;

import com.example.newform.api.endpoint.UsuarioEndPoint;
import com.example.newform.models.RespostaModel;
import com.example.newform.models.UsuarioModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String URL_ROOT = "http://nextcodedev.com.br";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void getUsuario(final String nome, final String senha, Callback<UsuarioModel> callback) {
        UsuarioEndPoint endPoint = retrofit.create(UsuarioEndPoint.class);
        Call<UsuarioModel> call = endPoint.getUsuario(nome, senha);
        call.enqueue(callback);
    }

    public static void postUsuario(final UsuarioModel usuario, Callback<RespostaModel> callback) {
        UsuarioEndPoint endPoint = retrofit.create(UsuarioEndPoint.class);
        Call<RespostaModel> call = endPoint.postUsuario(usuario);
        call.enqueue(callback);
    }
}
