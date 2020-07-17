package com.example.newform.apis.endpoints;

import com.example.newform.models.GraduacaoModel;
import com.example.newform.models.RespostaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GraduacaoEndPoint {

    @POST("api/graduacao/inserir")
    Call<RespostaModel> postGraduacao(@Body GraduacaoModel graduacaoModel);

    @GET("api/graduacao/buscar")
    Call<GraduacaoModel> getGraduacao(@Query("nome") final String nome, @Query("id_usuario") final Long idUsuario);

    @GET("api/graduacao/buscar")
    Call<List<GraduacaoModel>> getGraduacoes(@Query("id_usuario") final Long idUsuario);

}
