package com.example.newform.apis.endpoints;

import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.models.RespostaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MatriculaModalidadeEndPoint {

    @POST("api/matricula_modalidade/inserir")
    Call<RespostaModel> postMatriculaModalidade(@Body MatriculaModalidadeModel matriculaModalidadeModel);

    @GET("api/matricula_modalidade/buscar")
    Call<MatriculaModalidadeModel> getMatriculaMolidade(@Query("id_matricula") final Long idMatricula, @Query("id_usuario") final Long id_usuario);

}
