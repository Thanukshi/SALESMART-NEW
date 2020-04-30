package com.example.salesmart.delivery;
import com.example.salesmartnew.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.salesmart.salesmart.R;


public class insertdelivery extends AppCompatActivity {

    EditText txtProID, txtproName, txtcusName, txtAddress,txtPhone, txtQty, txtPrice;
    Button btnAddDelivery,btnClear,btnNext;
    Delivery del;
    String id;
    int ID;
    DatabaseReference dbf;
    private ProgressDialog pd;
    private TextView chooseFile;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdelivery);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        txtcusName = findViewById(R.id.txtCusName);
        txtAddress = findViewById(R.id.txtCusAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtProID = findViewById(R.id.txtProID);
        txtproName = findViewById(R.id.txtProName);
        txtQty = findViewById(R.id.txtQty);
        txtPrice = findViewById(R.id.txtPrice);

        btnAddDelivery = findViewById(R.id.btnAddDelivery);
        btnClear = findViewById(R.id.btnClear);

        dbf = FirebaseDatabase.getInstance().getReference().child("Delivery");
        dbf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ID = (int) dataSnapshot.getChildrenCount();
                System.out.println(ID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnAddDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtcusName.getText().toString().isEmpty()){
                    txtcusName.setError("Please Enter Name");


                }
                else if(txtAddress.getText().toString().isEmpty()){
                    txtAddress.setError("Please Enter Price");


                }else if(txtPhone.getText().toString().isEmpty()){
                    txtPhone.setError("Please Enter Description");


                }else if(!Validation.isValidPhoneNumber(txtPhone.getText().toString())){
                    txtPhone.setError("Please Enter Valid Phone Number");
                }
                else if(txtProID.getText().toString().isEmpty()){
                    txtProID.setError("Please Enter Description");


                }else if(txtproName.getText().toString().isEmpty()){
                    txtproName.setError("Please Enter Description");


                }else if(txtQty.getText().toString().isEmpty()){
                    txtQty.setError("Please Enter Description");


                }else if(txtPrice.getText().toString().isEmpty()){
                    txtPrice.setError("Please Enter Description");


                }

                else{
                    /////////////////////////////////////
                   // CommonConstants.DELIVERY_ID++;
                    ID=ID++;
                    id = CommonConstants.DELIVERY_PREFIX + ID;

                    String ProID = txtProID.getText().toString();
                    String address = txtAddress.getText().toString();
                    String phone = txtPhone.getText().toString();
                    String cusName = txtcusName.getText().toString();
                    String ProName = txtproName.getText().toString();
                    String qty = txtQty.getText().toString();
                    String price = txtPrice.getText().toString();



                    del = new Delivery();

                    del.setProID(ProID);
                    del.setProName(ProName);
                    del.setCustomername(cusName);
                    del.setAddress(address);
                    del.setPhone(phone);
                    del.setQuantity(qty);
                    del.setPrice(price);
                    del.setId(id);
                    del.setUsername(username);

                     dbf = FirebaseDatabase.getInstance().getReference().child("Delivery");

                   dbf.child(del.getId()).setValue(del);
                    Toast.makeText(getApplicationContext(), "Data Inserted Successfully!, Created Delivery ID: " + del.getId(), Toast.LENGTH_SHORT).show();
                    clearControls();
                    if(del.getUsername().equalsIgnoreCase("0768551045")){
                        Intent intent = new Intent(insertdelivery.this,Delivery_Admin.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(insertdelivery.this,Delivery_Customer.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }

                }




            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearControls();
            }
        });


    }
    public void clearControls(){
        txtcusName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtProID.setText("");
        txtproName.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }


}
