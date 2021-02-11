package com.example.foodonate_charity.account.userRegistrationDevelopment;

public class LoginResponse {
    private String token;
    private String status;
    private String phoneNo;
    private String firstName;
    private String lastName;

    public LoginResponse(String phoneNo, String firstName, String lastName) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public LoginResponse(String token, String status, String phoneNo, String firstName, String lastName) {
        this.token = token;
        this.status = status;
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
