package com.example.food_donation_dissertation.donate.getCharityDevelopment;

import com.example.food_donation_dissertation.account.userDevelopment.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CharityAPI {
    @GET("charities")
    Call<List<CharityResponse>> getCharitiesName();
}
