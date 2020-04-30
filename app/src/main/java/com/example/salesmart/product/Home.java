package com.example.salesmart.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.salesmartnew.R;
import com.example.salesmart.product.Model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
//import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

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

  //  private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_all);



        mDatabase = FirebaseDatabase.getInstance().getReference().child("products");
        ProList = (ListView) findViewById(R.id.listproduct);
       searchView = (SearchView) findViewById(R.id.search) ;



        mDatabase.addValueEventListener(new ValueEventListener() {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_list_item_1,pro);

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

                       Intent intent = new Intent(Home.this, Edit_Product.class);
                       intent.putExtra("id", nextID);
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
/*
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(ProductRef,Product.class).build();

        FirebaseRecyclerAdapter <Product, Product_View_Holder> adapter=
                new FirebaseRecyclerAdapter<Product, Product_View_Holder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Product_View_Holder  holder, int i, @NonNull Product model) {

                        holder.txtpname.setText(model.getPname());
                        holder.txtpdescrip.setText(model.getDescription());
                        holder.txtpstatus.setText(model.getStatus());
                        holder.txtpprice.setText("Rs "+model.getPrice() );
                        Picasso.get().load(model.getImage()).into(holder.imgview);

                    }

                    @NonNull
                    @Override
                    public Product_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false);
                        Product_View_Holder holder = new Product_View_Holder(view);
                        return holder;



                    }
                };

            recyclerView.setAdapter((adapter));
            adapter.startListening();

    }




}*/
