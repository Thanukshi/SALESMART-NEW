package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RegisterActivity extends AppCompatActivity {

    Button btRegister;
    RelativeLayout RL1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
