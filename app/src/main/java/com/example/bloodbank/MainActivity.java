package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;

    ImageView imageView;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1= (Button) findViewById(R.id.startbtn);
        button2= (Button) findViewById(R.id.startbtn2);
        imageView=findViewById(R.id.welcomeimg);
        cardView=findViewById(R.id.card);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WelcomePage1.class);
                startActivity(intent);

            }
        });
    }
}