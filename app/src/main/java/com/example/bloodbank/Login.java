package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button button12;

    EditText editText1;
    EditText editText2;

    TextView textView1;
    TextView textView2;

    String Email;
    String Pass;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button12= findViewById(R.id.startbtn12);

        editText1= findViewById(R.id.LogEmail);
        editText2= findViewById(R.id.LogPass);

        textView1= findViewById(R.id.Logtext11);
        textView2= findViewById(R.id.Logtext13);

        mAuth= FirebaseAuth.getInstance();

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent2 = new Intent(Login.this,Uploadpicture.class);
              startActivity(intent2);
            }
        });



        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Loginuser();

            }
        });
    }

    private void Loginuser() {

        Email= editText1.getText().toString().trim();
        Pass= editText2.getText().toString().trim();
        if(TextUtils.isEmpty(Email)){

            Toast.makeText(Login.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(Pass)){

            Toast.makeText(Login.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }
        else {

            mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
}