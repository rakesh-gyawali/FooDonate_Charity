package com.example.food_donation_dissertation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.food_donation_dissertation.account.LoginFragment;
import com.example.food_donation_dissertation.account.ProfileFragment;
import com.example.food_donation_dissertation.donate.DonateFragment;
import com.example.food_donation_dissertation.home.HomeFragment;
import com.example.food_donation_dissertation.log.LogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottom_navigation;
    public static Context _contextOfApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _contextOfApplication = getApplicationContext();

        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        openFragment(new HomeFragment());

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openFragment(new HomeFragment());
                        return true;
                    case R.id.donate:
                        openFragment(new DonateFragment());
                        return true;
                    case R.id.logs:
                        openFragment(new LogFragment());
                        return true;
                    case R.id.account:
                        if (hasLoggedIn()) {
                            openFragment(new ProfileFragment());
                            return true;
                        } else {
                            openFragment(new LoginFragment());
                        }
                }
                return false;
            }
        });
    }

    public static Context getContextOfApplication(){
        return _contextOfApplication;
    }

    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private boolean hasLoggedIn() {
        SharedPreferences savedData = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return savedData.getBoolean("HAS_LOGGED_IN", false);
    }

}