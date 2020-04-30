package com.example.salesmart.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.salesmartnew.R;
import com.example.salesmart.product.Model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Product> list;
    Product_Adapter product_adapter;
    ImageView cardView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_products);

        recyclerView = (RecyclerView) findViewById(R.id.myrecyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Product>();
        cardView = (ImageView) findViewById(R.id.productimg);



        reference = FirebaseDatabase.getInstance().getReference().child("products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Product p = dataSnapshot1.getValue(Product.class);
                    list.add(p);

                }
                product_adapter = new Product_Adapter(MainActivity.this,list);
                recyclerView.setAdapter(product_adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



            


    }



}
