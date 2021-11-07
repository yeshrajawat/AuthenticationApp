package com.university.authenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        changeFocus();


        binding.tvResendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerifyOtpActivity.this,"OTP sent Successfully",Toast.LENGTH_LONG).show();
            }
        });

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String one = binding.etC1.toString().trim();
                String two=  binding.etC2.toString().trim();
                String three=binding.etC3.toString() .trim();
                String four = binding.etC4.toString().trim();
                String five = binding.etC5.toString().trim();
                String six = binding.etC6.toString().trim();
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

    private void changeFocus() {

        binding.etC1.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            binding.etC2.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
        binding.etC2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etC3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etC4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etC5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.etC6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
}