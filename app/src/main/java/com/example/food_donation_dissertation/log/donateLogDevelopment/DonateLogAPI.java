package com.example.food_donation_dissertation.log.donateLogDevelopment;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DonateLogAPI {
    @GET("donates-user/")
    Call<List<DonateLogResponse>> checkGetDonate(@Header("authorization") String token);

}
