package com.example.newform.apis;

import com.example.newform.apis.endpoints.GenericEndPoint;
import com.example.newform.apis.endpoints.UsuarioEndPoint;
import com.example.newform.models.RespostaModel;
import com.example.newform.models.UsuarioModel;

import retrofit2.Call;
import retrofit2.Callback;

public final class UsuarioAPI {

    public static void getUsuario(final String nome, final String senha, Callback<UsuarioModel> callback) {
        UsuarioEndPoint endPoint = GenericEndPoint.retrofit.create(UsuarioEndPoint.class);
        Call<UsuarioModel> call = endPoint.getUsuario(nome, senha);
        call.enqueue(callback);
    }

    public static void postUsuario(final UsuarioModel usuario, Callback<RespostaModel> callback) {
        UsuarioEndPoint endPoint = GenericEndPoint.retrofit.create(UsuarioEndPoint.class);
        Call<RespostaModel> call = endPoint.postUsuario(usuario);
        call.enqueue(callback);
    }

}
