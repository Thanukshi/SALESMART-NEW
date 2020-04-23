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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;



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
        //RL1 = findViewById(R.id.RL1_Register);
        //bacKArrow = findViewById(R.id.image1_Register);
        //rFullName = findViewById(R.id.ET1_Register);
       // rEmail = findViewById(R.id.ET2_Register);
        //rUserName = findViewById(R.id.ET3_Register);
        //rPassword = findViewById(R.id.ET4_Register);
        //rConfirmPass = findViewById(R.id.ET5_Register);



        handler.postDelayed(runnable, 1000);

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
