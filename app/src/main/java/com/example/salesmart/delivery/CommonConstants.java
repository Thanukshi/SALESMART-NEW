package com.example.salesmart.delivery;
import com.example.salesmart.R;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

public class CommonConstants extends AppCompatActivity{


    public static final String DELIVERY_PREFIX = "DEL-0";
   // public static int DELIVERY_ID = 0;
    public static final String DEL_PREFIX = "DEL-0";
    DatabaseReference dbf;


}
