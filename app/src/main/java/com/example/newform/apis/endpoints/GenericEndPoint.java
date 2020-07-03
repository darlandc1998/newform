package com.example.newform.apis.endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenericEndPoint {

    private static final String URL_ROOT = "http://nextcodedev.com.br";

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
