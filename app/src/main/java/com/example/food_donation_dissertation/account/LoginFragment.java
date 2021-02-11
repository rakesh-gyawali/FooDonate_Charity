package com.example.food_donation_dissertation.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_donation_dissertation.MainActivity;
import com.example.food_donation_dissertation.R;
import com.example.food_donation_dissertation.URL;
import com.example.food_donation_dissertation.account.userRegistrationDevelopment.UserRegistrationBLL;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private TextView tvSignUp;
    private Button btnLogin;
    private EditText edtPhone;
    private EditText edtPassword;

    private String phoneNo;
    private String password;
    private String firstname;
    private String lastname;
    final private String TAG = "LoginFragment";
    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        tvSignUp = view.findViewById(R.id.tvSignUp);
        btnLogin = view.findViewById(R.id.btnLogin);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtPassword = view.findViewById(R.id.edtPassword);

        tvSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignUp:
                Fragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, signUpFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.btnLogin:
                phoneNo = edtPhone.getText().toString();
                password = edtPassword.getText().toString();
                loginCall();
                break;


        }
    }

    private void loginCall(){
        if (!validateInputs()) return;
        URL.getStrictMode();

        UserRegistrationBLL bll = new UserRegistrationBLL(edtPhone.getText().toString(), edtPassword.getText().toString());
        if (bll.checkLogin()) {
            storeLoggedInStatusToSharedPreference();

            Fragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.frameLayout, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            Toast.makeText(getContext(), "loginCall fail ...", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeLoggedInStatusToSharedPreference() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("LOGIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("HAS_LOGGED_IN", true);
        editor.putString("TOKEN", URL.token);
        editor.apply();
    }

    private boolean validateInputs() {
        //check whether a phoneNo is Number or not ...
        try { Double.valueOf(phoneNo); }
        catch (NumberFormatException ex) {
            Log.i(TAG, ex.getLocalizedMessage());
            return false;
        }
        //null validation ...
        if (phoneNo.length() <= 0) return false;
        else if (password.length() <= 0) return false;
        return true;
    }

}