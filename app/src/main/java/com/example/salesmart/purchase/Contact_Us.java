package com.example.salesmart.purchase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.salesmartnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Contact_Us extends AppCompatActivity {
    EditText editTextMessage,editTextPhone,editTextEmail,editTextName;
    Button buttonContact, buttonClear;
    DatabaseReference dbref;
    ContactUs cs;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonContact = findViewById(R.id.buttonContact);

        dbref = FirebaseDatabase.getInstance().getReference().child("contactus");
        ID= 0;
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ID = (int) dataSnapshot.getChildrenCount();
                System.out.println(ID);
                ID ++;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cs = new ContactUs();

                cs.setName(editTextName.getText().toString());
                cs.setEmail(editTextEmail.getText().toString());
                cs.setPhone(editTextPhone.getText().toString());
                cs.setMessage(editTextMessage.getText().toString());


                dbref.child(String.valueOf(ID)).setValue(cs);
                startActivity(new Intent(Contact_Us.this,ListAllContact.class) );
            }
        });


    }
}
