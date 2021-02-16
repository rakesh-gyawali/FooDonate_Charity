    package com.example.foodonate_charity.donate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodonate_charity.R;
import com.example.foodonate_charity.URL;
import com.example.foodonate_charity.donate.getCharityDevelopment.CharityAPI;
import com.example.foodonate_charity.donate.getCharityDevelopment.CharityResponse;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


    public class DonateFragment extends Fragment {
    private EditText edtDate;
    private EditText edtQuantity;
    private AutoCompleteTextView actvCharity;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private Button btnNext;
    private ChipGroup chipGroup;
    private List<CharityResponse> charityResponseList;
    private List<String> nameList;
    private String foodTypes;
    private String quantity;
    private String expiryDate;
    private String selectedCharity;

//    private Chip vegetables;
//    private Chip cookedFood;
//    private Chip packedFood;
//    private Chip fruit;
//    private Chip grains_bean_rice;
//    private Chip dairy;
//    private Chip others;

    public DonateFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_donate, container, false);
        edtDate = view.findViewById(R.id.edtDate);
        edtQuantity = view.findViewById(R.id.edtQuantity);
        actvCharity = view.findViewById(R.id.actvCharity);
        chipGroup = view.findViewById(R.id.chipGroup);
        btnNext = view.findViewById(R.id.btnNext);

        charityAsyncCall();

        //------auto complete text view--------
        actvCharity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCharity = charityResponseList.get(position).get_id();
            }
        });

        //-----date picker-------
        setUpDatePicker();

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });;
        // ------btnNext-------
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                storeRequestDonateValuesInStoredPreference();
                getInputValues();
                Intent intent = new Intent(getContext(), LocationConfirm.class);
                intent.putExtra("foodTypes", foodTypes);
                intent.putExtra("charity", selectedCharity);
                intent.putExtra("quantity", quantity);
                intent.putExtra("expiryDate", expiryDate);
                startActivity(intent);
            }
        });
        return view;
    }

        public void charityAsyncCall() {
            CharityAPI api = URL.getInstance().create(CharityAPI.class);
            Call<List<CharityResponse>> call = api.getCharitiesName();

            call.enqueue(new Callback<List<CharityResponse>>() {
                @Override
                public void onResponse(Call<List<CharityResponse>> call, Response<List<CharityResponse>> response) {
                    if (response.isSuccessful()) {
                        charityResponseList =  response.body();
                        nameList = new ArrayList<>();
                        for (CharityResponse charity: charityResponseList) {
                            nameList.add(charity.getName());
                        }

                        if (nameList.size() <= 0) {
                            Toast.makeText(getContext(), "No Charity Is Registered", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, nameList);
                        actvCharity.setAdapter(arrayAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<CharityResponse>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

//    private void storeRequestDonateValuesInStoredPreference () {
//        getInputValues();
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("DONATE_REQUEST", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("foodTypes", foodTypes);
//        editor.putString("quantity", quantity);
//        editor.putString("expiryDate", expiryDate);
//        editor.apply();
//    }
//

    private void getInputValues() {
        foodTypes = "";
        ArrayList<Integer> listCheckedChips = new ArrayList<>(chipGroup.getCheckedChipIds());
        for (Integer id: listCheckedChips) {
            switch (id) {
                case R.id.chip_vegetable:
                    foodTypes =  foodTypes.concat("vegetable,");
                    break;
                case R.id.chip_cookedFood:
                    foodTypes =  foodTypes.concat("cooked food,");
                    break;
                case R.id.chip_packed_food:
                    foodTypes =  foodTypes.concat("packed food,");
                    break;
                case R.id.chip_fruit:
                    foodTypes =  foodTypes.concat("fruit,");
                    break;
                case R.id.chip_grain_bean_rice:
                    foodTypes =  foodTypes.concat("grain bean rice,");
                    break;
                case R.id.chip_dairy:
                    foodTypes =  foodTypes.concat("dairy,");
                    break;
                case R.id.chip_others:
                    foodTypes =  foodTypes.concat("others");
                    break;
            }
        }
        quantity  =  edtQuantity.getText().toString();
        expiryDate = edtDate.getText().toString();
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
                updateLabel();
            }
        };
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtDate.setText(sdf.format(myCalendar.getTime()));
    }
}