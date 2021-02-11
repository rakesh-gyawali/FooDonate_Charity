package com.example.foodonate_charity.account.userDevelopment;

import com.example.foodonate_charity.account.userRegistrationDevelopment.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface UserAPI {
    @GET("users")
    Call<UserResponse> getUser(@Header("Authorization") String header);

    @FormUrlEncoded
    @PUT("users")
    Call<LoginResponse> checkLogin(@Field("phoneNo") String phoneNo, @Field("password") String password);
}
