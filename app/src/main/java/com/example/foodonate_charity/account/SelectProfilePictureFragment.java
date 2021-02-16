package com.example.foodonate_charity.account;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodonate_charity.R;
import com.example.foodonate_charity.URL;
import com.example.foodonate_charity.account.uploadImageDevelopment.UploadImageBLL;
import com.example.foodonate_charity.account.charityRegistrationDevelopment.UserRegistrationBLL;

import static android.app.Activity.RESULT_OK;

public class SelectProfilePictureFragment extends Fragment implements View.OnClickListener {
    private ImageView imgPP;
    private Button btnSelect;

    private Integer CHOOSE_FROM_GALLERY = 0;
    private Integer CHOOSE_FROM_CAMERA = 1;

    private String imagePath;
    private String file_name;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String logo;
    private String password;

    private boolean isImageUploaded;

    private AlertDialog.Builder builder;
    private UploadImageBLL uploadImageBLL;


    public SelectProfilePictureFragment() {
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
        View view = inflater.inflate(R.layout.fragment_select_profile_picture, container, false);
        imgPP = view.findViewById(R.id.imgPP);
        btnSelect = view.findViewById(R.id.btnSelect);

        btnSelect.setOnClickListener(this);
        imgPP.setOnClickListener(this);
        isImageUploaded = false;
        return view;
    }

    @Override
    public void onClick(View v) {
        getAddressFromSharedPreference();
        if (isImageUploaded)  {
            registerCall();
            return;
        }
        checkCameraPermission();
        popUpSelectFromCameraOrGallery();
    }

    private void registerCall() {
        URL.getStrictMode();
        UserRegistrationBLL bll = new UserRegistrationBLL(phone, password, name, email, logo, address);
        if (bll.checkRegister()) {
            Fragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.frameLayout, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            Toast.makeText(getContext(), "registerCall done ...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "registerCall fail ...", Toast.LENGTH_SHORT).show();
        }

    }

    private void getAddressFromSharedPreference() {
        SharedPreferences savedData = this.getActivity().getSharedPreferences("SIGN_UP", Context.MODE_PRIVATE);
        name =  savedData.getString("name", "");
        email =  savedData.getString("email", "");
        phone =  savedData.getString("phone", "");
        address =  savedData.getString("address", "");
        password =  savedData.getString("password", "");

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || address.isEmpty()) {
            Toast.makeText(getContext(), "Error while getting value from shared preference ...", Toast.LENGTH_LONG).show();
        }
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    private void popUpSelectFromCameraOrGallery() {
        final CharSequence[] options = { "Choose from Gallery","Cancel" };
        builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Add Profile Pic!")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("debug", String.valueOf(which));

                        if (which == CHOOSE_FROM_GALLERY) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, 0);
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uploadImageBLL = new UploadImageBLL();
        if (resultCode == RESULT_OK) {
            if ( data == null ) {
                Toast.makeText(getContext(), "Please Select Image", Toast.LENGTH_LONG).show();
            }
        }
        Uri uri = data.getData();
        imagePath = uploadImageBLL.getRealPathFromUri(uri, getContext());
        uploadImageBLL.previewImage(imagePath, imgPP);

        if (uploadImageBLL.checkImageUpload(imagePath)) {
            logo = uploadImageBLL.returnFilename();
            btnSelect.setText("Create Account");
            isImageUploaded = true;
            Toast.makeText(getContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Upload Image Failed ... ", Toast.LENGTH_SHORT).show();
        }
    }
}