package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {

    private ViewPager screenPage;
    WelcomeViewPageAdaptor welcomeViewPageAdaptor;
    TabLayout tabIndicator;
    ImageView btnNxt;
    int position = 0;
    Button btnGetStarted;
    Animation bottomAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (restorePrefData()){
            Intent welcomeIntent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(welcomeIntent);
            finish();   
        }

        setContentView(R.layout.activity_welcome_screen);


        //hide the action bar
        //getSupportActionBar().hide();

        //initialize the view
        btnNxt = findViewById(R.id.imageButton_Welcome);
        tabIndicator = findViewById(R.id.tabLayout1_WelcomeScreen);
        btnGetStarted = findViewById(R.id.getStarted_Welcome);
        bottomAni = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_animation);

        //fill list screen
        final List<ScreenItems> myList = new ArrayList<>();
        myList.add(new ScreenItems("Kitchen Tools", "You can buy new types of kitchen equipments for your kitchen.", R.drawable.onboard_kitchen));
        myList.add(new ScreenItems("Fast Delivery", "You can buy new types of kitchen equipments for your kitchen.", R.drawable.onboard_kitchen5));
        myList.add(new ScreenItems("Easy Payments", "You can buy new types of kitchen equipments for your kitchen.", R.drawable.onboard_kitchen4));

        screenPage = findViewById(R.id.viewPager_WelcomeScreen);
        welcomeViewPageAdaptor = new WelcomeViewPageAdaptor(this, myList);
        screenPage.setAdapter(welcomeViewPageAdaptor);

        //setup tab layout with view pager
        tabIndicator.setupWithViewPager(screenPage);

        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPage.getCurrentItem();
                if (position < myList.size()) {
                    position++;
                    screenPage.setCurrentItem(position);
                }

                if (position == myList.size()) {
                    loadLastScreen();
                }
            }


        });

        //set tabular screen
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == myList.size() - 1){

                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //start another activity
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( WelcomeScreen.this, MainActivity.class );
                startActivity(intent);

                saveData();
                finish();
            }
        });
    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        boolean isWelcomeActivityBefore = pref.getBoolean("isWelcomeOpened", false);
        return isWelcomeActivityBefore;
    }

    private void saveData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isWelcomeOpen", true);
        editor.commit();
    }

    private void loadLastScreen() {
        btnNxt.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(bottomAni);
    }
}
