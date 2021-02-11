package com.example.foodonate_charity.log.donateLogDevelopment;

import com.example.foodonate_charity.URL;
import com.example.foodonate_charity.donate.requestDonateDevelopment.RequestDonateAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DonateLogBLL {
    private String requestedDate;
    private String address;
    private String lat;
    private String longs;
    private String charity;
    private String token;
    private String quantity;
    private String expiryDate;
    private String foodTypes;

    public DonateLogBLL(String token) {
        this.token = token;
    }

    public DonateLogBLL(String token, String requestedDate, String address, String lat, String longs, String charity, String quantity, String expiryDate, String foodTypes) {
        this.token = token;
        this.requestedDate = requestedDate;
        this.address = address;
        this.lat = lat;
        this.longs = longs;
        this.charity = charity;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.foodTypes = foodTypes;
    }

    private Response<List<DonateLogResponse>> getResponse;
    private Response<Void> postResponse;

    public boolean checkPostRequest() {
        URL.getStrictMode();
        RequestDonateAPI api = URL.getInstance().create(RequestDonateAPI.class);

        Call<Void> call = api.postRequestDonate(token, requestedDate, address, lat, longs, charity, quantity, expiryDate, foodTypes);
        try {
            postResponse = call.execute();
            if (postResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkGetLog() {
        URL.getStrictMode();
        DonateLogAPI api = URL.getInstance().create(DonateLogAPI.class);

        Call<List<DonateLogResponse>> call = api.checkGetDonate(token);
        try {
            getResponse = call.execute();
            if (getResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<DonateLogResponse> returnLogList() {
        return getResponse.body();
    }




}
