package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class Home extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        chipNavigationBar= findViewById(R.id.nav);
        chipNavigationBar.setItemSelected(R.id.home,true);



        getSupportFragmentManager().beginTransaction().replace(R.id.relative,new HomeFrag()).commit();

        BottomNavOption();
    }

    private void BottomNavOption() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                Fragment fragment= null;

                switch (i){

                    case R.id.home:
                        fragment= new HomeFrag();
                        break;
                    case R.id.search:
                        fragment= new MapFrag();
                        break;
                    case R.id.Fav:
                        fragment= new NotifiFrag();
                        break;
                    case R.id.profile:
                        fragment= new ProfileFrag();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.relative,fragment).commit();

            }
        });
    }
}