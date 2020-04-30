package com.example.salesmart.delivery;
import com.example.salesmartnew.DashBoard;
import com.example.salesmartnew.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.salesmart.salesmart.R;

public class Delivery_Customer extends AppCompatActivity {
    Button buttonInsert,buttonSearch,buttonListAll,home;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery__customer);

        buttonInsert = findViewById(R.id.buttonAdmin);
        buttonSearch = findViewById(R.id.buttonCustomer);
        buttonListAll = findViewById(R.id.buttonListAll);
        home = findViewById(R.id.button4);
        final Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthome = new Intent(Delivery_Customer.this, DashBoard.class);
                intenthome.putExtra("username", username);
                startActivity(intenthome);
            }
        });


        System.out.println(username);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delivery_Customer.this, insertdelivery.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        buttonListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delivery_Customer.this, ListAllCustomer.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delivery_Customer.this, cview_Delivery.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }
}
