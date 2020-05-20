package com.example.upmobiletest.PostRetrofit;

import com.example.upmobiletest.API.GetDriveAdData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetDriveAdDataRetrofit {
    private static final String URL = "https://upitak.uz/api/tp-user/";
    private static GetDriveAdDataRetrofit retrofitClient;
    private Retrofit retrofit;

    private GetDriveAdDataRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized GetDriveAdDataRetrofit getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new GetDriveAdDataRetrofit();
        }
        return retrofitClient;
    }

    public GetDriveAdData getDriveAdData(){
        return retrofit.create(GetDriveAdData.class);
    }
}
