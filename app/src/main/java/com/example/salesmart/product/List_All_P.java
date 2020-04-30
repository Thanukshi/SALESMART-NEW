package com.example.salesmart.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.salesmart.product.Model.Product;
import com.example.salesmart.purchase.Order;
import com.example.salesmartnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List_All_P extends AppCompatActivity {
    private DatabaseReference ProductRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;
    private ListView ProList;
    String ID;
    SearchView searchView;

    private ArrayList<String> pro = new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();
    private ArrayList<Product> delList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__all__p);


            mDatabase = FirebaseDatabase.getInstance().getReference().child("products");
            ProList = (ListView) findViewById(R.id.listproduct);
            searchView = (SearchView) findViewById(R.id.search) ;



            mDatabase.addValueEventListener(new ValueEventListener() {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(List_All_P.this, android.R.layout.simple_list_item_1,pro);

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    pro.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Product pro1 = ds.getValue(Product.class);

                        String name = pro1.getPname();
                        //String name = pro1.getDate();

                        ID = pro1.getDate();


                        delList.add(pro1);
                        pro.add(name);
                        ids.add(ID);


                    }
                    ProList.setAdapter(arrayAdapter);

                    ProList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String id1 = pro.get(position);
                            String nextID = ids.get(position);

                            Intent intent = new Intent(List_All_P.this, Order.class);
                            intent.putExtra("pid", nextID);
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

