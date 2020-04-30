package com.example.salesmart.product.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmart.product.Interface.ItemClickListner;
import com.example.salesmartnew.R;

public class Product_View_Holder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtpname,txtpdescrip,txtpstatus,txtpprice;
    public ImageView imgview;
    public ItemClickListner listner;

    public Product_View_Holder(@NonNull View itemView) {
        super(itemView);


        imgview= (ImageView) itemView.findViewById(R.id.productimage);
        txtpname = (TextView) itemView.findViewById(R.id.productname);
        txtpdescrip = (TextView) itemView.findViewById(R.id.productdescription);
        txtpstatus = (TextView) itemView.findViewById(R.id.productstatus);
        txtpprice = (TextView) itemView.findViewById(R.id.productprice);
    }

    public  void setItenClickListner(ItemClickListner listner){

        this.listner= listner;
    }

    @Override
    public void onClick(View v) {

        listner.onClick(v, getAdapterPosition() , false);
    }
}
