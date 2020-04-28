package com.example.salesmartnew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

   CircleImageView profImage;
   EditText fullNameUp, contactUp, emailUp, passwordUp, confirmPassUp;
   Button updateProf, closeProf;

   private Uri imageUri;
   private String myUrl = "";
   private StorageReference storeProfImage;
   private String checker = "";
   private StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        storeProfImage = FirebaseStorage.getInstance().getReference().child("image");

        //value assignment
        profImage = (CircleImageView)findViewById(R.id.EPImage);
        fullNameUp = (EditText)findViewById(R.id.text1_EP);
        contactUp = (EditText)findViewById(R.id.text2_EP);
        emailUp =  (EditText)findViewById(R.id.text3_EP);
        passwordUp = (EditText)findViewById(R.id.text4_EP);
        confirmPassUp = (EditText)findViewById(R.id.text5_EP);
        updateProf = (Button)findViewById(R.id.buttonUp);
        closeProf = (Button)findViewById(R.id.buttonDel);
        
        userDetailsDisplay();

        closeProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker.equals("clicked")){
                    userDetailsDisplay();
                }else {
                    updateUserOnly();
                }

            }
        });

        profImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker = "clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(EditProfileActivity.this);

            }
        });

    }

    private void updateUserOnly() {

        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("users");

        HashMap<String, Object> userMapDetails = new HashMap<>();
        userMapDetails.put("")
    }

    private void userDetailsDisplay() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
