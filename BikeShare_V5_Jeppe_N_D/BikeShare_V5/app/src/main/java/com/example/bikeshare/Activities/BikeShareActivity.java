package com.example.bikeshare.Activities;
import android.support.v4.app.*;
import android.os.Bundle;

import com.example.bikeshare.Fragments.BikeShareFragment;
import com.example.bikeshare.R;


public class BikeShareActivity extends FragmentActivity {
    // GUI variables
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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




