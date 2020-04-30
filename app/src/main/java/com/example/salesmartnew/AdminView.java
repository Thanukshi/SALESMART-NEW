package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminView extends AppCompatActivity {

    Button Add;
    String un;
    String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        Add = findViewById(R.id.buttonAd);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");


                Query checkUser = reference.orderByChild("contactNo").equalTo(un);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            String pwInDB = dataSnapshot.child(un).child("passwordCustomer").getValue(String.class);
                            //String unIDb = dataSnapshot.child(un).child("userNameCustomer").getValue(String.class);

                            if (pwInDB.equals(pw)) {

                                String userNameDB = dataSnapshot.child(un).child("contactNo").getValue(String.class);
                                Intent logIntent = new Intent(getApplicationContext(), AdminCustomer.class);
                                logIntent.putExtra("contactNo", userNameDB);
                                startActivity(logIntent);
                            }
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
