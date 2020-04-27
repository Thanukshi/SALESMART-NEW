package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class Profile extends AppCompatActivity {

    TextView userNameText;
    TextView fullNameText;
    TextView emailText;
    TextView passwordText;
    TextView contactText;
    ImageView profImage;

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

    }
}
