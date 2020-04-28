package com.example.salesmartnew;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

   private CircleImageView profImage;
   private EditText fullNameUp, contactUp, emailUp, passwordUp, confirmPassUp;
   Button updateProf, closeProf;

   private Uri imageUri;
   private String myUrl = "";
   private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);





    }

}
