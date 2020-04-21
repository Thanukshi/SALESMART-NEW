package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView textV5;
    Button btnLogin;

    RelativeLayout relLay1, relLay2, relLay3;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relLay1.setVisibility( View.VISIBLE );
            relLay2.setVisibility( View.VISIBLE );
            relLay3.setVisibility( View.VISIBLE );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relLay1 = (RelativeLayout)findViewById( R.id.rl1Login );
        relLay2 = (RelativeLayout)findViewById( R.id.rl2Login );
        relLay3 = (RelativeLayout)findViewById( R.id.rl3Login );

        handler.postDelayed( runnable, 1000 );

        textV5 = findViewById( R.id.Text5Login );
        textV5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent( Login.this,Register.class );
                startActivity( sign );
            }
        } );

        String text = "Sign UP";

        SpannableString sp = new SpannableString( text );
        ClickableSpan clickableSpan1  = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState( ds );
                ds.setColor( Color.BLUE );
            }
        };
        sp.setSpan( clickableSpan1, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textV5.setText( sp );
        textV5.setMovementMethod( LinkMovementMethod.getInstance() );

        btnLogin = findViewById( R.id.btn1Login );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogin = new Intent( Login.this, MyAccount.class );
                startActivity( iLogin );
            }
        } );
    }
}
