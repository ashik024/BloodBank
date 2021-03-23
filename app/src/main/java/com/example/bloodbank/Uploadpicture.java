package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Uploadpicture extends AppCompatActivity {

    Button button4;
    Uri imageuri;
    Uri pasimglink;
    String imglink;

    SharedPreferences sharedPreferencesImg;
    SharedPreferences.Editor editor;

    CardView cardView;
    ImageView imageView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpicture);
        button4= findViewById(R.id.startbtn4);
        cardView=findViewById(R.id.uploadCard);
        imageView= findViewById(R.id.upload);

        textView= findViewById(R.id.skip);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(Uploadpicture.this,Login.class);
                startActivity(intent3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadPicture();

            }
        });


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);


            }
        });
    }

    private void uploadPicture() {
        if (imageuri!=null) {

            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("userImage");
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), imageuri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
            byte[] data = byteArrayOutputStream.toByteArray();
            UploadTask uploadTask = storageReference.putBytes(data);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Uploadpicture.this, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("tag",e.getMessage().toString());
                }
            });
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    if (!taskSnapshot.equals(null)){

                        Task<Uri> imageuri =taskSnapshot.getMetadata().getReference().getDownloadUrl();

//                        Uri uri = taskSnapshot.getMetadata().getReference().getDownloadUrl().getResult();
//
//                        Map image = new HashMap();
//                        image.put("image",imageuri.toString());
//                        pasDatabase.updateChildren(image);
//                        Toast.makeText(PassengerProfile.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                        imageuri.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                if (task.isSuccessful()){

                                    pasimglink = task.getResult();
                                    imglink=pasimglink.toString();

                                    sharedPreferencesImg=getSharedPreferences("userImg", Context.MODE_PRIVATE);
                                    editor= sharedPreferencesImg.edit();
                                    editor.putString("img",imglink);
                                    editor.commit();

                                    Log.i("tagimg",imglink);
//                                            Map image = new HashMap();
//                                            image.put("image",pasimglink.toString());
//                                            pasDatabase.updateChildren(image);
                                    Toast.makeText(Uploadpicture.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                                    Intent intent2 = new Intent(Uploadpicture.this,EnterBirthdate.class);
                                    startActivity(intent2);

                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(Uploadpicture.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else{
            Toast.makeText(this, "Upload A picture", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode== Activity.RESULT_OK)  {

            final Uri finalimageUri = data.getData();
            imageuri = finalimageUri;

            imageView.setImageURI(imageuri);


        }
    }


}