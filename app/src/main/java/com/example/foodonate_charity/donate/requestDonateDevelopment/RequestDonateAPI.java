package com.example.foodonate_charity.donate.requestDonateDevelopment;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RequestDonateAPI {
    @FormUrlEncoded
    @POST ("donates-user")
    Call<Void> postRequestDonate(@Header("Authorization") String token,  @Field("requestedDate") String requestedDate,  @Field("address") String address,
                                             @Field("lats") String lat, @Field("longs") String longs, @Field("charity") String charity,
                                             @Field("quantity") String quantity,
                                             @Field("expiryDate") String expiryDate, @Field("foodTypes") String foodTypes);



}
