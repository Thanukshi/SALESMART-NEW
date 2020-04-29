package com.example.salesmart.delivery;
import com.example.salesmart.R;
public class Validation {


    public final static boolean isValidPhoneNumber(CharSequence target) {

        String validNumber = "^?[0-9]{8,15}$";

        if (target == null || target.length() < 6 || target.length() > 13) {
            return false;


        } else {
            //  return android.util.Patterns.PHONE.matcher(target).matches();
            return true;

    }}



}
