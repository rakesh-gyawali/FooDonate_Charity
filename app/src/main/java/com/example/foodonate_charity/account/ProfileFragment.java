package com.example.foodonate_charity.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodonate_charity.R;
import com.example.foodonate_charity.URL;
import com.example.foodonate_charity.account.uploadImageDevelopment.UploadImageBLL;
import com.example.foodonate_charity.account.userDevelopment.UserBLL;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.net.MalformedURLException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private TextView tvName;
    private TextView tvPhoneNo;
    private MaterialToolbar toolbar;
    private String profilePicture = "";
    private CircleImageView imgProfile;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tvName = view.findViewById(R.id.tvName);
        tvPhoneNo = view.findViewById(R.id.tvPhoneNo);
        toolbar = view.findViewById(R.id.topAppBar);
        imgProfile = view.findViewById(R.id.imgProfile);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.logOut) {
                    //To Erase Login Data ...
                    SharedPreferences savedData = view.getContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                    savedData.edit().clear().apply();

                    Fragment loginFragment = new LoginFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayout, loginFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                return false;
            }
        });

        userCall();

        return view;
    }
    private void userCall() {
        URL.getStrictMode();
        UserBLL bll = new UserBLL();
        if (bll.checkGetUser()) {
            tvName.setText(bll.returnUser().getFirstName() + " " +  bll.returnUser().getLastName());
            tvPhoneNo.setText( bll.returnUser().getPhoneNo());
            profilePicture = bll.returnUser().getProfilePicture();

            profilePictureCall();

        } else {
            Toast.makeText(getContext(), "userCall Failed ...", Toast.LENGTH_SHORT).show();
        }

    }

    private void profilePictureCall() {
        // if no profile picture then ...
        if (profilePicture == null) return;
        // if there is profile picture ...
        UploadImageBLL imageBLL = new UploadImageBLL();
        imageBLL.MakeStrict();
        String imagePath = URL.IMAGE_BASE_URL +"uploads/" + profilePicture;
        try {
            java.net.URL url = new java.net.URL(imagePath);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imgProfile.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            Toast.makeText(getContext(), "MalformedURLException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(getContext(), "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}