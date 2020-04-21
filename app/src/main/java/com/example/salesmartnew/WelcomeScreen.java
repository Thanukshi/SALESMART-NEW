package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {

    private ViewPager screenPage;
    WelcomeViewPageAdaptor welcomeViewPageAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //fill list screen
        List<ScreenItems> myList = new ArrayList<>();
        m

        screenPage = findViewById(R.id.viewPager_WelcomeScreen);
        welcomeViewPageAdaptor = new WelcomeViewPageAdaptor(this,myList);
    }
}
