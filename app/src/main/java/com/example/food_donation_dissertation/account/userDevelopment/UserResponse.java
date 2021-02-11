package com.example.food_donation_dissertation.account.userDevelopment;

public class UserResponse {
    private String phoneNo;
    private String firstName;
    private String lastName;
    private String profilePicture;

    public UserResponse(String phoneNo, String firstName, String lastName, String profilePicture) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
