package com.example.upmobiletest.PostRetrofit;

import com.example.upmobiletest.API.AddBlankDrive;
import com.example.upmobiletest.API.RegistrationAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBlanDriveRetroFit {
    //https://upitak.uz/api/tp-blanks/create-blank
    private static final String URL = "https://upitak.uz/api/tpd-blanks/";
    private static AddBlanDriveRetroFit retrofitClient;
    private Retrofit retrofit;

    private AddBlanDriveRetroFit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized AddBlanDriveRetroFit getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new AddBlanDriveRetroFit();
        }
        return retrofitClient;
    }

    public AddBlankDrive AddBlanDriveData(){
        return retrofit.create(AddBlankDrive.class);
    }
}
