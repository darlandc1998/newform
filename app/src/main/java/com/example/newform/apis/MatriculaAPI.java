package com.example.newform.apis;

import com.example.newform.apis.endpoints.GenericEndPoint;
import com.example.newform.apis.endpoints.MatriculaEndPoint;
import com.example.newform.models.MatriculaModel;
import com.example.newform.models.RespostaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class MatriculaAPI {

    public static void getMatriculas(Long idUsuairo, Callback<List<MatriculaModel>> callback) {
        MatriculaEndPoint endPoint = GenericEndPoint.retrofit.create(MatriculaEndPoint.class);
        Call<List<MatriculaModel>> call = endPoint.getMatriculas(idUsuairo);
        call.enqueue(callback);
    }

    public static void getMatricula(Long idAluno, Long idUsuairo, Callback<MatriculaModel> callback) {
        MatriculaEndPoint endPoint = GenericEndPoint.retrofit.create(MatriculaEndPoint.class);
        Call<MatriculaModel> call = endPoint.getMatricula(idAluno, idUsuairo);
        call.enqueue(callback);
    }

    public static RespostaModel postMatricula(MatriculaModel matricula){
        MatriculaEndPoint endPoint = GenericEndPoint.retrofit.create(MatriculaEndPoint.class);
        Call<RespostaModel> call = endPoint.postMatricula(matricula);
        try {
            return call.execute().body();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
