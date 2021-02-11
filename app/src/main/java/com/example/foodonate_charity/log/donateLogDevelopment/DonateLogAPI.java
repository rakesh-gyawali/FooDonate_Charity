package com.example.foodonate_charity.log.donateLogDevelopment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DonateLogAPI {
    @GET("donates-user/")
    Call<List<DonateLogResponse>> checkGetDonate(@Header("authorization") String token);

}
