package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfileActivity extends AppCompatActivity {

    EditText uUserName, uFullName, uEmail, uPassword, uConPass, uContact;
    ImageView uProfImage;
    Button update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        uUserName = findViewById(R.id.text1_EP);
    }
}
