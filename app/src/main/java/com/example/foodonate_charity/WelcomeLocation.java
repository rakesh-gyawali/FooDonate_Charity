package com.example.foodonate_charity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodonate_charity.donate.MapsActivity;

public class WelcomeLocation extends AppCompatActivity implements View.OnClickListener {
    private TextView tvMap, tvAddress, tvLater;
    private ImageView imgCheck;
    private Button btnContinue;
    private Boolean isAddressSelected = false;
    private final String TAG = "LocationConfirm started";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_location);

        tvMap = findViewById(R.id.tvMap);
        tvAddress = findViewById(R.id.tvAddress);
        tvLater = findViewById(R.id.tvLater);
        btnContinue = findViewById(R.id.btnConfirm);
        imgCheck = findViewById(R.id.imgCheck);

        getAddressFromSharedPreference();


        tvMap.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvLater.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tvMap:
                 intent = new Intent(getApplicationContext(), MapsActivity.class);
                 startActivity(intent);
                 break;
            case R.id.tvAddress:
                Log.i(TAG, "tvAddress is pressed");
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                 startActivity(intent);
                 break;
            case R.id.btnConfirm:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                if (isAddressSelected) {
                    startActivity(intent);
                    break;
                }
            case R.id.tvLater:
                intent = new Intent(getApplicationContext(), MainActivity.class);
        }
    }

    private void getAddressFromSharedPreference() {
        SharedPreferences savedData = getSharedPreferences("USER_LOCATION", Context.MODE_PRIVATE);
        String addressLine =  savedData.getString("address_line", "");
        if (addressLine.isEmpty()) {
            tvAddress.setText("Address has not been set ...");
            imgCheck.setVisibility(View.INVISIBLE);
            btnContinue.setBackgroundColor(Color.GRAY);
            btnContinue.setClickable(false);
            return;
        } else if (addressLine.length() > 35) {
            //shorten addressLine to fit in card view ...
            String addressLineInShort =  addressLine.substring(0, 30);
            tvAddress.setText(addressLineInShort.concat(" ...."));
        } else {
            tvAddress.setText(addressLine);
        }
        //show check mark ...
        imgCheck.setVisibility(View.VISIBLE);
        btnContinue.setBackgroundResource(R.color.colorPrimary);
        btnContinue.setClickable(true);
        tvLater.setVisibility(View.INVISIBLE);
        isAddressSelected = true;

    }
}