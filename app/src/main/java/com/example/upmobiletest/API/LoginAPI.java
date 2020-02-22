package com.example.upmobiletest.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {

    @FormUrlEncoded
    @POST("login-user")
    Call<ResponseBody> LoginUser(
            @Field("username") String userLoginNamePost,
            @Field("password") String userPasswordPost
    );
}
