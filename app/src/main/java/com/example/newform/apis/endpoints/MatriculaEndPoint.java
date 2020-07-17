package com.example.newform.apis.endpoints;

import com.example.newform.models.MatriculaModel;
import com.example.newform.models.RespostaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MatriculaEndPoint {

    @POST("api/matricula/inserir")
    Call<RespostaModel> postMatricula(@Body MatriculaModel matriculaModel);

    @GET("api/matricula/buscar")
    Call<MatriculaModel> getMatricula(@Query("id_aluno") final Long idAluno, @Query("id_usuario") final Long idUsuario);

    @GET("api/matricula/buscar")
    Call<List<MatriculaModel>> getMatriculas(@Query("id_usuario") final Long idUsuario);

}
