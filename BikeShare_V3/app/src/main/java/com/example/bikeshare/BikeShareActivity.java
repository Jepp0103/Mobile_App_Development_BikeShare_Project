package com.example.bikeshare;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.List;

public class BikeShareActivity extends Activity {
    // GUI variables
    private Button mAddRide;
    private Button mEndRide;
    private static RidesDB sRidesDB ;
    private TextView mNewWhat;
    private TextView mNewWhere;
    RideArrayAdapter mAdapter;
    ListView mListView;
    RidesDB ridesDB;
    FragmentManager fragmentManager;
    private Ride mRide = new Ride("", "", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);

        // Singleton
        sRidesDB = RidesDB.get(this);
        List<Ride> values = sRidesDB.getRidesDB();

        // Adapter
        mAdapter = new RideArrayAdapter(this, values);
        mListView = (ListView) findViewById(R.id.main_list_view);
        mListView.setAdapter(mAdapter);


        // Button
        mAddRide = (Button) findViewById(R.id.add_button);
        mEndRide = (Button) findViewById(R.id.end_button);

        // Texts
        mNewWhat = (TextView) findViewById(R.id.what_text);
        mNewWhere = (TextView) findViewById(R.id.where_text);

        //View products click event
        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BikeShareActivity.this, StartRideActivity.class);
                startActivity(intent);
            }
        });

        mEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BikeShareActivity.this, EndRideActivity.class);
                startActivity(intent);
            }
        });
        }

}

