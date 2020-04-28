package com.example.salesmartnew;

public class RegisterHelperClass {

    String image;

    String fullName;

    String emailCustomer;

    String contactNo;

    String passwordCustomer;

    String confirmPasswordCustomer;





    public RegisterHelperClass() {
    }

    public RegisterHelperClass( String image,String fullName, String emailCustomer, String contactNo, String passwordCustomer, String confirmPasswordCustomer){
        this.image = image;
        this.fullName = fullName;
        this.emailCustomer = emailCustomer;
        this.contactNo = contactNo;
        this.passwordCustomer = passwordCustomer;
        this.confirmPasswordCustomer = confirmPasswordCustomer;

    }

    public RegisterHelperClass(String fullName, String emailCustomer, String contactNo, String passwordCustomer, String confirmPasswordCustomer ) {
        this.fullName = fullName;
        this.emailCustomer = emailCustomer;
        this.contactNo = contactNo;
        this.passwordCustomer = passwordCustomer;
        this.confirmPasswordCustomer = confirmPasswordCustomer;

    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public void setPasswordCustomer(String passwordCustomer) {
        this.passwordCustomer = passwordCustomer;
    }

    public void setConfirmPasswordCustomer(String confirmPasswordCustomer) {
        this.confirmPasswordCustomer = confirmPasswordCustomer;
    }

    public void setContactNo(String contactNo) {

        this.contactNo = contactNo;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public String getPasswordCustomer() {
        return passwordCustomer;
    }

    public String getConfirmPasswordCustomer() {
        return confirmPasswordCustomer;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getImage() {
        return image;
    }
}
