package com.example.salesmartnew;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

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
        








    }

}
