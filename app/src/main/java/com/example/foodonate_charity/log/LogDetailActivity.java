package com.example.foodonate_charity.log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonate_charity.MainActivity;
import com.example.foodonate_charity.R;
import com.example.foodonate_charity.URL;
import com.example.foodonate_charity.donate.MapsActivity;
import com.example.foodonate_charity.log.donateLogDevelopment.DonateLogAPI;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogDetailActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvContact;
    private TextView tvFoodItems;
    private TextView tvQuantity;
    private TextView tvLocation;
    private TextView tvStatus;
    private TextView tvRequestedOn;
    private Button btnViewMap;
    private Button btnDecline;
    private Button btnAccept;
    private String fullname;
    private String contact;
    private String foodTypes;
    private String quantity;
    private String location;
    private String status;
    private Double lats;
    private Double longs;
    private String _id;
    private String exprityDate;
    private String requestedDate;
    private String pickUpDate = null;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private Boolean isAccepted;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_detail);

        tvName = findViewById(R.id.tvName);
        tvContact = findViewById(R.id.tvContact);
        tvFoodItems = findViewById(R.id.tvFoodItems);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvLocation = findViewById(R.id.tvLocation);
        tvStatus = findViewById(R.id.tvStatus);
        tvRequestedOn = findViewById(R.id.tvRequestedOn);
        btnViewMap = findViewById(R.id.btnViewMap);
        btnDecline = findViewById(R.id.btnDecline);
        btnAccept = findViewById(R.id.btnAccept);
        getValuesFromIntent();

        btnViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("lats", lats);
                intent.putExtra("longs", longs);
                startActivity(intent);
            }
        });

        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putAsyncCall("cancelled");
                finish();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAccepted) {
                    setUpDatePicker();
                    new DatePickerDialog(LogDetailActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                    putAsyncCall("delivered");

                }
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getValuesFromIntent() {
        try {
            fullname = getIntent().getStringExtra("firstname") + " " + getIntent().getStringExtra("lastname");
            contact = getIntent().getStringExtra("contact");
            foodTypes = getIntent().getStringExtra("food_types");
            quantity = getIntent().getStringExtra("quantity") + " KG";
            location = getIntent().getStringExtra("address");
            lats = Double.parseDouble(getIntent().getStringExtra("lats"));
            longs = Double.parseDouble(getIntent().getStringExtra("longs"));
            status = getIntent().getStringExtra("status").toUpperCase();

            if (status.equals("ACCEPTED")) isAccepted = true;
            else isAccepted = false;

            _id = getIntent().getStringExtra("_id");

            requestedDate = getIntent().getStringExtra("requested_date");
            String [] arr1 =  requestedDate.split(" ");
            requestedDate = arr1[1] + " " + arr1[2];
            setValuesToTextView();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Value could not be extracted from intent ...", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setValuesToTextView() {
        tvName.setText(fullname);
        tvContact.setText(contact);
        tvFoodItems.setText(foodTypes);
        tvQuantity.setText(quantity);
        tvLocation.setText(location);
        tvStatus.setText(status);

      if (status.equals("CANCELLED") || status.equals("DELIVERED")) {
            btnAccept.setVisibility(View.GONE);
            btnDecline.setVisibility(View.GONE);
      }
      else if (status.equals("ACCEPTED")) {
          btnAccept.setText("Delivered");
          btnDecline.setText("Cancel");
      }

        tvRequestedOn.setText(requestedDate);
        if (status.equals("PENDING") || status.equals("CANCELLED")) tvStatus.setTextColor(this.getColor(R.color.red));
        else tvStatus.setTextColor(this.getColor(R.color.colorAddress));
    }

    private void putAsyncCall(String newStatus) {
        DonateLogAPI donateLogAPI = URL.getInstance().create(DonateLogAPI.class);
        Call<Void> call = donateLogAPI.checkPutDonate(getTokenFromSharedPreference(), _id, newStatus, pickUpDate);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    tvStatus.setText(newStatus);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(LogDetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getTokenFromSharedPreference() {
        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN", "");
    }

    private void setUpDatePicker() {
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                getSelectedDate();
            }
        };
    }
    private void getSelectedDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        pickUpDate = sdf.format(myCalendar.getTime());

        if (pickUpDate != null) {
            new MaterialAlertDialogBuilder(LogDetailActivity.this)
                    .setTitle("Confirm")
                    .setMessage("Pickup date is " + pickUpDate + ". Are you sure?" )
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            putAsyncCall("accepted");
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        }
    }
}