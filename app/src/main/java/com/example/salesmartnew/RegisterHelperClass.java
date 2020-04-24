package com.example.salesmartnew;

public class RegisterHelperClass {

    private String fullName;

    private String emailCustomer;

    private String userNameCustomer;

    private String passwordCustomer;

    private String confirmPasswordCustomer;

    public RegisterHelperClass() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public String getUserNameCustomer() {
        return userNameCustomer;
    }

    public String getPasswordCustomer() {
        return passwordCustomer;
    }

    public String getConfirmPasswordCustomer() {
        return confirmPasswordCustomer;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public void setUserNameCustomer(String userNameCustomer) {
        this.userNameCustomer = userNameCustomer;
    }

    public void setPasswordCustomer(String passwordCustomer) {
        this.passwordCustomer = passwordCustomer;
    }

    public void setConfirmPasswordCustomer(String confirmPasswordCustomer) {
        this.confirmPasswordCustomer = confirmPasswordCustomer;
    }
}
