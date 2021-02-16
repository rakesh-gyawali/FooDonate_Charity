package com.example.foodonate_charity.log;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodonate_charity.MainActivity;
import com.example.foodonate_charity.R;
import com.example.foodonate_charity.log.adapter.LogAdapter;
import com.example.foodonate_charity.log.donateLogDevelopment.DonateLogBLL;
import com.example.foodonate_charity.log.donateLogDevelopment.DonateLogResponse;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class LogFragment extends Fragment {

    private List<DonateLogResponse> donateLogList;
    private List<DonateLogResponse> donateLogToggledList = new ArrayList<>();
    private List<DonateLogResponse> donateLogUnToggledList = new ArrayList<>();

    private SwitchMaterial mSwitch;
    private RecyclerView rcvLog;
    LogAdapter adapter;
    public LogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        mSwitch = view.findViewById(R.id.mSwitch);
        rcvLog = view.findViewById(R.id.rcvLog);
        rcvLog.setLayoutManager(new LinearLayoutManager(getContext()));
        logSyncCall();

        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSwitch.isChecked())
                    donateLogList = donateLogToggledList;
                else
                    donateLogList = donateLogUnToggledList;
                adapter = new LogAdapter(donateLogList, mSwitch.isChecked(), getContext());
                rcvLog.setAdapter(adapter);

            }
        });
        return view;
    }

//    private void logAsyncCall() {
//        DonateLogAPI api = URL.getInstance().create(DonateLogAPI.class);
//        Call<List<DonateLogResponse>> call = api.checkGetDonate(getTokenFromSharedPreference());
//
//        call.enqueue(new Callback<List<DonateLogResponse>>() {
//            @Override
//            public void onResponse(Call<List<DonateLogResponse>> call, Response<List<DonateLogResponse>> response) {
//                if (response.isSuccessful()) {
//                    donateLogList =  response.body();
//
//                    for (DonateLogResponse log: donateLogList) {
//                        if (log.getStatus().equals("cancelled") || log.getStatus().equals("delivered")) {
//                            donateLogToggledList.add(log);
//                        } else if (log.getStatus().equals("pending") || log.getStatus().equals("accepted")) {
//                            donateLogUnToggledList.add(log);
//                        }
//                    }
//                    if (mSwitch.isChecked()) donateLogList = donateLogToggledList;
//                    else donateLogList = donateLogUnToggledList;
//                    adapter = new LogAdapter(donateLogList, mSwitch.isChecked(), getContext());
//                    rcvLog.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DonateLogResponse>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void logSyncCall() {
        DonateLogBLL bll = new DonateLogBLL(getTokenFromSharedPreference());
        if (bll.checkGetLog()) {
            donateLogList =  bll.returnLogList();
            for (DonateLogResponse log: donateLogList) {
                if (log.getStatus().equals("cancelled") || log.getStatus().equals("delivered")) {
                    donateLogToggledList.add(log);
                } else if (log.getStatus().equals("pending") || log.getStatus().equals("accepted")) {
                    donateLogUnToggledList.add(log);
                }
            }
            if (mSwitch.isChecked()) donateLogList = donateLogToggledList;
            else donateLogList = donateLogUnToggledList;
            adapter = new LogAdapter(donateLogList, mSwitch.isChecked(), getContext());
            rcvLog.setAdapter(adapter);
        }
    }

    private String getTokenFromSharedPreference() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN", "");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}