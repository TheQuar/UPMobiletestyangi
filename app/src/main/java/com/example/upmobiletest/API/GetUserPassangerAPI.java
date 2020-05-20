package com.example.upmobiletest.API;

import com.example.upmobiletest.DB.TinyDB;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetUserPassangerAPI {

    @FormUrlEncoded
    @POST("myadv")
    Call<ResponseBody> CallGetUserBlanksAPI(
            @Field("userid") String userid
    );
}
