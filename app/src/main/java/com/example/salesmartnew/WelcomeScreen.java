package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {

    private ViewPager screenPage;
    WelcomeViewPageAdaptor welcomeViewPageAdaptor;
    TabLayout tabIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome_screen);


        //hide the action bar
        //getSupportActionBar().hide();

        //initialize the view
        tabIndicator = findViewById(R.id.tabLayout1_WelcomeScreen);

        //fill list screen
        List<ScreenItems> myList = new ArrayList<>();
        myList.add(new ScreenItems("Kitchen Tools", "You can buy new types of kitchen equipments for your kitchen.",R.drawable.onboard_kitchen));
        myList.add(new ScreenItems("Fast Delivery", "You can buy new types of kitchen equipments for your kitchen.",R.drawable.onboard_kitchen5));
        myList.add(new ScreenItems("Easy Payments", "You can buy new types of kitchen equipments for your kitchen.",R.drawable.onboard_kitchen4));

        screenPage = findViewById(R.id.viewPager_WelcomeScreen);
        welcomeViewPageAdaptor = new WelcomeViewPageAdaptor(this,myList);
        screenPage.setAdapter(welcomeViewPageAdaptor);

        //setup tab layout with view pager
        tabIndicator.setupWithViewPager(screenPage);
    }
}
