package com.example.salesmart.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.salesmartnew.R;

public class Category_Product extends AppCompatActivity {

    private ImageView pan,electric,spoons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__product);

        pan = (ImageView) findViewById(R.id.pan);
        electric= (ImageView)findViewById(R.id.electric);
        spoons = (ImageView) findViewById(R.id.spoons);

        pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(Category_Product.this,Add_Product.class);
                intent.putExtra("category","pan");
                startActivity(intent);
            }
        });

       electric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(Category_Product.this,Add_Product.class);
                intent.putExtra("category","electric");
                startActivity(intent);
            }
        });

       spoons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(Category_Product.this,Add_Product.class);
                intent.putExtra("category","spoons");
                startActivity(intent);
            }
        });
    }
}
