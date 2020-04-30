package com.example.salesmart.product;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmartnew.R;
import com.example.salesmart.product.Model.Product;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Add_Product extends AppCompatActivity {

    private  String categoryName,pdescrip,pname,pprice,pstatus,saveCurrentDAte,saveCurrentTime,productRandomKey,downloadImgURL;
    private  Button Add,Camcel;
    private  EditText name,price,descrip,status;
    private ImageView img;
    private static final int GalleryPic =1;
    private Uri imguri;
    private StorageReference proRefImg;
    private DatabaseReference productRef;
    private ProgressDialog loadBar;
    String ID1;
//purchase


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);

        categoryName = getIntent().getExtras().get("category").toString();
        proRefImg = FirebaseStorage.getInstance().getReference().child("ProductImages");
        productRef = FirebaseDatabase.getInstance().getReference().child("products");


        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               int it = (int) dataSnapshot.getChildrenCount();
               it++;
               ID1 = Integer.toString(it);
                System.out.println(ID1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Add = (Button) findViewById(R.id.btnAddAP);
        Camcel = (Button) findViewById(R.id.btnCncleAP);
        name = (EditText) findViewById(R.id.editTextNameAP);
        descrip = (EditText) findViewById(R.id.editTextDescripAP);
        status = (EditText) findViewById(R.id.editTextStsAP);
        price = (EditText) findViewById(R.id.editTextPriceAP);
        img = (ImageView)findViewById(R.id.imageViewAP);
        loadBar = new  ProgressDialog(this);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
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
        img.setImageURI(imguri);


    }

    private void ValidateProductData()
    {
        pname = name.getText().toString();
        pdescrip = descrip.getText().toString();
        pstatus = status.getText().toString();
        pprice = price.getText().toString();

        if(imguri == null){
            Toast.makeText(this, "Product image  is mandetory.......", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pname))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pdescrip))
        {
            Toast.makeText(this, "Please write product description....", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pstatus))
        {
            Toast.makeText(this, "Please write product Status....", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pprice))
        {
            Toast.makeText(this, "Please write product price....", Toast.LENGTH_SHORT).show();
        }

        else {
            StroeProductInfo();

        }
    }

    private void StroeProductInfo(){

        loadBar.setTitle("Adding New Product ");
        loadBar.setMessage("Dear Admin,Please wait, while we  are adding the new product");
        loadBar.setCanceledOnTouchOutside(false);
        loadBar.show();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MM D, YYYY");
        saveCurrentDAte =currentdate.format(calendar.getTime());


        SimpleDateFormat currenttime = new SimpleDateFormat("HH:MM:SS a");
        saveCurrentTime = currenttime.format(calendar.getTime());

        productRandomKey = saveCurrentDAte + saveCurrentTime;

        final StorageReference filepath = proRefImg.child(imguri.getLastPathSegment() + productRandomKey+ ".jpg");
        final UploadTask uploadTask= filepath.putFile(imguri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String massege  = e.toString();
                Toast.makeText(Add_Product.this, "Error" + massege , Toast.LENGTH_SHORT).show();
                loadBar.dismiss();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(Add_Product.this, "Image upload successfully....", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask= uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()) {
                            throw task.getException();

                        }
                            downloadImgURL = filepath.getDownloadUrl().toString();
                            return filepath.getDownloadUrl();

                        }


                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful()){

                            downloadImgURL = task.getResult().toString();


                            Toast.makeText(Add_Product.this, " got Product Image URL  Successfully", Toast.LENGTH_SHORT).show();
                            SaveProductInfoToDatabase();
                            
                        }
                    }
                });

            }
        });

    }

    private void SaveProductInfoToDatabase() {


        Product pr = new Product();

        pr.setCategory(categoryName);
        pr.setDate(ID1);
        pr.setDescription(pdescrip);
        pr.setPid(ID1);
        pr.setPname(pname);
        pr.setStatus(pstatus);
        pr.setImage(downloadImgURL);
        pr.setTime(saveCurrentTime);
        pr.setPrice(pprice);


        productRef.child(String.valueOf(ID1)).setValue(pr)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Intent  intent = new Intent(Add_Product.this,Admin.class);

                            startActivity(intent);

                            loadBar.dismiss();
                            Toast.makeText(Add_Product.this, " Product is added successfully.........", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            loadBar.dismiss();
                             String msg = task.getException().toString();
                            Toast.makeText(Add_Product.this, "Error:"+msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            Camcel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i  = new Intent(getApplicationContext(),Admin.class);
                    startActivity(i);
                }
            });

    }
}



















        /*EditText name,descrip,status,price;
        Button add,cancel;
        FirebaseDatabase database;
        DatabaseReference reference;
        ProductHelperClass ProductHelperClass;
        ImageButton imgbtn;
        int Gallary_intent = 1;
        DatabaseReference imagepath;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);

        name = (EditText) findViewById(R.id.editTextNameAP);
        descrip = (EditText) findViewById(R.id.editTextDescripAP);
        status = (EditText) findViewById(R.id.editTextStsAP);
        price = (EditText) findViewById(R.id.editTextPriceAP);
        add = (Button) findViewById(R.id.btnAddAP);
        cancel = (Button) findViewById(R.id.btnCncleAP);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("product");
        ProductHelperClass = new ProductHelperClass();
        imgbtn = (ImageButton)findViewById(R.id.imageButtonAP);


    }
    private void getValues() {

        ProductHelperClass.setpName(name.getText().toString());
        ProductHelperClass.setpDescription(name.getText().toString());
        ProductHelperClass.setpStatus(name.getText().toString());
        ProductHelperClass.setpPrice(name.getText().toString());



    }

    public void btnAddAP (View view) {

    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            getValues();
            reference.child("product01").setValue(ProductHelperClass);
            Toast.makeText(Add_Product.this, "Added SuccessFully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),List_view.class);
            startActivity(i);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }


    public void imgbtn(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");


        startActivityForResult(intent,Gallary_intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallary_intent && requestCode == RESULT_OK) {

            Uri uri = data.getData();
            imgbtn.setImageURI(uri);
            imagepath = FirebaseDatabase.getInstance().getReference().child("product").child(uri.getLastPathSegment());



        }
    }
}






    /*FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    EditText name,descrip,status,price;
    Button add,cancel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);

        name = findViewById(R.id.editTextNameAP);
        descrip = findViewById(R.id.editTextDescripAP);
        status = findViewById(R.id.editTextStsAP);
        price = findViewById(R.id.editTextPriceAP);
        add = findViewById(R.id.btnAddAP);
        cancel = findViewById(R.id.btnCncleAP);

       add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //DBHandler dbHandler = new DBHandler(getApplicationContext());
                //long newID=     dbHandler.addInfo(name.getText().toString(),descrip.getText().toString(),status.getText().toString(),price.getText().toString());
                //Toast.makeText(Add_Product.this,"Product Added. Product ID"+newID,Toast.LENGTH_SHORT).show();



                //name.setText(null);
                //descrip.setText(null);
                //status.setText(null);
                //price.setText(null);
                String prName = name.getText().toString().trim();
                String prDescription = descrip.getText().toString().trim();
                String prStatus = status.getText().toString().trim();
                String prPrice = price.getText().toString().trim();

                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("product");
                ProductHelperClass productHelperClass = new ProductHelperClass(prName, prDescription, prStatus, prPrice);
                reference.child(prName).setValue(productHelperClass);



                Intent i = new Intent(getApplicationContext(),List_view.class);
                startActivity(i);





            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Admin.class);
                startActivity(i);


            }
        });

    }
}*/
