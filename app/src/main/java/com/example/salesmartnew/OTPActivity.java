package com.example.salesmartnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    String verificationCodeBySystem;
    EditText verifyOTP;
    Button verifyButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        verifyOTP = findViewById(R.id.ET1_OTP);
        verifyButton = findViewById(R.id.button2);
        progressBar =findViewById(R.id.progressBar_otp);

        String PhoneNo = getIntent().getStringExtra("contact");
        String Password = getIntent().getStringExtra("pw");

        sendVerificationCode(PhoneNo);
    }

    private void sendVerificationCode(String phoneNo) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+94" + phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        private void verifyCode(String codeByUsers) {

            PhoneAuthCredential credential  = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUsers);
            signInUserByCredential(credential);
        }

        private void signInUserByCredential(PhoneAuthCredential credential) {

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),ChangePassword.class);

                            }else {
                                Toast.makeText(OTPActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };
}
