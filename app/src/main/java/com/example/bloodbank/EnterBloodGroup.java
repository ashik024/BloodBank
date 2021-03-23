package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EnterBloodGroup extends AppCompatActivity {


    Button button9;

    CardView blood1;
    CardView blood2;
    CardView blood3;
    CardView blood4;
    CardView blood5;
    CardView blood6;
    CardView blood7;
    CardView blood8;

    String bloodGroup;

    TextView text;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    SharedPreferences sharedPreferencesBlood;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_blood_group);

        button9= findViewById(R.id.startbtn9);

        blood1=findViewById(R.id.card11);
        blood2=findViewById(R.id.card22);
        blood3=findViewById(R.id.card33);
        blood4=findViewById(R.id.card44);
        blood5=findViewById(R.id.card55);
        blood6=findViewById(R.id.card66);
        blood7=findViewById(R.id.card77);
        blood8=findViewById(R.id.card88);

        text=findViewById(R.id.blood);
        text2=findViewById(R.id.blood2);
        text3=findViewById(R.id.blood3);
        text4=findViewById(R.id.blood4);
        text5=findViewById(R.id.blood5);
        text6=findViewById(R.id.blood6);
        text7=findViewById(R.id.blood7);
        text8=findViewById(R.id.blood8);

         sharedPreferencesBlood= getSharedPreferences("UserBloodGroup", Context.MODE_PRIVATE);
         editor= sharedPreferencesBlood.edit();



        blood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="A+";
                blood1.setCardBackgroundColor(Color.parseColor("#F60404"));
                text.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();

                blood2.setEnabled(false);
                blood3.setEnabled(false);
                blood4.setEnabled(false);
                blood5.setEnabled(false);
                blood6.setEnabled(false);
                blood7.setEnabled(false);
                blood8.setEnabled(false);


            }
        });
        blood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="A-";
                blood2.setCardBackgroundColor(Color.parseColor("#F60404"));
                text2.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();
                blood1.setEnabled(false);

                blood3.setEnabled(false);
                blood4.setEnabled(false);
                blood5.setEnabled(false);
                blood6.setEnabled(false);
                blood7.setEnabled(false);
                blood8.setEnabled(false);
            }
        });
        blood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="B+";
                blood3.setCardBackgroundColor(Color.parseColor("#F60404"));
                text3.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();
                blood1.setEnabled(false);
                blood2.setEnabled(false);

                blood4.setEnabled(false);
                blood5.setEnabled(false);
                blood6.setEnabled(false);
                blood7.setEnabled(false);
                blood8.setEnabled(false);

            }
        });
        blood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="B-";
                blood4.setCardBackgroundColor(Color.parseColor("#F60404"));
                text4.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();
                blood1.setEnabled(false);
                blood2.setEnabled(false);
                blood3.setEnabled(false);

                blood5.setEnabled(false);
                blood6.setEnabled(false);
                blood7.setEnabled(false);
                blood8.setEnabled(false);

            }
        });
        blood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="O+";
                blood5.setCardBackgroundColor(Color.parseColor("#F60404"));
                text5.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();
                blood1.setEnabled(false);
                blood2.setEnabled(false);
                blood3.setEnabled(false);
                blood4.setEnabled(false);

                blood6.setEnabled(false);
                blood7.setEnabled(false);
                blood8.setEnabled(false);

            }
        });
        blood6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="O-";
                blood6.setCardBackgroundColor(Color.parseColor("#F60404"));
                text6.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();
                blood1.setEnabled(false);
                blood2.setEnabled(false);
                blood3.setEnabled(false);
                blood4.setEnabled(false);
                blood5.setEnabled(false);

                blood7.setEnabled(false);
                blood8.setEnabled(false);

            }
        });
        blood7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="AB+";
                blood7.setCardBackgroundColor(Color.parseColor("#F60404"));
                text7.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();
                blood1.setEnabled(false);
                blood2.setEnabled(false);
                blood3.setEnabled(false);
                blood4.setEnabled(false);
                blood5.setEnabled(false);
                blood6.setEnabled(false);

                blood8.setEnabled(false);

            }
        });
        blood8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroup="AB-";
                blood8.setCardBackgroundColor(Color.parseColor("#F60404"));
                text8.setTextColor(Color.parseColor("#FFFFFFFF"));
                editor.putString("Blood",bloodGroup);
                editor.commit();
                blood1.setEnabled(false);
                blood2.setEnabled(false);
                blood3.setEnabled(false);
                blood4.setEnabled(false);
                blood5.setEnabled(false);
                blood6.setEnabled(false);
                blood7.setEnabled(false);


            }
        });


        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bloodGroup!=null) {
                    Log.i("tag",bloodGroup);
                    Intent intent = new Intent(EnterBloodGroup.this, ContactInfo.class);
                    startActivity(intent);
                }else  {
                    Toast.makeText(EnterBloodGroup.this, "Enter Your Blood Group", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}