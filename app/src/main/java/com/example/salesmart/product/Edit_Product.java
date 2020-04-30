package com.example.salesmart.product;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmartnew.R;
import com.example.salesmart.product.Model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Edit_Product extends AppCompatActivity {
    EditText editTextNameEP, editTextDescripEP, editTextStsEP, editTextPriceEP;
    Button Update, Delete,Canccel;
    DatabaseReference ProductRef;
    private ImageView proimg;
    Product pr;
    String pid, saveCurrentDAte,saveCurrentTime,productRandomKey,downloadImgURL;
    private static final int GalleryPic =1;
    private Uri imguri;
    private StorageReference proRefImg;
    private DatabaseReference productRef;
    private ProgressDialog loadBar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit__product);

        editTextNameEP = findViewById(R.id.editTextNameEP);
        editTextDescripEP = findViewById(R.id.editTextDescripEP);
        editTextStsEP = findViewById(R.id.editTextStsEP);
        editTextPriceEP = findViewById(R.id.editTextPriceEP);
        proimg = (ImageView) findViewById(R.id.imageViewEP);
        Update = (Button) findViewById(R.id.btnUPEP);
        Delete = (Button) findViewById(R.id.btnDeleteEP);
        Canccel = (Button) findViewById(R.id.btnCnclEP) ;
        loadBar = new  ProgressDialog(this);

        OnStart();
        proimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product pr2 = new Product();

                pr2.setPrice(editTextPriceEP.getText().toString());
                pr2.setPname(editTextNameEP.getText().toString());
                pr2.setStatus(editTextStsEP.getText().toString());
                pr2.setDescription(editTextDescripEP.getText().toString());
                pr2.setDate(pr.getDate());
                pr2.setTime(pr.getTime());
                pr2.setPid(pr.getPid());
                pr2.setCategory(pr.getCategory());

                ProductRef = FirebaseDatabase.getInstance().getReference().child("products");
                ProductRef.child(pr.getDate()).setValue(pr2);
                Intent intent =new Intent(Edit_Product.this, Home.class);
                startActivity(intent);
                loadBar.setTitle("Updating  New Product ");
                loadBar.setMessage("Dear Admin,Please wait, while we  are Updating the new product");
                loadBar.setCanceledOnTouchOutside(false);
                loadBar.show();

                Toast.makeText(Edit_Product.this, "Updated Successfully...", Toast.LENGTH_SHORT).show();

            }
        });

            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductRef = FirebaseDatabase.getInstance().getReference().child("products").child(pr.getDate());
                    ProductRef.removeValue();
                    Intent intent =new Intent(Edit_Product.this, Home.class);
                    startActivity(intent);

                    loadBar.setTitle(" Delete Product ");
                    loadBar.setMessage("Dear Admin,Please wait, while we  are Deleting  the  product");
                    loadBar.setCanceledOnTouchOutside(false);
                    loadBar.show();

                    Toast.makeText(Edit_Product.this, "Deleted Successfully...", Toast.LENGTH_SHORT).show();
                    //ProductRef.child(pr.getDate()).setValue(pr2);
                }
            });

    }

    private void OpenGallery() {
        Intent galaeryintent = new Intent();
        galaeryintent.setAction(Intent.ACTION_GET_CONTENT);
        galaeryintent.setType("image/*");
        startActivityForResult(galaeryintent,GalleryPic);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPic && resultCode==RESULT_OK && data!=null );
        imguri = data.getData();
        proimg.setImageURI(imguri);


    }




    public void OnStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();

        pid = extras.getString("id");
        proimg = (ImageView) findViewById(R.id.imageViewEP);
        System.out.println();

        ProductRef = FirebaseDatabase.getInstance().getReference().child("products").child(pid);
        ProductRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pr = dataSnapshot.getValue(Product.class);

                editTextNameEP.setText(pr.getPname());
                editTextDescripEP.setText(pr.getDescription());
                editTextStsEP.setText(pr.getStatus());
                editTextPriceEP.setText(pr.getPrice());
                Picasso.get().load(pr.getImage()).into(proimg);

            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Canccel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

    }
/*

    private void SaveProductInfoToDatabase() {

        Product pr = new Product();

        pr.setCategory(pr.getCategory());
        pr.setDate(pid);
        pr.setDescription(editTextDescripEP.getText().toString());
        pr.setPid(pid);
        pr.setPname(editTextNameEP.getText().toString());
        pr.setStatus(editTextStsEP.getText().toString());
        pr.setImage(pr.getImage());
        pr.setTime(saveCurrentTime);
        pr.setPrice(editTextPriceEP.getText().toString());

//poddk hitpn ah elaaa // aiye data tika pass karana eka id eken nathuw okkoma set get dala gaththoth bari weida
        //puluwan
        productRef.child(pid).setValue(pr)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Intent  intent = new Intent(Edit_Product.this,Admin.class);

                            startActivity(intent);

                            //loadBar.dismiss();
                            Toast.makeText(Edit_Product.this, " Product is added successfully.........", Toast.LENGTH_SHORT).show();
                        }
                        else {
                           /// loadBar.dismiss();
                            String msg = task.getException().toString();
                            Toast.makeText(Edit_Product.this, "Error:"+msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
*/
}

