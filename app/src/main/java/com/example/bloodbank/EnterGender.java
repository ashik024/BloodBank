package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EnterGender extends AppCompatActivity {

    Button button7;
    CardView male;
    CardView female;

  String gender;

  SharedPreferences sharedPreferencesGender;
  SharedPreferences.Editor editor3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_gender);

        button7= findViewById(R.id.startbtn7);
        male= findViewById(R.id.Gencard1);
        female= findViewById(R.id.Gencard2);


        sharedPreferencesGender= getSharedPreferences("UserGender", Context.MODE_PRIVATE);
        editor3=sharedPreferencesGender.edit();


        male.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                female.setVisibility(View.INVISIBLE);
                gender="male";
                editor3.putString("Gender",gender);
                editor3.commit();

            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                male.setVisibility(View.INVISIBLE);
                gender="female";
                editor3.putString("Gender",gender);
                editor3.commit();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gender=="male" || gender== "female"){

                    Log.i("tag33", sharedPreferencesGender.getString("Gender","No Data"));
                    Intent intent = new Intent(EnterGender.this,EnterWeight.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(EnterGender.this, "Select Your Gender", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}