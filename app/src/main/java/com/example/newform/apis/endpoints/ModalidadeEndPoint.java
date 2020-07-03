package com.example.newform.apis.endpoints;

import com.example.newform.models.ModalidadeModel;
import com.example.newform.models.RespostaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ModalidadeEndPoint {

    @POST("api/modalidade/inserir")
    Call<RespostaModel> postModalidade(@Body ModalidadeModel modalidadeModel);

    @GET("api/modalidade/buscar")
    Call<ModalidadeModel> getModalidade(@Query("nome") final String nome, @Query("idUsuario") final Long idUsuario);

    @GET("api/modalidade/buscar")
    Call<List<ModalidadeModel>> getModalidades(@Query("idUsuario") final Long idUsuario);

}
