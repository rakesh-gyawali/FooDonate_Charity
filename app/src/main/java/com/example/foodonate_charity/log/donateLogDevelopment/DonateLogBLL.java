package com.example.foodonate_charity.log.donateLogDevelopment;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.example.foodonate_charity.URL;
import com.example.foodonate_charity.donate.requestDonateDevelopment.RequestDonateAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Integer totalQuantity = 0;
    private Integer monthQuantity = 0;
    private Integer monthDonator = 0;
    private Integer totalDonator = 0;
    private Integer cancelled = 0;
    private Integer accepted = 0;
    private Integer delivered = 0;
    private Integer pending = 0;

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

    public void filerDashboard() throws ParseException {
        for (DonateLogResponse log: getResponse.body()) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("E MMM dd");
            Date date = format.parse(log.getRequestedDate());
            if (date.getMonth() - new Date().getMonth() < 2) {
                String s = log.getQuantity();
                monthQuantity += Integer.parseInt(log.getQuantity());
                monthDonator++;
            }
            switch (log.getStatus()) {
                case "cancelled":
                    cancelled++;
                    break;
                case "pending":
                    pending++;
                    break;
                case "delivered":
                    delivered++;
                    break;
                case "accepted":
                    accepted++;
                    break;
            }

            totalQuantity += Integer.parseInt(log.getQuantity());
            totalDonator++;
        }
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public Integer getDelivered() {
        return delivered;
    }

    public Integer getPending() {
        return pending;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Integer getMonthQuantity() {
        return monthQuantity;
    }

    public Integer getMonthDonator() {
        return monthDonator;
    }

    public Integer getTotalDonator() {
        return totalDonator;
    }

    public List<DonateLogResponse> returnLogList() {
        return getResponse.body();
    }




}
