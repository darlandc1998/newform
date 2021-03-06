package com.example.newform.apis;

import com.example.newform.apis.endpoints.GenericEndPoint;
import com.example.newform.apis.endpoints.AlunoEndPoint;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.RespostaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class AlunoAPI {

    public static void getAlunos(Long idUsuairo, Callback<List<AlunoModel>> callback) {
        AlunoEndPoint endPoint = GenericEndPoint.retrofit.create(AlunoEndPoint.class);
        Call<List<AlunoModel>> call = endPoint.getAlunos(idUsuairo);
        call.enqueue(callback);
    }

    public static void getAluno(String nome, Long idUsuairo, Callback<AlunoModel> callback) {
        AlunoEndPoint endPoint = GenericEndPoint.retrofit.create(AlunoEndPoint.class);
        Call<AlunoModel> call = endPoint.getAluno(nome, idUsuairo);
        call.enqueue(callback);
    }

    public static RespostaModel postAluno(AlunoModel aluno){
        AlunoEndPoint endPoint = GenericEndPoint.retrofit.create(AlunoEndPoint.class);
        Call<RespostaModel> call = endPoint.postAluno(aluno);
        try {
            return call.execute().body();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
