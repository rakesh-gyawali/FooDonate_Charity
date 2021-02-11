package com.example.food_donation_dissertation.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.food_donation_dissertation.R;
import com.example.food_donation_dissertation.home.adapters.RecentActivityAdapter;
import com.example.food_donation_dissertation.home.adapters.RowItemAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<String> mNames;
    ArrayList<Integer> mImages;
    ArrayList<String> mAddresses;

    ArrayList<String> mActNames;
    ArrayList<Integer> mActLogos;
    ArrayList<Integer> mActBanners;
    ArrayList<String> mActAddresses;
    ArrayList<String> mActDates;

    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //-----carousel view -----
        CarouselView carouselView;
        int[] sampleImages = {R.drawable.page3, R.drawable.page2, R.drawable.page1};
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
        carouselView.setImageListener(imageListener);

        makeRowItemList(view);
        makeRecentActivityList(view);
        return view;
    }
    //-----row item-----
    private void makeRowItemList(View view) {
        mNames = new ArrayList<>();
        mImages = new ArrayList<>();
        mAddresses = new ArrayList<>();

        mImages.add(R.drawable.charity_logo1);
        mNames.add("Rotatrot Club");
        mAddresses.add("Chakrapath, KTM");

        mImages.add(R.drawable.charity_logo2);
        mNames.add("Lions Club");
        mAddresses.add("Pashupati, KTM");

        mImages.add(R.drawable.charity_logo3);
        mNames.add("Worker Care Charity");
        mAddresses.add("Putalisadak, KTM");

        mImages.add(R.drawable.charity_logo4);
        mNames.add("Share");
        mAddresses.add("Bouddha, KTM");

        mImages.add(R.drawable.square);
        mNames.add("KTM Youth Club");
        mAddresses.add("Bouddha, KTM");
        initRowItemRecyclerView(view);

    }

    private void initRowItemRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvRowItem);
        recyclerView.setLayoutManager(layoutManager);
        RowItemAdapter adapter = new RowItemAdapter (mNames, mImages, mAddresses, getContext());
        recyclerView.setAdapter(adapter);
    }

    //-----recent activity-----
    private void makeRecentActivityList(View view) {
        mActNames = new ArrayList<>();
        mActLogos = new ArrayList<>();
        mActBanners = new ArrayList<>();
        mActAddresses = new ArrayList<>();
        mActDates = new ArrayList<>();

        mActLogos.add(R.drawable.charity_logo4);
        mActNames.add("New Year Food Donation Program");
        mActBanners.add(R.drawable.act1);
        mActAddresses.add("Chakrapath, KTM");
        mActDates.add("Feb 20 2021");

        mActLogos.add(R.drawable.charity_logo3);
        mActNames.add("10th Anniversary Program");
        mActBanners.add(R.drawable.act2);
        mActAddresses.add("Kalanki, KTM");
        mActDates.add("Jan 1 2021");

        mActLogos.add(R.drawable.charity_logo2);
        mActNames.add("Maithi Devi School Food Distribution Program");
        mActBanners.add(R.drawable.act3);
        mActAddresses.add("Maithi Devi, KTM");
        mActDates.add("Dec 21 2020");

        mActLogos.add(R.drawable.charity_logo1);
        mActNames.add("Food Distribution Program");
        mActBanners.add(R.drawable.act4);
        mActAddresses.add("Jayabageswori, KTM");
        mActDates.add("Dec 10 2021");
        initActRecyclerView(view);
    }

    private void initActRecyclerView(View view) {
        Log.d(TAG, "initRecentRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvRowItem2);
        recyclerView.setLayoutManager(layoutManager);
        RecentActivityAdapter adapter = new RecentActivityAdapter (mActNames, mActLogos , mActBanners, mActAddresses, mActDates, getContext());
        recyclerView.setAdapter(adapter);
    }


}