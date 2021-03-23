package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbank.ModelClass.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Contactinfo2 extends AppCompatActivity {

    Button button11;

    EditText username;
    EditText email;
    EditText password;

    SharedPreferences sharedPreferencesContactTwo;
    SharedPreferences.Editor editor;




    String img;
    String address;
    String city;
    String postal;

    String gender;
    String weight;
    String bloddgroup;

    String onephone;
    String twophone;
    String website;

    String Uname;
    String Uemail;

    int birthday;
    int birthmonth;
    int birthyear;

    FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactinfo2);

        button11= findViewById(R.id.startbtn11);


        username= findViewById(R.id.username);
        email= findViewById(R.id.Email);
        password= findViewById(R.id.pass);

        mauth=FirebaseAuth.getInstance();


//        address=getIntent().getStringExtra("UserInfoadd");
//        Toast.makeText(this, "Address"+ address, Toast.LENGTH_LONG).show();






        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registeruser();

            }
        });


    }

    private void registeruser() {

         Uname = username.getText().toString();
         Uemail = email.getText().toString().trim();
        String Upassword = password.getText().toString().trim();





        if(TextUtils.isEmpty(Uname)){

            Toast.makeText(Contactinfo2.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(Uemail)){

            Toast.makeText(Contactinfo2.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(Upassword)){

            Toast.makeText(Contactinfo2.this, "Complete All Fields", Toast.LENGTH_SHORT).show();
        }


        if(Upassword.length() < 6){
            Toast.makeText(Contactinfo2.this, "Password Length Should Be At least 6", Toast.LENGTH_SHORT).show();;
        }else {

            sharedPreferencesContactTwo=getSharedPreferences("UserContact2", Context.MODE_PRIVATE);
            editor= sharedPreferencesContactTwo.edit();
            editor.putString("username",Uname);
            editor.putString("email",Uemail);
            editor.putString("pass",Upassword);
            editor.commit();




            SharedPreferences sharedPreferencesZero=getSharedPreferences("UserContact1", Context.MODE_PRIVATE);
            onephone = sharedPreferencesZero.getString("PhoneNumOne","No Data Found");
            twophone= sharedPreferencesZero.getString("PhoneNumTwo","No Data Found");
            website= sharedPreferencesZero.getString("Web","No Data Found");

            SharedPreferences sharedPreferences=getSharedPreferences("BirthDate", Context.MODE_PRIVATE);
            birthday= sharedPreferences.getInt("BDay",00);
            birthmonth=sharedPreferences.getInt("BMonth",00);
            birthyear=sharedPreferences.getInt("BYear",00);

            SharedPreferences sharedPreferences2=getSharedPreferences("UserAddress",Context.MODE_PRIVATE);
            address=sharedPreferences2.getString("address","No Data");
            city=sharedPreferences2.getString("city","No Data");
            postal=sharedPreferences2.getString("postal","No Data");

            SharedPreferences sharedPreferences3=getSharedPreferences("UserGender",Context.MODE_PRIVATE);
            gender=sharedPreferences3.getString("Gender","No Data");

            SharedPreferences sharedPreferences4=getSharedPreferences("UserWeight",Context.MODE_PRIVATE);
            weight=sharedPreferences4.getString("Weight","No Data");

            SharedPreferences sharedPreferences5=getSharedPreferences("UserBloodGroup",Context.MODE_PRIVATE);
            bloddgroup=sharedPreferences5.getString("Blood","No Data");

            SharedPreferences sharedPreferences6=getSharedPreferences("userImg",Context.MODE_PRIVATE);
            img=sharedPreferences6.getString("img","No Data");

//            SharedPreferences sharedPreferences6=getSharedPreferences("UserContact2",Context.MODE_PRIVATE);
//            username=sharedPreferences6.getString("username","No Data");
//            email=sharedPreferences6.getString("email","No Data");
//            pass=sharedPreferences6.getString("pass","No Data");





            Log.i("tag23455",onephone);
            Log.i("tag23445",twophone);
            Log.i("tag23445",website);

            Log.i("tag23445",String.valueOf(birthyear));

            Log.i("tag23445",city);
            Log.i("tag23445",postal);
            Log.i("tag23445",gender);
            Log.i("tag23445",weight);
            Log.i("tag23445",bloddgroup);
            Log.i("tag23445",img);


            Log.i("tag23445",Upassword);



            mauth.createUserWithEmailAndPassword(Uemail,Upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){


                        UserInfo userInfo = new UserInfo(address,city,postal,gender,weight,bloddgroup,onephone,twophone,website,birthday,birthmonth,birthyear,img,Uname,Uemail);

                        String userid= mauth.getCurrentUser().getUid();
                        FirebaseDatabase.getInstance().getReference().child("user").child(userid).setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(Contactinfo2.this, "Registration Completed ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Contactinfo2.this,Login.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(Contactinfo2.this, "Error In Registration", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                }
            });




        }


    }
}