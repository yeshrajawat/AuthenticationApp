package com.university.authenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.university.authenticationapp.databinding.ActivityVerifyOtpBinding;

import org.w3c.dom.Text;

public class VerifyOtpActivity extends AppCompatActivity {

    private ActivityVerifyOtpBinding binding;
    private String number;
    private String otpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        number = getIntent().getStringExtra("number");
        otpId = getIntent().getStringExtra("otpId");

        binding = DataBindingUtil.setContentView(VerifyOtpActivity.this,R.layout.activity_verify_otp);



        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String one = binding.etC1.getEditableText().toString().trim();
                String two=  binding.etC2.getEditableText().toString().trim();
                String three=binding.etC3.getEditableText().toString() .trim();
                String four = binding.etC4.getEditableText().toString().trim();
                String five = binding.etC5.getEditableText().toString().trim();
                String six = binding.etC6.getEditableText().toString().trim();
                Log.e("TAG",one+two+three+four+five+six);
                if(one.isEmpty() || two.isEmpty() || three.isEmpty() ||
                        four.isEmpty() || five.isEmpty() || six.isEmpty() ){

                    Toast.makeText(VerifyOtpActivity.this, "OTP invalid", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(otpId!=null){
                        String code = one + two + three + four + five + six;
                        PhoneAuthCredential  credential = PhoneAuthProvider.getCredential(otpId,code);
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(VerifyOtpActivity.this, "Otp valid and Successfully Sign In", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(VerifyOtpActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }

                }
            }
        });



                binding.tvMobile.setText(number);

    }


}