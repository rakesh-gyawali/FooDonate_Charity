package com.example.foodonate_charity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.github.ybq.android.spinkit.SpinKitView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView ivSplashLogo;
    private SpinKitView spin_kit;
    static int TIMEOUT_MILLIS = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivSplashLogo = findViewById(R.id.ivSplashLogo);
        spin_kit = findViewById(R.id.spin_kit);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
               checkWhetherLocationHasBeenSavedOrNotAndStartActivityAccordingly();
            }
        }, TIMEOUT_MILLIS);

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        ivSplashLogo.startAnimation(animFadeIn);
    }
    private void checkWhetherLocationHasBeenSavedOrNotAndStartActivityAccordingly() {
        SharedPreferences savedData = getSharedPreferences("USER_LOCATION", Context.MODE_PRIVATE);
        String addressLine =  savedData.getString("address_line", "");
        if (addressLine.isEmpty()) {
            Intent intent = new Intent(SplashScreenActivity.this, WelcomeLocation.class);
            intent.putExtra("welcome_screen", true);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        //show check mark ...
    }
}