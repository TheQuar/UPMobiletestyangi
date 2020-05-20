package com.example.upmobiletest.PostRetrofit;

import com.example.upmobiletest.API.GetAdDataPassanger;
import com.example.upmobiletest.API.GetDriveAdData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAdDataPassangerRetrofit {
    private static final String URL = "https://upitak.uz/api/tp-user/";
    private static GetAdDataPassangerRetrofit retrofitClient;
    private Retrofit retrofit;

    private GetAdDataPassangerRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized GetAdDataPassangerRetrofit getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new GetAdDataPassangerRetrofit();
        }
        return retrofitClient;
    }

    public GetAdDataPassanger getAdDataPassangerRetrofit(){
        return retrofit.create(GetAdDataPassanger.class);
    }
}
