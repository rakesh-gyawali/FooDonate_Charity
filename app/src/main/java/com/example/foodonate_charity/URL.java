package com.example.foodonate_charity;

import android.os.StrictMode;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {
    public static String token = "";
    //--------- Connect from Emulator ----------
//    public static String BASE_URL = "http://10.0.2.2:3001/api/";
//    public static final String IMAGE_BASE_URL = "http://10.0.2.2:3001/";

    public static String ipAddressOfYourHost = "192.168.0.11";
    public static final String BASE_URL = "http://" + ipAddressOfYourHost + ":3001/api/";
    public static final String IMAGE_BASE_URL = "http://" + ipAddressOfYourHost + ":3001/";

    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static void getStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
