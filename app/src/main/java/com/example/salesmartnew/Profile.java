package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    TextView fullNameText;
    TextView emailText;
    TextView passwordText;
    TextView contactText;
    ImageView profImage;
    Button editProfileButton;

    //FirebaseDatabase profDb;
    //DatabaseReference profReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullNameText = findViewById(R.id.profText1);
        contactText = findViewById(R.id.text1__ProfLL);
        emailText = findViewById(R.id.text2__ProfLL);
        passwordText = findViewById(R.id.text3__ProfLL);
        profImage = findViewById(R.id.profImage);
        editProfileButton = findViewById(R.id.button);

        //profDb = FirebaseDatabase.getInstance();
        //profReference = profDb.getReference("users");

        showFullDetails();

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String userNameEdit= intent.getStringExtra("contactNo");
                String fullNameEdit = intent.getStringExtra("fullName");
                String emailEdit = intent.getStringExtra("emailCustomer");
                String passwordEdit = intent.getStringExtra("passwordCustomer");

                Intent profIntent = new Intent(Profile.this, EditProfileActivity.class);
                profIntent.putExtra("contactNo",userNameEdit);
                profIntent.putExtra("fullName",fullNameEdit);
                profIntent.putExtra("emailCustomer",emailEdit);
                profIntent.putExtra("passwordCustomer",passwordEdit);
                startActivity(profIntent);
            }
        });
    }
    private void showFullDetails() {
        Intent intent = getIntent();

        //set values as String

        String userNameEdit= intent.getStringExtra("contactNo");
        String fullNameEdit = intent.getStringExtra("fullName");
        String emailEdit = intent.getStringExtra("emailCustomer");
        String passwordEdit = intent.getStringExtra("passwordCustomer");

        contactText.setText(userNameEdit);
        fullNameText.setText(fullNameEdit);
        emailText.setText(emailEdit);
        passwordText.setText(passwordEdit);

    }
}
