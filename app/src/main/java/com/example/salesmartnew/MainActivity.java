package com.example.salesmartnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  static int SPLASH_SCREEN = 5000;

    TextView welcome;
    ImageView splashImage;
    Animation topAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restorePrefData()){
            Intent welcomeIntent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(welcomeIntent);
            finish();
        }

        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        welcome = findViewById(R.id.tv1_StartUP);
        splashImage = findViewById(R.id.imageLoad);

        welcome.setAnimation(topAnim);
        splashImage.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( MainActivity.this, WelcomeScreen.class );
                startActivity( intent );
                saveData();
                finish();
            }
        },SPLASH_SCREEN);
    }

    private void saveData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isStartUpOpen", true);
        editor.commit();
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        boolean isWelcomeActivityBefore = pref.getBoolean("isStartUpOpened", false);
        return isWelcomeActivityBefore;
    }
}
