package com.example.upmobiletest.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddBlankPassanger {
    @FormUrlEncoded
    @POST("create-pblanks")
    Call<ResponseBody> CreateBlankPassanger(
            @Field("iduser") String userid,
            @Field("price") String price,
            @Field("passangers") String passangers,
            @Field("addressfrom") String addressfrom,
            @Field("leavedate") String places,
            @Field("cartype") String cartype
    );
}
