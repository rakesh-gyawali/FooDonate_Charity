package com.example.foodonate_charity.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}