package com.example.salesmart.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.salesmartnew.R;

public class Admin_Panel extends AppCompatActivity {

    Button pur,cust,feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__panel);

        pur = (Button)findViewById(R.id.buttonpur);
        cust = (Button)findViewById(R.id.buttonfeed);
        feed = (Button)findViewById(R.id.buttoncust);

    }
}
