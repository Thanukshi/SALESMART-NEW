package com.example.salesmart.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmart3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListAllCart extends AppCompatActivity {
DatabaseReference mDatabase;
    private ListView MessageList;
    int shpID;

    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<String> shpidies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("shoppingcart");
        MessageList = (ListView) findViewById(R.id.ContactList);


        mDatabase.addValueEventListener(new ValueEventListener() {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (ListAllCart.this, android.R.layout.simple_list_item_1,messages);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ShoppingCart con = ds.getValue(ShoppingCart.class);
                    String message = con.getName();
                    String shp =  con.ID;
                    messages.add(message);
                    shpidies.add(shp);

                }
                MessageList.setAdapter(arrayAdapter);

                MessageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String shpid1 = shpidies.get(position);
                        Toast.makeText(ListAllCart.this,"ID: "+ shpid1+" Selected",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListAllCart.this,
                                updatedeletecart.class);
                        intent.putExtra("shpid", shpid1);
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
