package com.example.upmobiletest.PostRetrofit;

import com.example.upmobiletest.API.LoginAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://upitak.uz/api/tp-user/login-user
public class LoginRetrofit {
    private static final String URL = "https://upitak.uz/api/tp-user/";
    private static LoginRetrofit retrofitClient;
    private Retrofit retrofit;

    private LoginRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized LoginRetrofit getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new LoginRetrofit();
        }
        return retrofitClient;
    }

    public LoginAPI getLoginApi(){
        return retrofit.create(LoginAPI.class);
    }
}
