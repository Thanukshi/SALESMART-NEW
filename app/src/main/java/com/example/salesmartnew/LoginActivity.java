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
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

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


        //add validation for userName
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.ET1_Login, RegexTemplate.NOT_EMPTY,R.string.invalid_username);

        String errorPassword = "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}";
        //add validation for password
        awesomeValidation.addValidation(this,R.id.ET2_Login,errorPassword,R.string.invalid_password);

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

                //check the validation
                if(awesomeValidation.validate()){
                    //validate success
                    //Toast.makeText(getApplicationContext(),"Details is correct...",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(),"Enter valid user name and password..",Toast.LENGTH_SHORT).show();

                }

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
