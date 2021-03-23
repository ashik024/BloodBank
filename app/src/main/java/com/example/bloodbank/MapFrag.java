package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.ModelClass.BookedHospitalUser;
import com.example.bloodbank.ModelClass.HospitalInfo;
import com.example.bloodbank.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MapFrag extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    GoogleApiClient googleApiClient;
    Location lastlocation;
    LocationRequest locationRequest;
    private GoogleMap mMap;

    CardView card;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button button;

    Marker marker;


    SupportMapFragment mapFragment;
//    Marker marker1,marker2,marker3,marker4,marker5,marker6;

    String hospitalname;
    String hospitaladdress;
    String hospitalphone;
    String hospitalrating;
    String hospitalstatus;


    String bookername;
    String bookeraddress;
    String bookerphone;
    String hosname;



    FirebaseDatabase firebaseDatabase;
    ValueEventListener databaseReference;

    SwitchMaterial switchMaterial;

    ArrayList<LatLng> arrayList= new ArrayList<LatLng>();
    LatLng Hospital1 = new LatLng(23.83151100010753, 90.41675226718466);
    LatLng Hospital2 = new LatLng(23.829724827167425, 90.42042152905809);
    LatLng Hospital3 = new LatLng(23.83230593729829, 90.41639821560038);
    LatLng Hospital4 = new LatLng(23.8289396884234, 90.41646258861572);
    LatLng Hospital5 = new LatLng(23.828929874159027, 90.42139785312384);


    ArrayList<String> title= new ArrayList<String>();
    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //Location Permission already granted
                    buildgoogleapi();
                    mMap.setMyLocationEnabled(true);
                } else {
                    //Request Location Permission
                    checkLocationPermission();
                }
            } else {
                buildgoogleapi();
                mMap.setMyLocationEnabled(true);
            }


            arrayList.add(Hospital1);
            arrayList.add(Hospital2);
            arrayList.add(Hospital3);
            arrayList.add(Hospital4);
            arrayList.add(Hospital5);


            title.add("Hospital1");
            title.add("Hospital2");
            title.add("Hospital3");
            title.add("Hospital4");
            title.add("Hospital5");















        }




    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_map, container, false);
        card= view.findViewById(R.id.mapcard);

        textView1=view.findViewById(R.id.namemap);
        textView2=view.findViewById(R.id.ratingmap);
        textView3=view.findViewById(R.id.addresssmap);
        textView4=view.findViewById(R.id.status);
        button=view.findViewById(R.id.book);

        switchMaterial=view.findViewById(R.id.swhmap);

        switchMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchMaterial.isChecked()){
                    getHospitalinfo();

                    switchMaterial.setText("Hospital");

                }else {
                        mMap.clear();
                    switchMaterial.setText("Donors");
                    Toast.makeText(getContext(), "Donor Section", Toast.LENGTH_SHORT).show();
                }
            }
        });




        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lastlocation = location;


        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap = googleMap;
        buildgoogleapi();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    protected synchronized void buildgoogleapi() {
        googleApiClient= new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();


    }
////    public static final int Location_Request_Code=1;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mapFragment.getMapAsync(this::onMapReady);

//                    // permission was granted, yay! Do the
//                    // location-related task you need to do.
//                    if (ContextCompat.checkSelfPermission(getContext(),
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        if (googleApiClient == null) {
//                            buildgoogleapi();
//                        }
//                        mMap.setMyLocationEnabled(true);
//                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getContext())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions((Activity) getContext(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }


//    @Override
//    public boolean onMarkerClick(final Marker marker) {
//
//        if (marker.equals(marker1))
//        {
//            Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }


    private void getHospitalinfo() {

        for (int i=0; i<arrayList.size();i++){

            for (int j =0;j<title.size();j++){

                marker=  mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(i))));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

               hosname= marker.getTitle();
                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=  firebaseDatabase.getReference().child("Hospital").child(hosname).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Map<String,Object> map = (Map<String, Object>) snapshot.getValue();
                            if (map.get("name")!=null){
                                hospitalname=map.get("name").toString();
                                Log.i("map",hosname);
                                textView1.setText(hospitalname);
                            }
                            if (map.get("address")!=null){
                                hospitaladdress=map.get("address").toString();
                                Log.i("map",hospitaladdress);
                                textView3.setText(hospitaladdress);
                            }
                            if (map.get("contactnum")!=null){
                                hospitalphone=map.get("contactnum").toString();
                                Log.i("map",hospitalphone);
                            }
                            if (map.get("rating")!=null){
                                hospitalrating=map.get("rating").toString();
                                Log.i("map",hospitalrating);
                                textView2.setText(hospitalrating);                          }
                            if (map.get("status")!=null){
                                hospitalstatus=map.get("status").toString();
                                Log.i("map",hospitalstatus);
                                textView4.setText(hospitalstatus);
                            }
                            card.setVisibility(View.VISIBLE);


                            HospitalInfo hospitalInfo = new HospitalInfo(hospitalname,hospitaladdress,hospitalphone,hospitalrating,hospitalstatus);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String userid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    firebaseDatabase= FirebaseDatabase.getInstance();
                                    databaseReference= firebaseDatabase.getReference().child("user").child(userid).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            if (snapshot.exists()) {
                                                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                                                if (map.get("username")!=null){
                                                    bookername=map.get("username").toString();

                                                }
                                                if (map.get("userphoneoone")!=null){
                                                    bookerphone=map.get("userphoneoone").toString();

                                                }
                                                if (map.get("useraddress") != null) {
                                                    bookeraddress = map.get("useraddress").toString();
                                                }

                                                BookedHospitalUser bookedHospitalUser = new BookedHospitalUser(bookername,bookerphone,bookeraddress);


                                                FirebaseDatabase.getInstance().getReference().child("HospitalBooked").child(hosname).child(userid).setValue(bookedHospitalUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        Toast.makeText(getContext(), "Booked", Toast.LENGTH_SHORT).show();
                                                        card.setVisibility(View.INVISIBLE);

                                                    }
                                                });

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return false;
            }
        });



    }
}