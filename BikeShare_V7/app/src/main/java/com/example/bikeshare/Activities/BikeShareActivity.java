package com.example.bikeshare.Activities;
import android.support.v4.app.*;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bikeshare.Fragments.BikeShareFragment;
import com.example.bikeshare.R;
import com.example.bikeshare.Ride;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmResults;


public class BikeShareActivity extends FragmentActivity{
    Realm realm;
    // GUI variable
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





