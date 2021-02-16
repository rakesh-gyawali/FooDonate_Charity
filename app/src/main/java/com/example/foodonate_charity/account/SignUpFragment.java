package com.example.foodonate_charity.account;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.foodonate_charity.R;

import static android.content.Context.MODE_PRIVATE;

public class SignUpFragment extends Fragment {
    private Button btnNext;

    private TextView tvLogin;

    private EditText edtName;
    private TextView edtAddress;
    private TextView edtPhone;
    private TextView edtEmail;
    private TextView edtPassword;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;

    public SignUpFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        btnNext = view.findViewById(R.id.btnNext);
        tvLogin = view.findViewById(R.id.tvLogin);
        edtName = view.findViewById(R.id.edtName);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtPassword = view.findViewById(R.id.edtPassword);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateInputs()) return;
                storeToSharedPreference();

                Fragment selectProfilePicturesFragment = new SelectProfilePictureFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frameLayout, selectProfilePicturesFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });
        return view;
    }

    private void storeToSharedPreference() {
        getValuesFromEditText();
       SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("SIGN_UP", MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString("name", name);
       editor.putString("address", address);
       editor.putString("phone", phone);
       editor.putString("email", email);
       editor.putString("password", password);
       editor.apply();
    }
    private void getValuesFromEditText() {
        name = edtName.getText().toString();
        address = edtAddress.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
    }

    private boolean validateInputs() {
        return true;
    }
}