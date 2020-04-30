package com.example.salesmart.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.salesmartnew.R;

public class Admin extends AppCompatActivity {

    Button add,view,cst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        add = findViewById(R.id.buttonpur);
        view = findViewById(R.id.buttonfeed);
        cst = findViewById(R.id.buttonviewcust);


        cst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityProduct.class);
                startActivity(i);
            }

        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Category_Product.class);
                startActivity(i);
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);

            }
        });
    }
}
