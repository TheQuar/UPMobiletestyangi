package com.example.upmobiletest.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDriveAdData {
    @FormUrlEncoded
    @POST("adv-driver")
    Call<ResponseBody> CallGetDriveAdData(
            @Field("userid") String userid
    );
}
