package com.example.bikeshare.Activities;
import android.support.v4.app.*;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.bikeshare.Fragments.BikeShareFragment;
import com.example.bikeshare.R;



public class BikeShareActivity extends FragmentActivity{

    // GUI variable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.fragment_bike_share);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new BikeShareFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
            }
        }
    }





