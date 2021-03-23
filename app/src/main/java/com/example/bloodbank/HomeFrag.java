package com.example.bloodbank;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.ModelClass.DonorInfo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Map;


public class HomeFrag extends Fragment {

    SliderView sliderView;
    SliderAdp sliderAdp;
    LineChart lineChart;

    SwitchMaterial switchMaterial;

     FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    String Donerimg;
    String Doneraddress;
    String Donercity;
    String Donerpostal;

    String Donergender;
    String Donerweight;
    String Donerbloddgroup;

    String Doneronephone;
    String Donertwophone;
    String Donerwebsite;

    String Donername;
    String DonerEmail;




    String Donorid;

    int [] images={ R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view= inflater.inflate(R.layout.fragment_home, container, false);;


        sliderView=view.findViewById(R.id.imageSlider);

        lineChart=view.findViewById(R.id.linechart);

        switchMaterial=view.findViewById(R.id.swh);




        switchMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchMaterial.isChecked()){


                    Donorid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                    firebaseDatabase= FirebaseDatabase.getInstance();
                     databaseReference= firebaseDatabase.getReference().child("user").child(Donorid);
                     getinformation();
                }else {

                    FirebaseDatabase.getInstance().getReference().child("Donor").child(Donorid).removeValue();
                }
            }
        });


        sliderAdp= new SliderAdp(images);
        sliderView.setSliderAdapter(sliderAdp);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(),"data set");
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);

        LineData lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();



        lineChart.setNoDataText("Data not Available");

        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setLineWidth(5);





        return view;
    }

    private void getinformation() {



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Map<String,Object> map = (Map<String, Object>) snapshot.getValue();
                    if (map.get("useraddress")!=null){
                        Doneraddress=map.get("useraddress").toString();





                    }
                    if (map.get("usercity")!=null){
                        Donercity=map.get("usercity").toString();

                    }
                    if (map.get("userpostal")!=null){
                        Donerpostal=map.get("userpostal").toString();

                    }
                    if (map.get("usergender")!=null){
                        Donergender=map.get("usergender").toString();

                    }
                    if (map.get("userbloodgroup")!=null){
                        Donerbloddgroup=map.get("userbloodgroup").toString();

                    }
                    if (map.get("userphoneoone")!=null){
                        Doneronephone=map.get("userphoneoone").toString();

                    }
                    if (map.get("userphoneotwo")!=null){
                        Donertwophone=map.get("userphoneotwo").toString();

                    }
                    if (map.get("userimage")!=null){
                        Donerimg=map.get("userimage").toString();

                    }
                    if (map.get("username")!=null){
                        Donername=map.get("username").toString();

                    }
                    if (map.get("useremail")!=null){
                        DonerEmail=map.get("useremail").toString();

                    }

                    DonorInfo donorInfo = new DonorInfo(Donername,DonerEmail,Donerimg,Donergender,Doneronephone,
                            Donertwophone,Donerbloddgroup,Doneraddress,Donercity,Donerpostal);

                    FirebaseDatabase.getInstance().getReference().child("Donor").child(Donorid).setValue(donorInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                switchMaterial.setText("Available");
                            }

                        }
                    });






                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<Entry> lineChartDataSet(){
        ArrayList<Entry> dataSet = new ArrayList<Entry>();

        dataSet.add(new Entry(0,40));
        dataSet.add(new Entry(1,10));
        dataSet.add(new Entry(2,15));
        dataSet.add(new Entry(3,12));
        dataSet.add(new Entry(4,20));
        dataSet.add(new Entry(5,50));
        dataSet.add(new Entry(6,23));
        dataSet.add(new Entry(7,34));
        dataSet.add(new Entry(8,12));
        return  dataSet;


    }
}