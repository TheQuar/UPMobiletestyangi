package com.example.upmobiletest.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddBlankDrive {
    @FormUrlEncoded
    @POST("create-dblank")
    Call<ResponseBody> CreateBlankDriver(
            @Field("iduser") String userid,
            @Field("addressfrom") String addressfrom,
            @Field("leavetime") String leavetime,
            @Field("price") String price,
            @Field("places") String places,
            @Field("passangers") String passangers,
            @Field("carname") String carname,
            @Field("carnum") String carnum
    );

}
