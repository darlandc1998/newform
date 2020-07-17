package com.example.newform.apis;

import com.example.newform.apis.endpoints.GenericEndPoint;
import com.example.newform.apis.endpoints.MatriculaModalidadeEndPoint;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.models.RespostaModel;
import retrofit2.Call;
import retrofit2.Callback;

public class MatriculaModalidadeAPI {

    public static void getMatriculaModalidade(Long idMatricula, Long idUsuairo, Callback<MatriculaModalidadeModel> callback) {
        MatriculaModalidadeEndPoint endPoint = GenericEndPoint.retrofit.create(MatriculaModalidadeEndPoint.class);
        Call<MatriculaModalidadeModel> call = endPoint.getMatriculaMolidade(idMatricula, idUsuairo);
        call.enqueue(callback);
    }

    public static RespostaModel postMatriculaModalidade(MatriculaModalidadeModel matriculaModalidade){
        MatriculaModalidadeEndPoint endPoint = GenericEndPoint.retrofit.create(MatriculaModalidadeEndPoint.class);
        Call<RespostaModel> call = endPoint.postMatriculaModalidade(matriculaModalidade);
        try {
            return call.execute().body();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
