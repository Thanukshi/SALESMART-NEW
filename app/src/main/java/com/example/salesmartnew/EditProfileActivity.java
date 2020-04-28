package com.example.salesmartnew;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

   private CircleImageView profImage;
   private EditText fullNameUp, contactUp, emailUp, passwordUp, confirmPassUp;
   Button updateProf, closeProf;

   private Uri imageUri;
   private String myUrl = "";
   private DatabaseReference storeProfImage;
   private String checker = "";
   private StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        storeProfImage = FirebaseDatabase.getInstance().getReference().child("image");







    }

}
