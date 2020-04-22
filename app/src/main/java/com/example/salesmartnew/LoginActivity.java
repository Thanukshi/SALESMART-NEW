package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView register;
    Button btnLogin;

    RelativeLayout relLay1, relLay2, relLay3;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            relLay1.setVisibility( View.VISIBLE );
            relLay2.setVisibility( View.VISIBLE );
            relLay3.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relLay1 = findViewById( R.id.rl1Login );
        relLay2 = findViewById( R.id.rl2Login );
        relLay3 = findViewById(R.id.rl3Login);

        handler.postDelayed( runnable, 1000 );

        register = findViewById( R.id.text6_Login );
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent( LoginActivity.this,DashBoard.class );
                startActivity( sign );
           }
        } );

       // String text = "Sign UP";

       // SpannableString sp = new SpannableString( text );
        //ClickableSpan clickableSpan1  = new ClickableSpan() {
         //   @Override
           // public void onClick(@NonNull View view) {

           // }

           // @Override
           // public void updateDrawState(@NonNull TextPaint ds) {
              // super.updateDrawState( ds );
               // ds.setColor( Color.YELLOW);
          // }
       // };
        //sp.setSpan( clickableSpan1, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        //textV5.setText( sp );
        register.setMovementMethod( LinkMovementMethod.getInstance() );

        btnLogin = findViewById( R.id.button1_Login );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogin = new Intent( LoginActivity.this, DashBoard.class );
                startActivity( iLogin );
            }
        } );
    }
}
