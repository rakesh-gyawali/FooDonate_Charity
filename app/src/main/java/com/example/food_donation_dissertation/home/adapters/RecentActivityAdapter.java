package com.example.food_donation_dissertation.home.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_donation_dissertation.R;

import java.util.ArrayList;

public class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.ViewHolder> {

    private ArrayList<String> mActivityNames = new ArrayList<>();
    private ArrayList<Integer> mLogoImgs = new ArrayList<>();
    private ArrayList<Integer> mActivityImgs = new ArrayList<>();
    private ArrayList<String> mAddresses = new ArrayList<>();
    private ArrayList<String> mDate = new ArrayList<>();
    private Context mContext;
    private static final String TAG = "RecentActivityAdapter";

    public RecentActivityAdapter(ArrayList<String> mActivityNames, ArrayList<Integer> mLogoImgs, ArrayList<Integer> mActivityImgs, ArrayList<String> mAddresses, ArrayList<String> mDate, Context mContext) {
        this.mActivityNames = mActivityNames;
        this.mLogoImgs = mLogoImgs;
        this.mActivityImgs = mActivityImgs;
        this.mAddresses = mAddresses;
        this.mDate = mDate;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_activity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.imgActivity.setImageDrawable(ContextCompat.getDrawable(mContext, mActivityImgs.get(position)));
        holder.imgLogo.setImageDrawable(ContextCompat.getDrawable(mContext, mLogoImgs.get(position)));
        holder.tvActivityName.setText(mActivityNames.get(position));
        holder.tvDate.setText(mDate.get(position));
        holder.tvAddress.setText(mAddresses.get(position));
    }

    @Override
    public int getItemCount() {
        return mActivityNames.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgActivity;
        ImageView imgLogo;
        TextView tvActivityName;
        TextView tvAddress;
        TextView tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgActivity = itemView.findViewById(R.id.imgActivity);
            imgLogo = itemView.findViewById(R.id.imgLogo);
            tvActivityName = itemView.findViewById(R.id.tvActivityName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
