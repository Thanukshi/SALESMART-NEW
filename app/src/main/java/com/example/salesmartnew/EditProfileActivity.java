package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText uContact, uFullName, uEmail, uPassword, uConPass;
    ImageView uProfImage;
    Button update, delete;
    DatabaseReference dbReference;
    String un, fn, em, upw, ucpw, ucon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        uContact = findViewById(R.id.text1_EP);
        uFullName = findViewById(R.id.text2_EP);
        uEmail = findViewById(R.id.text3_EP);
        uPassword = findViewById(R.id.text4_EP);
        uConPass = findViewById(R.id.text5_EP);
        uProfImage = findViewById(R.id.EPImage);
        update =findViewById(R.id.buttonUp);
        delete = findViewById(R.id.buttonDel);

        Intent intent = getIntent();
        String userNameEdit= intent.getStringExtra("contactNo");
        String fullNameEdit = intent.getStringExtra("fullName");
        String emailEdit = intent.getStringExtra("emailCustomer");
        String passwordEdit = intent.getStringExtra("passwordCustomer");

        uContact.setText(userNameEdit);
        uFullName.setText(fullNameEdit);
        uEmail.setText(emailEdit);
        uPassword.setText(passwordEdit);

        dbReference = FirebaseDatabase.getInstance().getReference("users");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    public void update( ){
        if(isUserNameChanged() || isFullNameChanged() || isEmailChanged() || isPasswordChanged() || isConfirmPassChanged() ){
            Toast.makeText(this, "Data has been updated.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Data is same and can not be updated.", Toast.LENGTH_LONG).show();
        }



    }

    private boolean isUserNameChanged() {
        if(!un.equals(uContact.getText().toString())){
            dbReference.child(un).child("contactNo").setValue(uContact.getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isFullNameChanged() {
        if(!fn.equals(uFullName.getText().toString())){
            dbReference.child(un).child("contactNo").setValue(uFullName.getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isEmailChanged() {
        if(!em.equals(uEmail.getText().toString())){
            dbReference.child(un).child("contactNo").setValue(uEmail.getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if(!upw.equals(uPassword.getText().toString())){
            dbReference.child(un).child("contactNo").setValue(uPassword.getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private boolean isConfirmPassChanged() {

        if(!ucpw.equals(uConPass.getText().toString())){
            dbReference.child(un).child("contactNo").setValue(uConPass.getText().toString());
            return true;
        }else{
            return false;
        }
    }

}
