package com.example.salesmartnew;

import android.graphics.Bitmap;

public class RegisterModel {

    private Bitmap image;
    private String fullName;
    private String userName;
    private String password;
    private String confirmPassword;


    public RegisterModel(Bitmap image, String fullName, String userName, String password, String confirmPassword) {
        this.image = image;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    

    public Bitmap getImage() {
        return image;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
