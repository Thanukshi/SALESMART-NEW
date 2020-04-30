package com.example.salesmart.purchase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.salesmartnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Order extends AppCompatActivity {
    private Button addToCartButton;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice , productDescription ,productName;
    EditText Total;
    private String productID = "2"; ///meka comment karala
    Product products;
    int shpID =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

       // productID = getIntent().getStringExtra("pid");   ///meka uncomment karanna

        addToCartButton =(Button) findViewById(R.id.updatesp);
        numberButton =(ElegantNumberButton) findViewById(R.id.number_btn);
        productImage =(ImageView) findViewById(R.id.product_image_details);
        productName =(TextView) findViewById(R.id.product_name_details);
        productDescription =(TextView) findViewById(R.id.product_description_details);
        productPrice =(TextView) findViewById(R.id.product_price_details);
        Total =(EditText) findViewById(R.id.product_price_details2);
        getProductDetails(productID);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addingToCartList();
                ShoppingCart shp = new ShoppingCart();
                shp.setName(productName.getText().toString());
                shp.setImage(products.getImage());
                shp.setDescription(products.getDescription());
                shp.setPrice(products.getPrice());
                shp.setQuantity(numberButton.getNumber());
               shp.setTotal(Total.getText().toString());
                DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("shoppingcart");

                productsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        shpID = (int) dataSnapshot.getChildrenCount();
                        System.out.println(shpID);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                shpID++;
                shp.setID(String.valueOf(shpID));
                productsRef.child(String.valueOf(shpID)).setValue(shp);
                startActivity(new Intent(Order.this,ListAllCart.class));

            }
        });

    }
    private void getProductDetails(String productID) {

        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("products");

        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                     products = dataSnapshot.getValue(Product.class);

                    productName.setText(products.getPname());
                    productPrice.setText(products.getPrice());
                    productDescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(productImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    }
