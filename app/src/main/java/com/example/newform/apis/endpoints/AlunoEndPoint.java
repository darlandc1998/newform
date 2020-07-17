package com.example.newform.apis.endpoints;

import com.example.newform.models.AlunoModel;
import com.example.newform.models.RespostaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AlunoEndPoint {

    @POST("api/aluno/inserir")
    Call<RespostaModel> postAluno(@Body AlunoModel alunoModel);

    @GET("api/aluno/buscar")
    Call<AlunoModel> getAluno(@Query("nome") final String nome, @Query("id_usuario") final Long idUsuario);

    @GET("api/aluno/buscar")
    Call<List<AlunoModel>> getAlunos(@Query("id_usuario") final Long idUsuario);

}
