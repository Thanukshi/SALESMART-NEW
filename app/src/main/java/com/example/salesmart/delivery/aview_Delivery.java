package com.example.salesmart.delivery;
import com.example.salesmartnew.R;
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


public class aview_Delivery extends AppCompatActivity {
    EditText txtProID, txtproName, txtcusName, txtAddress,txtPhone, txtQty, txtPrice,editTextSearch;
    Button btnSearch,btnUpdateDelivery,btnDelete,btnViewDelivery,btnClear;

    String id;
    DatabaseReference dbf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aview_delivery);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");
        txtcusName = findViewById(R.id.txtCusName);
        txtAddress = findViewById(R.id.txtCusAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtProID = findViewById(R.id.txtProID);
        txtproName = findViewById(R.id.txtProName);
        txtQty = findViewById(R.id.txtQty);
        txtPrice = findViewById(R.id.txtPrice);
        editTextSearch = findViewById(R.id.editTextSearch);

        editTextSearch.setText("DEL-");

        btnSearch = findViewById(R.id.btnSearch);
  //      btnUpdateDelivery = findViewById(R.id.btnUpdateDelivery);
        btnDelete = findViewById(R.id.btnDeleteDelivery);
        btnViewDelivery = findViewById(R.id.btnViewDelivery);
        btnClear = findViewById(R.id.btnClear);

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
                Intent intent = new Intent(aview_Delivery.this, ListAll.class);
                startActivity(intent);
            }
        });

 /*       btnUpdateDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(txtcusName.getText().toString().isEmpty()){
                    txtcusName.setError("Please Enter Name");


                }
                else if(txtAddress.getText().toString().isEmpty()){
                    txtAddress.setError("Please Enter Price");


                }else if(txtPhone.getText().toString().isEmpty()){
                    txtPhone.setError("Please Enter Phone Number");


                }else if(txtProID.getText().toString().isEmpty()){
                    txtProID.setError("Please Enter Product ID");


                }else if(txtproName.getText().toString().isEmpty()){
                    txtproName.setError("Please Enter Product Name");


                }else if(txtQty.getText().toString().isEmpty()){
                    txtQty.setError("Please Enter Quantity");


                }else if(txtPrice.getText().toString().isEmpty()){
                    txtPrice.setError("Please Enter Price");


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
                    del1.setUsername(username);



                    dbf = FirebaseDatabase.getInstance().getReference().child("Delivery");

                    dbf.child(id).setValue(del1);

                    Toast.makeText(getApplicationContext(), del1.getId()+" Data Updated Successfully!", Toast.LENGTH_SHORT).show();

                 Intent intent1 = new Intent(aview_Delivery.this,Delivery_Admin.class);
                 intent1.putExtra("username",username);
                 startActivity(intent1);

                }clearControls();
            }
        });*/

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.charAt(0) != 'D'){
                    Intent intent = new Intent(aview_Delivery.this, insertdelivery.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Please Search for the Delivery By a Valid Id!", Toast.LENGTH_SHORT).show();
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
                    clearControls();
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editTextSearch = findViewById(R.id.editTextSearch);
                String searchvalue = editTextSearch.getText().toString();

                if(searchvalue.isEmpty())
                    editTextSearch.setHint("Please Enter ID to Search ");

                else{

                    dbf = FirebaseDatabase.getInstance().getReference().child("Delivery").child(searchvalue);
                    dbf.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getChildrenCount();
                            Delivery del = dataSnapshot.getValue(Delivery.class);
                            if(del != null){
                                txtcusName.setText(del.getCustomername());
                                txtAddress.setText(del.getAddress());
                                txtPhone.setText(del.getPhone());
                                txtProID.setText(del.getProID());
                                txtproName.setText(del.getProName());
                                txtQty.setText(del.getQuantity());
                                txtPrice.setText(del.getPrice());
                                id = del.getId();

                                if(!del.getCustomername().isEmpty()){
                                    editTextSearch.setText("DEL-");
                                }

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Please Enter Valid Id!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


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
}
