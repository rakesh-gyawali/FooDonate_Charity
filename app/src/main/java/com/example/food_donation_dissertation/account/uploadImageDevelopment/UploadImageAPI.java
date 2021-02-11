package com.example.food_donation_dissertation.account.uploadImageDevelopment;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadImageAPI {
    @Multipart
    @POST("uploads")
    Call<UploadImageResponse> uploadImage (@Part MultipartBody.Part img);
}
