package com.example.salesmart.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.salesmart.delivery.Delivery_Admin;
import com.example.salesmart.purchase.ListAllContact;
import com.example.salesmartnew.AdminCustomer;
import com.example.salesmartnew.R;

public class Admin_Panel extends AppCompatActivity {

    Button pur,cust,feed,buttoncust,buttonProduct,buttonpurchase,buttonDelivery,buttonfeed;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__panel);

        pur = (Button)findViewById(R.id.buttonProduct);
        cust = (Button)findViewById(R.id.buttonfeed);
        feed = (Button)findViewById(R.id.buttoncust);

        buttoncust = findViewById(R.id.buttoncust);
        buttonProduct = findViewById(R.id.buttonProduct);
        buttonpurchase = findViewById(R.id.buttonpurchase);
        buttonDelivery = findViewById(R.id.buttonDelivery);
        buttonfeed = findViewById(R.id.buttonfeed);

        Intent intent = getIntent();
        username= intent.getStringExtra("username");


        buttoncust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Panel.this, AdminCustomer.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        buttonProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Panel.this, Admin.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        buttonpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Panel.this, AdminCustomer.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        buttonDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Panel.this, Delivery_Admin.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        buttonfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Panel.this, ListAllContact.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });



    }
}
