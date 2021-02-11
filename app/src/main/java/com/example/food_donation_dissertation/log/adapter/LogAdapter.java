package com.example.food_donation_dissertation.log.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_donation_dissertation.R;
import com.example.food_donation_dissertation.log.donateLogDevelopment.DonateLogResponse;

import java.lang.reflect.Array;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogItemHolder> {
    private List<DonateLogResponse> donateLogResponseList;
    private Boolean isToggled;
    private Context context;
    private String deliveryDate;

    public LogAdapter(List<DonateLogResponse> donateLogResponseList, Boolean isToggled, Context context) {
        this.donateLogResponseList = donateLogResponseList;
        this.isToggled = isToggled;
        this.context = context;
    }

    @NonNull
    @Override
    public LogItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LogItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.log_items, parent, false));

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull LogItemHolder holder, int position) {
        String status = donateLogResponseList.get(position).getStatus().toUpperCase();
        holder.tvStatus.setText(status);
        //change status text color according to status ...
        if (status.equals("PENDING") || status.equals("CANCELLED")) holder.tvStatus.setTextColor(context.getColor(R.color.red));
        else if (status.equals("COMPLETED") || status.equals("ACCEPTED")) holder.tvStatus.setTextColor(context.getColor(R.color.colorPrimary));
        holder.tvName.setText(donateLogResponseList.get(position).getCharity().getName());
        //get shorter requested date ...
        String [] arr1 =  donateLogResponseList.get(position).getRequestedDate().split(" ");
        String requestedDate = "Requested on: " + arr1[1] + " " + arr1[2] + " " + arr1[3].substring(0, 5);
        holder.tvRequestedOn.setText(requestedDate);
        //get shorter delivery date ...
        if (donateLogResponseList.get(position).getPickUpDate() != null) {
            String [] arr2 = donateLogResponseList.get(position).getPickUpDate().split(" ");
            deliveryDate = arr2[1] + " " + arr2[2] + " " + arr2[3].substring(0, 5);
        }
        //get shorter address ...
        String address = donateLogResponseList.get(position).getAddress();
        if (address.length() > 25) address = address.substring(0, 25) + " ...";
        holder.tvLocation.setText(address);

        //toggled = Completed and Cancelled Requests ...
        if (isToggled && donateLogResponseList.get(position).getStatus().equals("cancelled")) {
            holder.tvDate.setText("Pick up date: -");
        }
        if (isToggled && donateLogResponseList.get(position).getStatus().equals("delivered")) {
            //get shorter delivery date ...
            holder.tvDate.setText("Pick up date: " + deliveryDate);

        }
        if (!isToggled && donateLogResponseList.get(position).getStatus().equals("pending")) {
            holder.tvDate.setText("Pick up date: -");
        }
        if (!isToggled && donateLogResponseList.get(position).getStatus().equals("accepted")) {
            holder.tvDate.setText("Pick up date: " + deliveryDate);
        }
    }

    @Override
    public int getItemCount() {
        return donateLogResponseList.size();
    }

    public class LogItemHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvStatus;
        private TextView tvDate;
        private TextView tvRequestedOn;
        private TextView tvLocation;

        public LogItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvRequestedOn = itemView.findViewById(R.id.tvRequestedOn);
            tvLocation = itemView.findViewById(R.id.tvLocation);
        }
    }
}
