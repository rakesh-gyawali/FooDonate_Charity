package com.example.foodonate_charity.account.userDevelopment;

public class CharityResponse {
    private String phoneNo;
    private String name;
    private String logo;

    public CharityResponse(String phoneNo, String name, String logo) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.logo = logo;
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
}
