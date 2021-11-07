package com.university.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth ;
    Button btnRegister;
    TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        btnRegister = findViewById(R.id.btnRegister);
        signUpLink = findViewById(R.id.signUpLink);


        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();

            }
        });

    }

    private void registerUser() {
        EditText signUpUserEdittext = findViewById(R.id.signUpUserEdittext);
        String email = signUpUserEdittext.getEditableText().toString();
        EditText signUpPassEdittext = findViewById(R.id.signUpPassEdittext);
        String password = signUpPassEdittext.getEditableText().toString();

        if(!email.isEmpty() && !password.isEmpty()){

                 auth.createUserWithEmailAndPassword(email,password);
                if(auth.getCurrentUser()!=null){
                    Log.e("TAG",auth.getFirebaseAuthSettings().toString());
                    Toast.makeText(this,"Registration successful",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(this,"Registration unsuccessful",Toast.LENGTH_LONG).show();
                }

            }
        else{
                Toast.makeText(this,"Registration Failed",Toast.LENGTH_LONG).show();

        }

    }
}