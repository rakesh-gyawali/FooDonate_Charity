package com.example.foodonate_charity.log.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodonate_charity.R;
import com.example.foodonate_charity.log.LogDetailActivity;
import com.example.foodonate_charity.log.donateLogDevelopment.DonateLogResponse;

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
        else if (status.equals("COMPLETED") || status.equals("ACCEPTED")) holder.tvStatus.setTextColor(context.getColor(R.color.colorAddress));
        holder.tvName.setText(donateLogResponseList.get(position).getUser().getFirstName().concat(" " + donateLogResponseList.get(position).getUser().getLastName()));
        holder.tvContact.setText("Contact No: " + donateLogResponseList.get(position).getUser().getPhoneNo());
        //get shorter requested date ...
        String [] arr1 =  donateLogResponseList.get(position).getRequestedDate().split(" ");
        String requestedDate = "Requested on: " + arr1[1] + " " + arr1[2] + " " + arr1[3].substring(0, 5);
        holder.tvRequestedOn.setText(requestedDate);
        //get shorter delivery date ...
        if (donateLogResponseList.get(position).getPickUpDate() != null) {
//            String [] arr2 = donateLogResponseList.get(position).getPickUpDate().split(" ");
//            deliveryDate = arr2[1] + " " + arr2[2] + " " + arr2[3].substring(0, 5);
            deliveryDate = donateLogResponseList.get(position).getPickUpDate();
        }
        //get shorter address ...
        String address = donateLogResponseList.get(position).getAddress();
        if (address.length() > 20) address = address.substring(0, 20) + " ...";
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
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LogDetailActivity.class);
                intent.putExtra("firstname",donateLogResponseList.get(position).getUser().getFirstName());
                intent.putExtra("lastname",donateLogResponseList.get(position).getUser().getLastName());
                intent.putExtra("contact",donateLogResponseList.get(position).getUser().getPhoneNo());
                intent.putExtra("food_types",donateLogResponseList.get(position).getFoodTypes());
                intent.putExtra("quantity",donateLogResponseList.get(position).getQuantity());
                intent.putExtra("address",donateLogResponseList.get(position).getAddress());
                intent.putExtra("lats",donateLogResponseList.get(position).getLats());
                intent.putExtra("longs",donateLogResponseList.get(position).getLongs());
                intent.putExtra("_id",donateLogResponseList.get(position).get_id());
                intent.putExtra("status",donateLogResponseList.get(position).getStatus());
                intent.putExtra("requested_date", donateLogResponseList.get(position).getRequestedDate());
                intent.putExtra("expiry_date", donateLogResponseList.get(position).getExpiryDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donateLogResponseList.size();
    }

    public class LogItemHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvContact;
        private TextView tvStatus;
        private TextView tvDate;
        private TextView tvRequestedOn;
        private TextView tvLocation;
        private Button btnDetail;

        public LogItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvContact = itemView.findViewById(R.id.tvContact);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvRequestedOn = itemView.findViewById(R.id.tvRequestedOn);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            btnDetail = itemView.findViewById(R.id.btnDetail);

        }
    }
}
