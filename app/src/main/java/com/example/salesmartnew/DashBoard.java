package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salesmartnew.HomeAdapterHelperClass.EquipmentAdapter;
import com.example.salesmartnew.HomeAdapterHelperClass.EquipmentHelperClass;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    ImageView drawerMenu;

    //Drawer Menu Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;
    TextView viewName;
    String userNameEdit;
    RecyclerView eqRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        drawerMenu = findViewById(R.id.menuIcon_Dash);
        contentView = findViewById(R.id.contentL);
        viewName = findViewById(R.id.navText_menu);
        eqRecyclerView = findViewById(R.id.rv_dash);

        //called the item cycle
        equipmentRecyclerView();

        //Hooks for the navigation
        drawerLayout = findViewById(R.id.RL1_Dash);
        navigationView = findViewById(R.id.nav);

        Intent intent = getIntent();
        userNameEdit= intent.getStringExtra("contactNo");



        navigationDrawer();
        showFullName();

    }

    private void equipmentRecyclerView() {
        eqRecyclerView.setHasFixedSize(true);
        eqRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));

        ArrayList<EquipmentHelperClass> equipmentLocation = new ArrayList<>();
        equipmentLocation.add(new EquipmentHelperClass(R.drawable.kitchen_r6, "All Equipments At One Place", "You can make a beautiful kitchen in your home after buying equipments from us. We have very goods items for you."));
        equipmentLocation.add(new EquipmentHelperClass(R.drawable.kitchen_r2, "Spoons", "You can buy different types of spoons. Plastics Spoons, Stainless Steel Spoons"));
        equipmentLocation.add(new EquipmentHelperClass(R.drawable.kitchen_r5, "Refrigerators", "All types of Refrigerators ae available. Single door, Two door and Side By Side."));
        equipmentLocation.add(new EquipmentHelperClass(R.drawable.kitchen_r1, "Grind Mixer", "Easy to work with our mixers. Juice Mixer, Magic Bullet"));
        equipmentLocation.add(new EquipmentHelperClass(R.drawable.kitchen_r7, "Enjoy Your Life", "Enjoy at your kitchen with using our equipments.. Happy Life..."));


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
        navigationView.setCheckedItem(R.id.home_menu);
        final ImageView navImage = findViewById(R.id.navImage);

        DatabaseReference dbf = FirebaseDatabase.getInstance().getReference().child("users").child(userNameEdit);
        dbf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegisterHelperClass rh = (RegisterHelperClass) dataSnapshot.getValue(RegisterHelperClass.class);
                Picasso.get().load(rh.getImage()).into(navImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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
        int id = item.getItemId();

        if(id == R.id.home_menu){
            Intent on = new Intent(DashBoard.this,DashBoard.class);
            startActivity(on);
        }

        else if(id == R.id.profile_menu){
            Intent intent = getIntent();
             userNameEdit= intent.getStringExtra("contactNo");
            String fullNameEdit = intent.getStringExtra("fullName");
            String emailEdit = intent.getStringExtra("emailCustomer");
            String passwordEdit = intent.getStringExtra("passwordCustomer");
            String url = intent.getStringExtra("url");
            Intent profIntent = new Intent(DashBoard.this, Profile.class);
            profIntent.putExtra("contactNo",userNameEdit);
            profIntent.putExtra("fullName",fullNameEdit);
            profIntent.putExtra("emailCustomer",emailEdit);
            profIntent.putExtra("passwordCustomer",passwordEdit);
            startActivity(profIntent);
        }
        else if(id == R.id.logOut_menu){
            startActivity(new Intent(DashBoard.this,LoginActivity.class));
        }
        else if (id == R.id.setting_menu){

            Intent intent = new Intent(DashBoard.this, SettingsActivity.class);
            intent.putExtra("contactNo",userNameEdit);
            startActivity(intent);
        }

        else if (id == R.id.rating_menu){

            Intent intent = new Intent(DashBoard.this, RatingUs.class);
            intent.putExtra("contactNo",userNameEdit);
            startActivity(intent);
        }


        return true;
    }
}
