package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Validation;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    AwesomeValidation awesomeValidation;

    private static final int PICK_IMAGE = 1;

    EditText rFullName, rEmail, rUserName, rPassword, rConfirmPass;
    Button btRegister;
    RelativeLayout RL1;
    ImageView bacKArrow;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            RL1.setVisibility(View.VISIBLE);
            bacKArrow.setVisibility(View.VISIBLE);
            btRegister.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialize the view
        RL1 = findViewById(R.id.RL1_Register);
        bacKArrow = findViewById(R.id.image1_Register);
        rFullName = findViewById(R.id.ET1_Register);
        rEmail = findViewById(R.id.ET2_Register);
        rUserName = findViewById(R.id.ET3_Register);
        rPassword = findViewById(R.id.ET4_Register);
        rConfirmPass = findViewById(R.id.ET5_Register);

        handler.postDelayed(runnable, 1000);

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        //add validation for name
        awesomeValidation.addValidation(this,R.id.ET1_Register, RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        //add validation for email
        awesomeValidation.addValidation(this,R.id.ET2_Register, Patterns.EMAIL_ADDRESS, R.string.invalid_email);

        //add validation for userName
        awesomeValidation.addValidation(this,R.id.ET3_Register,RegexTemplate.NOT_EMPTY,R.string.invalid_username);

        //add validation for password
        awesomeValidation.addValidation(this,R.id.ET4_Register,".{6,}",R.string.invalid_password);

        
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cName = rFullName.getText().toString();
                String cEmail = rEmail.getText().toString();
                String cUserName = rUserName.getText().toString();
                String cPass = rPassword.getText().toString();
                String cConfirm = rConfirmPass.getText().toString();



            }
        });


        bacKArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goBack);
            }
        });

       //btRegister = findViewById(R.id.button1_Register);



    }


}
