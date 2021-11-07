package com.university.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.university.authenticationapp.databinding.ActivityMobileVerificationBinding;


import java.util.concurrent.TimeUnit;

public class MobileVerification extends AppCompatActivity {

    private String number;
    private ActivityMobileVerificationBinding binding;
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_mobile_verification);

        FirebaseAuth auth = FirebaseAuth.getInstance();


        binding.verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = binding.editMn.getEditableText().toString().trim();
                if(number.length() != 10){
                    Log.e("TAG",number);
                    Toast.makeText(getApplicationContext(),"Invalid Phone Number "+number.length(),Toast.LENGTH_LONG).show();
                }
                else{
                    Log.e("TAG",number);
                    otpVerification();

                }


            }
        });
        binding.emailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MobileVerification.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void otpVerification() {


            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber("+91" + number)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(this)
                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onCodeSent(String verificationId,
                                               PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            Intent intent = new Intent(MobileVerification.this, VerifyOtpActivity.class)
                                    .putExtra("number", number).putExtra("otpId", verificationId);
                            startActivity(intent);


                        }

                        @Override
                        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


                        }

                        @Override
                        public void onVerificationFailed(FirebaseException e) {
                            binding.editMn.setText("");
                            Log.e("TAG", e.getLocalizedMessage());
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
//        }
//        catch (Exception e){
//            Log.e("TAG",e.toString());
//        }


    }
}