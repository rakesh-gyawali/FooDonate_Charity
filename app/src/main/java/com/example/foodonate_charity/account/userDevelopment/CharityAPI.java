package com.example.foodonate_charity.account.userDevelopment;

import com.example.foodonate_charity.account.charityRegistrationDevelopment.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CharityAPI {
    @GET("charities/{charity_id}")
    Call<CharityResponse> getUser(@Header("Authorization") String header, @Path("charity_id") String randomString);

    @FormUrlEncoded
    @PUT("users")
    Call<LoginResponse> checkLogin(@Field("phoneNo") String phoneNo, @Field("password") String password);
}
