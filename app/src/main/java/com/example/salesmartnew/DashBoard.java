package com.example.salesmartnew;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DashBoard extends AppCompatActivity {


    //Drawer Menu Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        
        //Hooks for the navigation
        drawerLayout = findViewById(R.id.RL1_Dash);
        navigationView = findViewById(R.id.nav);


    }
}
