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
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;

public class LoginActivity extends AppCompatActivity {

    AwesomeValidation awesomeValidation;

    TextView register, forgetPassword;
    EditText eUserName, ePassword;
    Button btnLogin;
    RelativeLayout relLay1, relLay2;

    //splash screen
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            relLay1.setVisibility( View.VISIBLE );
            relLay2.setVisibility( View.VISIBLE );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relLay1 = findViewById( R.id.rl1Login );
        relLay2 = findViewById(R.id.rl3Login);
        eUserName = findViewById(R.id.ET1_Login);
        ePassword =  findViewById(R.id.ET2_Login);
        forgetPassword = findViewById(R.id.text5_Login);
        register = findViewById( R.id.text6_Login );

        handler.postDelayed( runnable, 1000 );

        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent( LoginActivity.this,RegisterActivity.class );
                startActivity( sign );
           }
        } );


        register.setMovementMethod( LinkMovementMethod.getInstance() );

        btnLogin = findViewById( R.id.button1_Login );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String un = eUserName.getText().toString();
                String pw = ePassword.getText().toString();

                if(TextUtils.isEmpty(un)){
                    eUserName.setError("Enter User Name.");
                    return;
                }

                if(TextUtils.isEmpty(pw)){
                    ePassword.setError("Enter Password.");
                    return;
                }
                Intent iLogin = new Intent( LoginActivity.this, DashBoard.class );
                startActivity( iLogin );
            }
        } );
    }
}
