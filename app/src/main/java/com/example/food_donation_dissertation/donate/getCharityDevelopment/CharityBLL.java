package com.example.food_donation_dissertation.donate.getCharityDevelopment;

import android.widget.Toast;

import com.example.food_donation_dissertation.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharityBLL {
    private String name;
    Response<List<CharityResponse>> listResponse;

    public boolean checkGetCharityName() {
        CharityAPI api = URL.getInstance().create(CharityAPI.class);
        Call<List<CharityResponse>> call = api.getCharitiesName();
        try {
            listResponse = call.execute();
            if (listResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CharityResponse> returnCharityNames() {
        return listResponse.body();
    }
}
