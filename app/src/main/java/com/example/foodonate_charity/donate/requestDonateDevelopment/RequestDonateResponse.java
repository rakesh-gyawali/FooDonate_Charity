package com.example.foodonate_charity.donate.requestDonateDevelopment;

import java.util.Date;

public class RequestDonateResponse {
    private Date requestedDate;
    private String address;
    private String lat;
    private String longs;
    private String charity;
    private String user;
    private String quantity;
    private String expiryDate;
    private String foodTypes;

    public RequestDonateResponse(Date requestedDate, String address, String lat, String longs, String charity, String user, String quantity, String expiryDate, String foodTypes) {
        this.requestedDate = requestedDate;
        this.address = address;
        this.lat = lat;
        this.longs = longs;
        this.charity = charity;
        this.user = user;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.foodTypes = foodTypes;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongs() {
        return longs;
    }

    public void setLongs(String longs) {
        this.longs = longs;
    }

    public String getCharity() {
        return charity;
    }

    public void setCharity(String charity) {
        this.charity = charity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(String foodTypes) {
        this.foodTypes = foodTypes;
    }
}
