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

    private EditText edtFirstname;
    private TextView edtLastname;
    private TextView edtPhone;
    private TextView edtPassword;

    private String firstname;
    private String lastname;
    private String phone;
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
        edtFirstname = view.findViewById(R.id.edtFirstname);
        edtLastname = view.findViewById(R.id.edtLastName);
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
       editor.putString("firstname", firstname);
       editor.putString("lastname", lastname);
       editor.putString("phone", phone);
       editor.putString("password", password);
       editor.apply();
    }
    private void getValuesFromEditText() {
        firstname = edtFirstname.getText().toString();
        lastname = edtLastname.getText().toString();
        phone = edtPhone.getText().toString();
        password = edtPassword.getText().toString();
    }


    private boolean validateInputs() {
        return true;
    }
}