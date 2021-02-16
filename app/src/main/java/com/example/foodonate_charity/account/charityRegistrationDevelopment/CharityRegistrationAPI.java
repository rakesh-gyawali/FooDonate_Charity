package com.example.foodonate_charity.account.charityRegistrationDevelopment;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CharityRegistrationAPI {
    @FormUrlEncoded
    @POST("registration-charity/register")
    Call<RegistrationResponse> checkRegister(@Field("phoneNo") String phoneNo, @Field("password") String password, @Field("name") String name, @Field("logo") String logo, @Field("address") String address , @Field("email") String email);

    @FormUrlEncoded
    @POST("registration-charity/login")
    Call<LoginResponse> checkLogin(@Field("phoneNo") String phoneNo, @Field("password") String password);


}
