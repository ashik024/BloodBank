package com.example.bloodbank;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;

public class EnterBirthdate extends AppCompatActivity {

    DatePicker datePicker;
    Button button5;
    SharedPreferences sharedPreferencesBirth;

    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_birthdate);
        datePicker= findViewById(R.id.date);

        button5= findViewById(R.id.startbtn5);






        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    saveBithdate();
            }
        });
    
    }


    private void saveBithdate() {


        int day= datePicker.getDayOfMonth();
        int month=datePicker.getMonth();
        int year= datePicker.getYear();
        Log.i("tag", String.valueOf(day));
        Log.i("tag2", String.valueOf(month));
        Log.i("tag3", String.valueOf(year));

        sharedPreferencesBirth=getSharedPreferences("BirthDate", Context.MODE_PRIVATE);
        editor=sharedPreferencesBirth.edit();
        editor.putInt("BDay",day);
        editor.putInt("BMonth",month);
        editor.putInt("BYear",year);

        editor.commit();
        if (!datePicker.equals("")) {

            Intent intent = new Intent(EnterBirthdate.this, EnterAdress.class);
            startActivity(intent);
        }else{
            Toast.makeText(EnterBirthdate.this, "Enter your BirthDate.", Toast.LENGTH_SHORT).show();
        }


//       int dateuser= sharedPreferencesBirth.getInt("BDay",11);
//
//       Log.i("tag4", String.valueOf(dateuser));
    }
}