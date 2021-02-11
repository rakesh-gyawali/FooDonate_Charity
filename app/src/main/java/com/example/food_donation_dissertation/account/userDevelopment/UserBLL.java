package com.example.food_donation_dissertation.account.userDevelopment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.food_donation_dissertation.MainActivity;
import com.example.food_donation_dissertation.URL;
import com.example.food_donation_dissertation.account.ProfileFragment;
import com.example.food_donation_dissertation.account.userRegistrationDevelopment.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Response;

public class UserBLL {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String profilePicture;

    private Response<UserResponse> response;

    public UserBLL() {
    }

    public UserBLL(String firstName, String lastName, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
    }

    public boolean checkGetUser() {
        UserAPI api = URL.getInstance().create(UserAPI.class);
        String token = getTokenFromSharedPreference();
        if (token.isEmpty()) {
            Log.i("UserBLL", "TOKEN IS EMPTY ...");
            return false;
        }
        Call<UserResponse> call = api.getUser(token);
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

    public UserResponse returnUser() {
        return response.body();
    }

    private String getTokenFromSharedPreference() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN", "");
    }

}
