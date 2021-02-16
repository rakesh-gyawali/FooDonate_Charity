package com.example.foodonate_charity.log.donateLogDevelopment;

import androidx.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DonateLogAPI {
    @GET("donates-charity/")
    Call<List<DonateLogResponse>> checkGetDonate(@Header("authorization") String token);

    @FormUrlEncoded
    @PUT("donates-charity/{request_id}")
    Call<Void> checkPutDonate (@Header("authorization") String token, @Path("request_id") String request_id, @Field("status") String status, @Nullable @Field("pickUpDate") String pickUpDate);

}
