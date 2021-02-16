package com.example.foodonate_charity.account.charityRegistrationDevelopment;

import retrofit2.Call;
import retrofit2.Response;

import com.example.foodonate_charity.URL;

import java.io.IOException;

public class UserRegistrationBLL {
    private String phoneNo;
    private String password;
    private String name;
    private String email;
    private String logo;
    private String address;
    private boolean isSuccess;

    public UserRegistrationBLL(String phoneNo, String password, String name, String email, String logo, String address) {
        this.phoneNo = phoneNo;
        this.password = password;
        this.name = name;
        this.email = email;
        this.logo = logo;
        this.address = address;
    }

    public UserRegistrationBLL(String phoneNo, String password) {
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public boolean checkLogin() {
        isSuccess = false;
        CharityRegistrationAPI userAPI = URL.getInstance().create(CharityRegistrationAPI.class);
        Call<LoginResponse> call =userAPI.checkLogin(phoneNo, password);
        try {
            Response<LoginResponse> response = call.execute();
            if (response.isSuccessful()) {
                URL.token = response.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean checkRegister() {
        isSuccess = false;
        CharityRegistrationAPI userAPI = URL.getInstance().create(CharityRegistrationAPI.class);
        Call<RegistrationResponse> call =userAPI.checkRegister(phoneNo, password, name, logo, address, email);
        try {
            Response<RegistrationResponse> response = call.execute();
            if (response.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }






}
