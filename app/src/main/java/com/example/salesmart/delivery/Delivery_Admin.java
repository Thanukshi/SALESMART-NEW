package com.example.salesmart.delivery;
import com.example.salesmartnew.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.salesmartnew.*;

public class Delivery_Admin extends AppCompatActivity {
    Button buttonInsert,buttonSearch,buttonListAll,home;
    String username = "Default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery__admin);
        buttonInsert = findViewById(R.id.buttonAdmin);
        buttonSearch = findViewById(R.id.buttonCustomer);
        buttonListAll = findViewById(R.id.buttonListAll);
        home = findViewById(R.id.button4);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthome = new Intent(Delivery_Admin.this, DashBoard.class);
                intenthome.putExtra("username", username);
                startActivity(intenthome);
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delivery_Admin.this, insertdelivery.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        buttonListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delivery_Admin.this, ListAll.class);
                startActivity(intent);
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delivery_Admin.this, aview_Delivery.class);
                startActivity(intent);
            }
        });
    }
}
