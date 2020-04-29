package com.example.salesmart.delivery;
import com.example.salesmart.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.salesmart.salesmart.R;

import java.util.ArrayList;


public class ListAll extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView DeliveryList;

    private ArrayList<String> deliveries = new ArrayList<>();
    private ArrayList<Delivery> delList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Delivery");
        DeliveryList = (ListView) findViewById(R.id.del_list);


        mDatabase.addValueEventListener(new ValueEventListener() {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListAll.this, android.R.layout.simple_list_item_1,deliveries);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                deliveries.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Delivery delivery = ds.getValue(Delivery.class);
                    String id = delivery.getId();
                    delList.add(delivery);
                    deliveries.add(id);

                }
                DeliveryList.setAdapter(arrayAdapter);

                DeliveryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String id1 = deliveries.get(position);
                        Toast.makeText(ListAll.this,"ID: "+ id1+" Selected",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListAll.this, SelectDelivery.class);
                        intent.putExtra("id", id1);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
