package com.example.bikeshare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class BikeShareActivity extends Activity {
    // GUI variables
    private Button mAddRide;
    private Button mEndRide;

    private TextView mLastAdded;
    private TextView mNewWhat;
    private TextView mNewWhere;
    private Ride mLast = new Ride("", "", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);
        mLastAdded = (TextView) findViewById(R.id.last_thing);
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
