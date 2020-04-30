package com.example.salesmart.purchase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.salesmartnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListAllContact extends AppCompatActivity {
DatabaseReference mDatabase;
    private ListView MessageList;

    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<ContactUs> ConList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_contact);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("contactus");
        MessageList = (ListView) findViewById(R.id.ContactList);


        mDatabase.addValueEventListener(new ValueEventListener() {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListAllContact.this, android.R.layout.simple_list_item_1,messages);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ContactUs con = ds.getValue(ContactUs.class);
                    String message = con.getMessage();
                    ConList.add(con);
                    messages.add(message);

                }
                MessageList.setAdapter(arrayAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
