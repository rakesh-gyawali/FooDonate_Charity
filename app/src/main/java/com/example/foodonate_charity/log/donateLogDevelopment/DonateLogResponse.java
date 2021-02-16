package com.example.foodonate_charity.log.donateLogDevelopment;

public class DonateLogResponse {
    private String _id;
    private String requestedDate;
    private String pickUpDate;
    private String address;
    private String lats;
    private String longs;
    private String status;
    private User user;
    private String quantity;
    private String expiryDate;
    private String foodTypes;

    public DonateLogResponse(String _id, String requestedDate, String pickUpDate, String address, String lats, String longs, String status, User user, String quantity, String expiryDate, String foodTypes) {
        this._id = _id;
        this.requestedDate = requestedDate;
        this.pickUpDate = pickUpDate;
        this.address = address;
        this.lats = lats;
        this.longs = longs;
        this.status = status;
        this.user = user;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.foodTypes = foodTypes;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
