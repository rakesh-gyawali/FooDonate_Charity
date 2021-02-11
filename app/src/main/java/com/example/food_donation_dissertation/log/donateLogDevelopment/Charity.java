package com.example.food_donation_dissertation.log.donateLogDevelopment;

public class Charity {
    private String phoneNo;
    private String name;
    private String logo;
    private String address;
    private String lats;
    private String longs;
    private String email;

    public Charity(String phoneNo, String name, String logo, String address, String lats, String longs, String email) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.logo = logo;
        this.address = address;
        this.lats = lats;
        this.longs = longs;
        this.email = email;
    }


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
