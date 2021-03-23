package com.example.bloodbank;

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
import android.widget.Toast;

public class EnterAdress extends AppCompatActivity {
    Button button6;
    EditText adress;
    EditText city;
    EditText postal;

    String Uadress ;
    String Ucity;
    String Upostal;

    SharedPreferences sharedPreferencesAddress;

    SharedPreferences.Editor editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_adress);

        button6= findViewById(R.id.startbtn6);

        adress=findViewById(R.id.address);
        city=findViewById(R.id.city);
        postal=findViewById(R.id.postal);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveinfo();

            }
        });
    }

    private void saveinfo() {

            Uadress = adress.getText().toString();
            Ucity= city.getText().toString().trim();
            Upostal = postal.getText().toString().trim();

        if(TextUtils.isEmpty(Uadress)){

            Toast.makeText(EnterAdress.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(Ucity)){

            Toast.makeText(EnterAdress.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(Upostal)){

            Toast.makeText(EnterAdress.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }
        else{
            sharedPreferencesAddress= getSharedPreferences("UserAddress", Context.MODE_PRIVATE);

            editor2=sharedPreferencesAddress.edit();
            editor2.putString("address",Uadress);
            editor2.putString("city",Ucity);
            editor2.putString("postal",Upostal);
            editor2.commit();

            Log.d("2tag",sharedPreferencesAddress.getString("address","No Data Found"));

                Intent intent = new Intent(EnterAdress.this,EnterGender.class);
                startActivity(intent);


        }


    }
}