package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {
    TextView textViewDelete;
    DatabaseReference dbf;
    String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textViewDelete = findViewById(R.id.textViewDelete);
        Intent intent = getIntent();
        contact= intent.getStringExtra("contactNo");

        textViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbf = FirebaseDatabase.getInstance().getReference().child("users").child(contact);
                dbf.removeValue();
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            }
        });

    }
}
