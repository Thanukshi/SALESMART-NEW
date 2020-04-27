package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    ImageView drawerMenu;

    //Drawer Menu Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;
    TextView viewName;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        drawerMenu = findViewById(R.id.menuIcon_Dash);
        contentView = findViewById(R.id.contentL);
        viewName = findViewById(R.id.navText_menu);


        //Hooks for the navigation
        drawerLayout = findViewById(R.id.RL1_Dash);
        navigationView = findViewById(R.id.nav);



        fragmentManager = getSupportFragmentManager();
        fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragProfile, new DashBoard());
        fragmentTransaction.commit();



        navigationDrawer();
        showFullName();

    }
    //display name in the nav bar
    private void showFullName() {
        Intent intent = getIntent();
        String userNameNav = intent.getStringExtra("fullName");

        viewName.setText(userNameNav);
    }

    //Navigation Drawer Functions

    private void navigationDrawer() {
        //navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setCheckedItem(R.id.db1);

        drawerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        onCreateAnimationOnNavigation();
    }

    private void onCreateAnimationOnNavigation() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)  {
        if(item.getItemId() == R.id.db1){
            //load default fragment
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragProfile, new ProfileNav());
            fragmentTransaction.commit();
        }

        if(item.getItemId() == R.id.db4){
            //load default fragment
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragProfile, new ProfileNav());
            fragmentTransaction.commit();
        }
        return true;
    }
}
