package com.example.food_donation_dissertation.log.donateLogDevelopment;

public class DonateLogResponse {
    private String requestedDate;
    private String pickUpDate;
    private String address;
    private String lats;
    private String longs;
    private String status;
    private Charity charity;
    private String user;
    private String quantity;
    private String expiryDate;
    private String foodTypes;

    public DonateLogResponse(String requestedDate, String pickUpDate, String address, String lats, String longs, String status, Charity charity, String user, String quantity, String expiryDate, String foodTypes) {
        this.requestedDate = requestedDate;
        this.pickUpDate = pickUpDate;
        this.address = address;
        this.lats = lats;
        this.longs = longs;
        this.status = status;
        this.charity = charity;
        this.user = user;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.foodTypes = foodTypes;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLats() {
        return lats;
    }

    public void setLats(String lats) {
        this.lats = lats;
    }

    public String getLongs() {
        return longs;
    }

    public void setLongs(String longs) {
        this.longs = longs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
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
