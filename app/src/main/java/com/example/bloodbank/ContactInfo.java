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

public class ContactInfo extends AppCompatActivity {


    Button button10;

    EditText editText1;
    EditText editText2;
    EditText editText3;

    SharedPreferences sharedPreferencesContactOne;
    SharedPreferences.Editor editor;

//    int birthday;
//    int birthmonth;
//    int birthyear;

//    String address;
//    String city;
//    String postal;
//
//    String gender;
//    String weight;
//    String bloddgroup;
//
//    String onephone;
//    String twophone;
//    String website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        button10= findViewById(R.id.startbtn10);

        editText1= findViewById(R.id.phone1);
        editText2= findViewById(R.id.phone2);
        editText3= findViewById(R.id.website);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savecontact();

            }
        });
    }

    private void savecontact() {

        String phonenum1=editText1.getText().toString().trim();
        String phonenum2=editText2.getText().toString().trim();
        String web=editText3.getText().toString().trim();

        if(TextUtils.isEmpty(phonenum1)){

            Toast.makeText(ContactInfo.this, "You Have To Provide At Least One Phone Number", Toast.LENGTH_SHORT).show();
        }else{

            sharedPreferencesContactOne=getSharedPreferences("UserContact1", Context.MODE_PRIVATE);
            editor= sharedPreferencesContactOne.edit();
            editor.putString("PhoneNumOne",phonenum1);
            editor.putString("PhoneNumTwo",phonenum2);
            editor.putString("Web",web);
            editor.commit();

//            onephone = sharedPreferencesContactOne.getString("PhoneNumOne","No Data Found");
//            twophone= sharedPreferencesContactOne.getString("PhoneNumTwo","No Data Found");
//            website= sharedPreferencesContactOne.getString("Web","No Data Found");
//
//            SharedPreferences sharedPreferences=getSharedPreferences("BirthDate",Context.MODE_PRIVATE);
//            birthday= sharedPreferences.getInt("BDay",00);
//            birthmonth=sharedPreferences.getInt("BMonth",00);
//            birthyear=sharedPreferences.getInt("BYear",00);
//
//            SharedPreferences sharedPreferences2=getSharedPreferences("UserAddress",Context.MODE_PRIVATE);
//            address=sharedPreferences2.getString("address","No Data");
//            city=sharedPreferences2.getString("city","No Data");
//            postal=sharedPreferences2.getString("postal","No Data");
//
//            SharedPreferences sharedPreferences3=getSharedPreferences("UserGender",Context.MODE_PRIVATE);
//            gender=sharedPreferences3.getString("Gender","No Data");
//
//            SharedPreferences sharedPreferences4=getSharedPreferences("UserWeight",Context.MODE_PRIVATE);
//            weight=sharedPreferences4.getString("Weight","No Data");
//
//            SharedPreferences sharedPreferences5=getSharedPreferences("UserBloodGroup",Context.MODE_PRIVATE);
//            bloddgroup=sharedPreferences5.getString("Blood","No Data");
//
//
//
//
//
//            Log.i("tag23",onephone);
//            Log.i("tag23",twophone);
//            Log.i("tag23",website);
//
//            Log.i("tag23",String.valueOf(birthyear));
//
//            Log.i("tag23",city);
//            Log.i("tag23",postal);
//            Log.i("tag23",gender);
//            Log.i("tag23",weight);



            Intent intent = new Intent(ContactInfo.this,Contactinfo2.class);
//            intent.putExtra("UserInfoadd",address);
//            intent.putExtra("UserInfocity",city);
//            intent.putExtra("UserInfopostal",postal);
//            intent.putExtra("UserInfogender",gender);
//            intent.putExtra("UserInfoweight",weight);
//            intent.putExtra("UserInfoblood",bloddgroup);
//            intent.putExtra("UserInfophn1",onephone);
//            intent.putExtra("UserInfophn2",twophone);
//            intent.putExtra("UserInfoweb",website);
//            intent.putExtra("Userinfobdate",birthday);
//            intent.putExtra("Userinfobmonth",birthmonth);
//            intent.putExtra("Userinfobyear",birthyear);
            startActivity(intent);

        }
    }
}