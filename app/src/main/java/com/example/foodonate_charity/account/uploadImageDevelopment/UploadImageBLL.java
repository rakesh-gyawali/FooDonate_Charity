package com.example.foodonate_charity.account.uploadImageDevelopment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.loader.content.CursorLoader;

import com.example.foodonate_charity.URL;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class UploadImageBLL {
    String path = "";
    public void previewImage(String imagePath, ImageView imgView) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgView.setImageBitmap(myBitmap);
        }
    }

    public  void MakeStrict() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    public  String getRealPathFromUri(Uri uri, Context context) {
        String [] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    public  Bitmap loadImageFromURL(String imagePath) {
        MakeStrict();
        Bitmap bmp;
        try {
            java.net.URL url = new java.net.URL(imagePath);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bmp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean checkImageUpload(String imagePath) {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile", file.getName(), requestBody);

        UploadImageAPI imageAPI = URL.getInstance().create(UploadImageAPI.class);
        Call<UploadImageResponse> responseCall = imageAPI.uploadImage(body);

        MakeStrict();

        try {
            Response<UploadImageResponse> responseResponse = responseCall.execute();
            path = responseResponse.body().getFilename();
            return !path.equals("");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String returnFilename() {
        return path;
    }


}
