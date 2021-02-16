package com.example.foodonate_charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodonate_charity.account.LoginFragment;
import com.example.foodonate_charity.account.ProfileFragment;
import com.example.foodonate_charity.donate.DonateFragment;
import com.example.foodonate_charity.donate.LocationConfirm;
import com.example.foodonate_charity.home.HomeCharityFragment;
import com.example.foodonate_charity.home.HomeFragment;
import com.example.foodonate_charity.log.LogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottom_navigation;
    public static Context _contextOfApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _contextOfApplication = getApplicationContext();

        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        if (hasLoggedIn()) openFragment(new HomeCharityFragment());
        else openFragment(new LoginFragment());


        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
//                        openFragment(new HomeFragment());
                        if (hasLoggedIn()) {
                            openFragment(new HomeCharityFragment());
                        } else {
                            new MaterialAlertDialogBuilder(MainActivity.this)
                                    .setTitle("Please Login")
                                    .setMessage("Please login to view this page.")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            openFragment(new LoginFragment());
                                        }
                                    }).show();
                        }
                        return true;
//                    case R.id.donate:
//                        openFragment(new DonateFragment());
//                        return true;
                    case R.id.request:
                        if (hasLoggedIn()) {
                            openFragment(new LogFragment());
                        } else {
                            new MaterialAlertDialogBuilder(MainActivity.this)
                                    .setTitle("Please Login")
                                    .setMessage("Please login to view this page.")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            openFragment(new LoginFragment());
                                        }
                                    }).show();
                        }
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