package com.example.upmobiletest.PostRetrofit;

import com.example.upmobiletest.API.RegistrationAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationRetrofit {
    //https://upitak.uz/api/tp-user/create-user
    private static final String URL = "https://upitak.uz/api/tp-user/";
    private static RegistrationRetrofit retrofitClient;
    private Retrofit retrofit;

    private RegistrationRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RegistrationRetrofit getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RegistrationRetrofit();
        }
        return retrofitClient;
    }

    public RegistrationAPI getRegistrationAPI(){
        return retrofit.create(RegistrationAPI.class);
    }
}
