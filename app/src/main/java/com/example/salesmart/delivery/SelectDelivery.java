package com.example.salesmart.delivery;
import com.example.salesmartn.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.salesmart.salesmart.R;

public class SelectDelivery extends AppCompatActivity {

    EditText txtProID, txtproName, txtcusName, txtAddress,txtPhone, txtQty, txtPrice,editTextSearch;
    Button btnSearch,btnUpdateDelivery,btnDelete,btnViewDelivery,btnClear;

    String id;
    DatabaseReference dbf;
    // Delivery del;
    ProgressBar proSerch;
    private Dialog deleteAllDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_delivery);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        txtcusName = findViewById(R.id.txtCusName);
        txtAddress = findViewById(R.id.txtCusAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtProID = findViewById(R.id.txtProID);
        txtproName = findViewById(R.id.txtProName);
        txtQty = findViewById(R.id.txtQty);
        txtPrice = findViewById(R.id.txtPrice);



        btnUpdateDelivery = findViewById(R.id.btnUpdateDelivery);
        btnDelete = findViewById(R.id.btnDeleteDelivery);
        btnViewDelivery = findViewById(R.id.btnViewDelivery);
        btnClear = findViewById(R.id.btnClear);
        OnStart();
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtcusName.setText("");
                txtAddress.setText("");
                txtPhone.setText("");
                txtProID.setText("");
                txtproName.setText("");
                txtQty.setText("");
                txtPrice.setText("");
            }
        });

        btnViewDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectDelivery.this, ListAll.class);
                startActivity(intent);
            }
        });

        btnUpdateDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtcusName.getText().toString().isEmpty()){
                    txtcusName.setError("Please Enter Name");


                }
                else if(txtAddress.getText().toString().isEmpty()){
                    txtAddress.setError("Please Enter Price");


                }else if(txtPhone.getText().toString().isEmpty()){
                    txtPhone.setError("Please Enter Description");


                }else if(txtProID.getText().toString().isEmpty()){
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

                    String ProID = txtProID.getText().toString();
                    String address = txtAddress.getText().toString();
                    String phone = txtPhone.getText().toString();
                    String cusName = txtcusName.getText().toString();
                    String ProName = txtproName.getText().toString();
                    String qty = txtQty.getText().toString();
                    String price = txtPrice.getText().toString();
                    clearControls();





                    Delivery del1 = new Delivery();

                    del1.setProID(ProID);
                    del1.setProName(ProName);
                    del1.setCustomername(cusName);
                    del1.setAddress(address);
                    del1.setPhone(phone);
                    del1.setQuantity(qty);
                    del1.setPrice(price);
                    del1.setId(id);

                    dbf = FirebaseDatabase.getInstance().getReference().child("Delivery");

                    dbf.child(id).setValue(del1);

                    Toast.makeText(getApplicationContext(), del1.getId()+" Data Updated Successfully!", Toast.LENGTH_SHORT).show();



                }
                Intent intent = new Intent(SelectDelivery.this, ListAll.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.charAt(0) != 'D'){

                }
                else{
                    dbf.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dbf = FirebaseDatabase.getInstance().getReference().child("Delivery").child(id);
                            dbf.removeValue();
                            clearControls();
                            id ="";
                            Toast.makeText(getApplicationContext(), "Successfully Deleted Delivery of Id "+id, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                Intent intent = new Intent(SelectDelivery.this, ListAll.class);
                startActivity(intent);
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
        // editTextSearch.setText("");
    }

    public void OnStart(){
        super.onStart();
        Bundle extras = getIntent().getExtras();

           String newString= extras.getString("id");

        dbf = FirebaseDatabase.getInstance().getReference().child("Delivery").child(newString);
        dbf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Delivery del = dataSnapshot.getValue(Delivery.class);

                    txtcusName.setText(del.getCustomername());
                    txtAddress.setText(del.getAddress());
                    txtPhone.setText(del.getPhone());
                    txtProID.setText(del.getProID());
                    txtproName.setText(del.getProName());
                    txtQty.setText(del.getQuantity());
                    txtPrice.setText(del.getPrice());
                    id = del.getId();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
