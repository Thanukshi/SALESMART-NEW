package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    private ViewPager screenPage;
    WelcomeViewPageAdaptor welcomeViewPageAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        screenPage = findViewById(R.id.viewPager_WelcomeScreen);
        welcomeViewPageAdaptor = new 
    }
}
