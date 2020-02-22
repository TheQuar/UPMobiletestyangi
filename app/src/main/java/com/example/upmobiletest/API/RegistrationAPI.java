package com.example.upmobiletest.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistrationAPI {
    //https://upitak.uz/api/tp-user/create-user

    @FormUrlEncoded
    @POST("create-user")
    Call<ResponseBody> createUser(
            @Field("name") String userNamePost,
            @Field("username") String userLoginPost,
            @Field("password") String userPasswordPost,
            @Field("email") String userEmailPost,
            @Field("phone") String userPhonePost
    );

}
