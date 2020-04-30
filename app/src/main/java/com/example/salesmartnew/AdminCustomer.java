package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesmart.delivery.ListAllCustomer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminCustomer extends AppCompatActivity {

    EditText  txtcusName, txtEmail,editTextSearch, txtPhone;
    TextView txtConfirm, txtPass;
    Button btnSearch,btnUpdate,btnDelete,btnView,btnClear;

    String cn;
    DatabaseReference dbf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        txtcusName = findViewById(R.id.txtCusName__Customer);
        txtEmail = findViewById(R.id.txtCusPgone__Customer);
        txtPhone = findViewById(R.id.txtEmail__Customer);
        txtPass = findViewById(R.id.txtPassword_Customer);
        txtConfirm = findViewById(R.id.txtConfirm_Customer);



        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdateCus);
        btnDelete = findViewById(R.id.btnDeleteCus);
        btnView = findViewById(R.id.btnViewCus);
        btnClear = findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtcusName.setText("");
                txtEmail.setText("");
                txtPhone.setText("");



            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCustomer.this, ListAllUserCustomer.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtcusName.getText().toString().isEmpty()) {
                    txtcusName.setError("Please Enter Name");


                } else if (txtPhone.getText().toString().isEmpty()) {
                    txtPhone.setError("Please Enter Phone Number");


                } else if (txtEmail.getText().toString().isEmpty()) {
                    txtEmail.setError("Please Enter Email");


                } else {
                    /////////////////////////////////////

                    String cusPhone = txtPhone.getText().toString();
                    String cusName = txtcusName.getText().toString();
                    String cusEmail = txtEmail.getText().toString();
                    clearControls();

                    RegisterHelperClass reg = new RegisterHelperClass();
                    reg.setContactNo(cusPhone);
                    reg.setFullName(cusName);
                    reg.setEmailCustomer(cusEmail);

                    dbf = FirebaseDatabase.getInstance().getReference().child("users");
                    dbf.child(cn).setValue(reg);

                    Toast.makeText(getApplicationContext(), reg.getContactNo()+" Data Updated Successfully!", Toast.LENGTH_SHORT).show();

                }clearControls();
            }

        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cn.charAt(0) != 'D'){
                    Intent intent = new Intent(AdminCustomer.this, AdminView.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Please Search for theCustomer By a Valid Phone Number!", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbf.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dbf = FirebaseDatabase.getInstance().getReference().child("Delivery").child(cn);
                            dbf.removeValue();
                            clearControls();
                            cn ="";
                            Toast.makeText(getApplicationContext(), "Successfully Deleted Customer of Phone Number "+cn, Toast.LENGTH_LONG).show();
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
                editTextSearch = findViewById(R.id.editTextSearch_Customer);
                String searchvalue = editTextSearch.getText().toString();

                if (searchvalue.isEmpty())
                    editTextSearch.setHint("Please Enter ID to Search ");

                else {

                    dbf = FirebaseDatabase.getInstance().getReference().child("Delivery").child(searchvalue);
                    dbf.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getChildrenCount();
                            RegisterHelperClass reg = dataSnapshot.getValue(RegisterHelperClass.class);
                            if (reg != null) {
                                txtcusName.setText(reg.getFullName());
                                txtPhone.setText(reg.getContactNo());
                                txtEmail.setText(reg.getEmailCustomer());
                                txtPass.setText(reg.getPasswordCustomer());
                                txtConfirm.setText(reg.getConfirmPasswordCustomer());
                                cn = reg.getContactNo();

                                if (!reg.getContactNo().isEmpty()) {
                                    editTextSearch.setText("");
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Please Enter Valid Contact Number!", Toast.LENGTH_LONG).show();
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





    private void clearControls() {
        txtcusName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");

    }
}

