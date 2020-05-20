package com.example.upmobiletest.PostRetrofit;

import com.example.upmobiletest.API.AddBlankDrive;
import com.example.upmobiletest.API.AddBlankPassanger;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBlankPassangerRertofit {
    private static final String URL = "https://upitak.uz/api/tpp-blanks/";
    private static AddBlankPassangerRertofit retrofitClient;
    private Retrofit retrofit;

    private AddBlankPassangerRertofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized AddBlankPassangerRertofit getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new AddBlankPassangerRertofit();
        }
        return retrofitClient;
    }

    public AddBlankPassanger addBlankPassangerData(){
        return retrofit.create(AddBlankPassanger.class);
    }
}
