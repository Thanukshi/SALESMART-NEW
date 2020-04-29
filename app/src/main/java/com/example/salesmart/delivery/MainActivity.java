package com.example.salesmart.delivery;
import com.example.salesmartnew.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button buttonAdmin,buttonCustomer,buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_M);

        buttonAdmin = findViewById(R.id.buttonAdmin);
        buttonCustomer = findViewById(R.id.buttonCustomer);

        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Delivery_Admin.class);
                startActivity(intent);
            }
        });

        buttonCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Delivery_Customer.class);
                startActivity(intent);
            }
        });

    }
}
