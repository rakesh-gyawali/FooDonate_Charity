package com.example.foodonate_charity.account.userDevelopment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodonate_charity.MainActivity;
import com.example.foodonate_charity.URL;

import retrofit2.Call;
import retrofit2.Response;

public class CharityBLL {
    private String charity;
    private String phoneNo;
    private String profilePicture;

    private Response<CharityResponse> response;

    public CharityBLL() {
    }

    public CharityBLL(String charity, String phoneNo) {
        this.charity = charity;
        this.phoneNo = phoneNo;
    }

    public boolean checkGetUser() {
        CharityAPI api = URL.getInstance().create(CharityAPI.class);
        String token = getTokenFromSharedPreference();
        if (token.isEmpty()) {
            Log.i("UserBLL", "TOKEN IS EMPTY ...");
            return false;
        }
        Call<CharityResponse> call = api.getUser(token, "randomString");
        try {
             response = call.execute();
            if (response.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CharityResponse returnCharity() {
        return response.body();
    }

    private String getTokenFromSharedPreference() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN", "");
    }

}
