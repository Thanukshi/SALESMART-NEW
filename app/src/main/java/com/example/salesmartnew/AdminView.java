package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.salesmart.delivery.Delivery;
import com.example.salesmart.delivery.ListAll;
import com.example.salesmart.delivery.SelectDelivery;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminView extends AppCompatActivity {

    Button Add;
    String un;
    String pw;

    private DatabaseReference mDatabase;
    private ListView list_users;

    private ArrayList<String> deliveries = new ArrayList<>();
    private ArrayList<RegisterHelperClass> delList = new ArrayList<>();
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_admin_view);

                mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
                list_users = (ListView) findViewById(R.id.list_users);


                mDatabase.addValueEventListener(new ValueEventListener() {
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AdminView.this, android.R.layout.simple_list_item_1,deliveries);

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        deliveries.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            RegisterHelperClass delivery = ds.getValue(RegisterHelperClass.class);
                            String id = delivery.getFullName();
                            delList.add(delivery);
                            deliveries.add(id);

                        }
                        list_users.setAdapter(arrayAdapter);
/*
                        DeliveryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String id1 = deliveries.get(position);
                                Toast.makeText(ListAll.this,"ID: "+ id1+" Selected",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ListAll.this, SelectDelivery.class);
                                intent.putExtra("id", id1);
                                startActivity(intent);

                            }
                        });*/

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }


