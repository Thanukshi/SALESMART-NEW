package com.example.salesmartnew;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class DashBoard extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  DashBoardFragmentViewPagerAdapter dashBoardFragmentViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        tabLayout = (TabLayout) findViewById(R.id.tabLayOut_Dash);
        viewPager = (ViewPager) findViewById(R.id.viewPager_Dash);
        dashBoardFragmentViewPagerAdapter = new DashBoardFragmentViewPagerAdapter(getSupportFragmentManager());


        dashBoardFragmentViewPagerAdapter.AddFragment(new DashBoardFragmentHome(), "Home");
        dashBoardFragmentViewPagerAdapter.AddFragment(new DashBoardFragmentMessage(), "Message");
        dashBoardFragmentViewPagerAdapter.AddFragment(new DashBoardFragmentCart(), "Cart");
        dashBoardFragmentViewPagerAdapter.AddFragment(new DashBoardFragmentAccount(), "Account");

        viewPager.setAdapter(dashBoardFragmentViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_message);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_shopping_cart);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_person);


    }
}
