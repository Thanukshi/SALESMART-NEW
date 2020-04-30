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
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.salesmartnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class updatedeletecart extends AppCompatActivity {
    private Button updatesp,deletesp;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName;
    private String shpid;
    EditText product_price_Total;
    ShoppingCart cart;
    ShoppingCart shp;
    int shpID = 0;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedeletecart);


            shpid = getIntent().getStringExtra("shpid");

            updatesp =(Button) findViewById(R.id.updatesp);
            deletesp =(Button) findViewById(R.id.deletesp);
            numberButton =(ElegantNumberButton) findViewById(R.id.number_btn);
            productImage =(ImageView) findViewById(R.id.product_image_details);
            productName =(TextView) findViewById(R.id.product_name_details);
            productDescription =(TextView) findViewById(R.id.product_description_details);
            productPrice =(TextView) findViewById(R.id.product_price_details);
            product_price_Total = (EditText) findViewById(R.id.product_price_Total);

            getShoppingDetails(shpid);

        updatesp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //addingToCartList();
                    shp = new ShoppingCart();
                    shp.setName(productName.getText().toString());
                    shp.setImage(cart.getImage());
                    shp.setDescription(cart.getDescription());
                    shp.setPrice(cart.getPrice());
                    shp.setQuantity(numberButton.getNumber());
                    shp.setTotal(product_price_Total.getText().toString());

                    DatabaseReference Ref = FirebaseDatabase.getInstance()
                            .getReference().child("shoppingcart");

                    shp.setID(String.valueOf(shpid));
                    Ref.child(String.valueOf(shpid)).setValue(shp);
                    Toast.makeText(updatedeletecart.this,"Product: "+shp.getName() +", successfully Updated",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(updatedeletecart.this,ListAllCart.class));

                }
            });

            deletesp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference()
                            .child("shoppingcart");
                    Ref.child(shpid).removeValue();
                    Toast.makeText(updatedeletecart.this,"Product: "+shp.getName() +", successfully Deleted",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(updatedeletecart.this,ListAllCart.class));
                }
            });


        }

        private void getShoppingDetails(String productID) {

            DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("shoppingcart");

            productsRef.child(productID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        cart = dataSnapshot.getValue(ShoppingCart.class);
                        Picasso.get().load(cart.getImage()).into(productImage);
                        productName.setText(cart.getName());
                        productPrice.setText(cart.getPrice());
                        productDescription.setText(cart.getDescription());
                        product_price_Total.setText(cart.getTotal());
                        int x = Integer.valueOf(cart.getQuantity());
                        numberButton.setNumber(cart.getQuantity());
                        product_price_Total.setText(cart.getTotal());



                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }



