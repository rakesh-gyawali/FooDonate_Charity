package com.example.foodonate_charity.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.foodonate_charity.MainActivity;
import com.example.foodonate_charity.R;
import com.example.foodonate_charity.log.donateLogDevelopment.DonateLogBLL;
import com.github.ybq.android.spinkit.SpinKitView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.ParseException;

public class HomeCharityFragment extends Fragment {

    private TextView tvFM, tvFT, tvDM, tvDT;
    private PieChart pieChart;
    private SpinKitView spin_kit;
    private RelativeLayout rlCards;

    public HomeCharityFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_charity, container, false);
        tvFM = view.findViewById(R.id.tvFM);
        tvFT = view.findViewById(R.id.tvFT);
        tvDM = view.findViewById(R.id.tvDM);
        tvDT = view.findViewById(R.id.tvDT);
        rlCards = view.findViewById(R.id.rlCards);
        pieChart = view.findViewById(R.id.piechart);
        spin_kit = view.findViewById(R.id.spin_kit);

        try {
            SyncCall();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  view;
    }

    @SuppressLint("SetTextI18n")
    private void SyncCall() throws ParseException {
        DonateLogBLL bll = new DonateLogBLL(getTokenFromSharedPreference());
        if (bll.checkGetLog()) {
            bll.filerDashboard();
            pieChart.addPieSlice(new PieModel("Cancelled", bll.getCancelled(), Color.parseColor("#ff595e")));
            pieChart.addPieSlice(new PieModel("Pending", bll.getPending(), Color.parseColor("#ffca3a")));
            pieChart.addPieSlice(new PieModel("Accepted", bll.getAccepted(), Color.parseColor("#8ac926")));
            pieChart.addPieSlice(new PieModel("Completed", bll.getDelivered(), Color.parseColor("#1982c4")));
            pieChart.startAnimation();
            tvFM.setText(String.valueOf(bll.getMonthQuantity()));
            tvFT.setText(String.valueOf(bll.getTotalQuantity()));
            tvDM.setText(String.valueOf(bll.getMonthDonator()));
            tvDT.setText(String.valueOf(bll.getTotalDonator()));

            spin_kit.setVisibility(View.GONE);
            rlCards.setVisibility(View.VISIBLE);
        }
    }

    private String getTokenFromSharedPreference() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN", "");
    }
}