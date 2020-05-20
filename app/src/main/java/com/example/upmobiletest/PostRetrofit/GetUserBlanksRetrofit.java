package com.example.upmobiletest.PostRetrofit;

import com.example.upmobiletest.API.GetAdDataPassanger;
import com.example.upmobiletest.API.GetUserPassangerAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUserBlanksRetrofit {
    private static final String URL = "https://upitak.uz/api/tp-user/";
    private static GetUserBlanksRetrofit retrofitClient;
    private Retrofit retrofit;

    private GetUserBlanksRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized GetUserBlanksRetrofit getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new GetUserBlanksRetrofit();
        }
        return retrofitClient;
    }

    public GetUserPassangerAPI GetUserBlanksRetrofit(){
        return retrofit.create(GetUserPassangerAPI.class);
    }
}
