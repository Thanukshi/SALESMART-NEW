package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.awt.font.TextAttribute;

public class Profile extends AppCompatActivity {

    TextView userNameText;
    TextView fullNameText;
    TextView emailText;
    TextView passwordText;
    TextView contactText;
    ImageView profImage;

    //FirebaseDatabase profDb;
    //DatabaseReference profReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userNameText = findViewById(R.id.profText1);
        fullNameText = findViewById(R.id.text1__ProfLL);
        emailText = findViewById(R.id.text2__ProfLL);
        passwordText = findViewById(R.id.text3__ProfLL);
        contactText = findViewById(R.id.text4__ProfLL);
        profImage = findViewById(R.id.profImage);

        //profDb = FirebaseDatabase.getInstance();
        //profReference = profDb.getReference("users");

        showFullDetails();



    }

    private void showFullDetails() {
        Intent intent = getIntent();

        //set values as String

        String userNameEdit= intent.getStringExtra("userNameCustomer");
        String fullNameEdit = intent.getStringExtra("fullName");
        String emailEdit = intent.getStringExtra("emailCustomer");
        String passwordEdit = intent.getStringExtra("passwordCustomer");

        userNameText.setText(userNameEdit);
        fullNameText.setText(fullNameEdit);
        emailText.setText(emailEdit);
        passwordText.setText(passwordEdit);
    }
}
