package com.example.foodonate_charity.account.uploadImageDevelopment;

public class UploadImageResponse {
    private String filename;

    public UploadImageResponse(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
