package com.example.salesmartnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    CircleImageView profImage;
    EditText fullNameUp, contactUp, emailUp, passwordUp, confirmPassUp;
    Button updateProf, closeProf;
    DatabaseReference dbref;
    private Uri imageUri;
    private String myUrl = "";
    private StorageReference storeProfImage;
    private String checker = "";
    private StorageTask uploadTask;
    RegisterHelperClass rh, good;
    String contact;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        storeProfImage = FirebaseStorage.getInstance().getReference().child("image");

        //value assignment
        profImage = (CircleImageView) findViewById(R.id.EPImage);
        fullNameUp = (EditText) findViewById(R.id.text1_EP);
        contactUp = (EditText) findViewById(R.id.text2_EP);
        emailUp = (EditText) findViewById(R.id.text3_EP);
        passwordUp = (EditText) findViewById(R.id.text4_EP);
        confirmPassUp = (EditText) findViewById(R.id.text5_EP);
        updateProf = (Button) findViewById(R.id.buttonUp);
        closeProf = (Button) findViewById(R.id.buttonDel);


        Intent intent = getIntent();
        contact = intent.getStringExtra("contactNo");
        dbref = FirebaseDatabase.getInstance().getReference().child("users").child(contact);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rh = (RegisterHelperClass) dataSnapshot.getValue(RegisterHelperClass.class);

                fullNameUp.setText(rh.getFullName());
                contactUp.setText(rh.contactNo);
                emailUp.setText(rh.getEmailCustomer());
                passwordUp.setText(rh.passwordCustomer);
                confirmPassUp.setText(rh.confirmPasswordCustomer);
                Picasso.get().load(rh.getImage()).into(profImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref = FirebaseDatabase.getInstance().getReference().child("users");
                //Validation();
                good = new RegisterHelperClass();

                good.setImage(rh.image);
                good.setContactNo(contactUp.getText().toString());
                good.setFullName(fullNameUp.getText().toString());
                good.setEmailCustomer(emailUp.getText().toString());
                good.setPasswordCustomer(passwordUp.getText().toString());
                good.setConfirmPasswordCustomer(confirmPassUp.getText().toString());

                dbref.child(good.getContactNo()).setValue(good);

            }
        });


    }
}


