package com.university.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.university.authenticationapp.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();
        binding = DataBindingUtil.setContentView(SignInActivity.this,R.layout.activity_sign_in);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });



    }

    private void logIn() {
        String email = binding.userEdittext.getEditableText().toString();
        String password = binding.passEdittext.getEditableText().toString();

        if(!email.isEmpty() &&! password.isEmpty()){
          try {
              auth.signInWithEmailAndPassword(email,password);
              Toast.makeText(SignInActivity.this, "LoginSuccessfully", Toast.LENGTH_SHORT).show();

              if(auth.getCurrentUser()!=null){
                  Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                  startActivity(intent);
                  finish();
              }
          }
          catch (Exception e){
              Toast.makeText(SignInActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
          }

        }


    }
}