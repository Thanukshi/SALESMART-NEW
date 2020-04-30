package com.example.salesmart.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.salesmartnew.R;

import java.lang.annotation.Annotation;

public class Welcom_Adim extends AppCompatActivity {

    private ImageView imge;
    private static int time=5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom__adim);
        imge = (ImageView) findViewById(R.id.welcomelogo);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcom_Adim.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },time);


      //  Annotation annotation = (Annotation) AnimationUtils.loadAnimation(Welcom_Adim.this,R.anim.welcomeanimation);
      //       imge.startAnimation((Animation) annotation);




    }
}
