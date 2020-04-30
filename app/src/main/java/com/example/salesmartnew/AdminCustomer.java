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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminCustomer extends AppCompatActivity {

    TextView   editTextSearch;
    TextView txtcusName, txtEmail,txtConfirm, txtPass,txtPhone;
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
        editTextSearch = findViewById(R.id.editTextSearch_Customer);


        btnSearch = findViewById(R.id.btnSearch_Customer);
        btnView = findViewById(R.id.btnViewCus);




        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCustomer.this, AdminView.class);
                startActivity(intent);
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  search = editTextSearch.getText().toString();

                dbf = FirebaseDatabase.getInstance().getReference().child("users").child(search);

                dbf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RegisterHelperClass reh = (RegisterHelperClass) dataSnapshot.getValue(RegisterHelperClass.class);
                        if(reh != null){

                        txtcusName.setText(reh.getFullName());
                        txtEmail.setText(reh.getEmailCustomer());
                        txtPhone.setText(reh.getContactNo());
                        txtPass.setText(reh.getPasswordCustomer());
                        txtConfirm.setText(reh.getConfirmPasswordCustomer());
                        editTextSearch.setText("");
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Please Enter Valid Phone Number!", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}

