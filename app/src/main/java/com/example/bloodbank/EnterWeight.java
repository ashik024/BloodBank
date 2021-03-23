package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class EnterWeight extends AppCompatActivity {
Button button8;

CardView weight1;
CardView weight2;

String weight;

    SharedPreferences sharedPreferencesWeight;
    SharedPreferences.Editor editor4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_weight);
        weight1=findViewById(R.id.Weightcard1);
        weight2=findViewById(R.id.Weightcard2);

        button8= findViewById(R.id.startbtn8);

        sharedPreferencesWeight= getSharedPreferences("UserWeight", Context.MODE_PRIVATE);
        editor4=sharedPreferencesWeight.edit();

        SharedPreferences sharedPreferences = getSharedPreferences("UserAddress", MODE_PRIVATE);
        String value = sharedPreferences.getString("city","no data");

        Log.i("tag",value);


        weight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weight= "Under50";
                weight2.setVisibility(View.INVISIBLE);
                editor4.putString("Weight",weight);
                editor4.commit();

            }
        });

        weight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weight= "Over50";
                weight1.setVisibility(View.INVISIBLE);
                editor4.putString("Weight",weight);
                editor4.commit();

            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (weight=="Under50" ||weight=="Over50" ) {

                    Log.i("tag44", sharedPreferencesWeight.getString("Weight","No Data"));
                    Intent intent = new Intent(EnterWeight.this, EnterBloodGroup.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(EnterWeight.this, "Select Your Weight", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}