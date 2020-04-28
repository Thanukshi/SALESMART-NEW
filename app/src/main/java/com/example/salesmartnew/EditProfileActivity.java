package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText uUserName, uFullName, uEmail, uPassword, uConPass, uContact;
    ImageView uProfImage;
    Button update, delete;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        uUserName = findViewById(R.id.text1_EP);
        uFullName = findViewById(R.id.text2_EP);
        uEmail = findViewById(R.id.text3_EP);
        uPassword = findViewById(R.id.text4_EP);
        uConPass = findViewById(R.id.text5_EP);
        uContact = findViewById(R.id.text6_EP);
        uProfImage = findViewById(R.id.EPImage);
        update =findViewById(R.id.buttonUp);
        delete = findViewById(R.id.buttonDel);

        Intent intent = getIntent();
        String userNameEdit= intent.getStringExtra("userNameCustomer");
        String fullNameEdit = intent.getStringExtra("fullName");
        String emailEdit = intent.getStringExtra("emailCustomer");
        String passwordEdit = intent.getStringExtra("passwordCustomer");

        uUserName.setText(userNameEdit);
        uFullName.setText(fullNameEdit);
        uEmail.setText(emailEdit);
        uPassword.setText(passwordEdit);

        dbReference = FirebaseDatabase.getInstance().getReference("users");

    }

    public void update(View view){
        

    }
}
