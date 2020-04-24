package com.example.salesmartnew;

public class RegisterHelperClass {

    String fullName;

    String emailCustomer;

    String userNameCustomer;

    String passwordCustomer;

    String confirmPasswordCustomer;

    public RegisterHelperClass(String fullName, String emailCustomer, String userNameCustomer, String passwordCustomer, String confirmPasswordCustomer) {
        this.fullName = fullName;
        this.emailCustomer = emailCustomer;
        this.userNameCustomer = userNameCustomer;
        this.passwordCustomer = passwordCustomer;
        this.confirmPasswordCustomer = confirmPasswordCustomer;
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
}
