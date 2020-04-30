package com.example.salesmart.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmartnew.R;
import com.example.salesmart.product.Model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.Product_View_Holder> {

    Context context;
    ArrayList<Product> products;


    public Product_Adapter(Context c, ArrayList<Product> pro){

        context = c;
        products = pro;


    }

    @NonNull
    @Override
    public Product_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Product_View_Holder(LayoutInflater.from(context).inflate(R.layout.card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Product_View_Holder holder, int position) {

        holder.proname.setText(products.get(position).getPname());
        holder.prodescrip.setText(products.get(position).getDescription());
        holder.prosts.setText(products.get(position).getStatus());
        holder.proprice.setText(products.get(position).getPrice());
        Picasso.get().load(products.get(position).getImage()).into(holder.proimg);



    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    class Product_View_Holder extends RecyclerView.ViewHolder{

        TextView proname,prodescrip,prosts,proprice;
        ImageView  proimg;



        public Product_View_Holder(@NonNull View itemView) {
            super(itemView);

            proname = (TextView)itemView.findViewById(R.id.productnameCV);
            prodescrip = (TextView)itemView.findViewById(R.id.productdescripCV);
            prosts = (TextView)itemView.findViewById(R.id.productstsCV);
            proprice = (TextView)itemView.findViewById(R.id.productpriceCV);
            proimg = (ImageView) itemView.findViewById(R.id.productimg);


        }

        public  void  onClick(final int position){

                proimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, position+"Add to Cart.........", Toast.LENGTH_SHORT).show();

                       }
                });
        }
    }
}
