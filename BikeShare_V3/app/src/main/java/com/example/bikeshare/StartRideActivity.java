package com.example.bikeshare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class StartRideActivity extends Activity {
    // GUI variables
    private Button mAddRide;
    private TextView mNewWhat;
    private TextView mNewWhere;
    BikeShareActivity bikeShareActivity;
    private Ride mRide = new Ride("", "", "");
    private static RidesDB sRidesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sRidesDB = RidesDB.get(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);
        // Button
        mAddRide = (Button) findViewById(R.id.addRide_button);

        // Texts
        mNewWhat = (TextView) findViewById(R.id.what_text);
        mNewWhere = (TextView) findViewById(R.id.where_text);

        //View products click event
        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mNewWhat.getText().length() > 0) && (mNewWhere.getText().length() > 0)) {
                    mRide.setBikeName(mNewWhat.getText().toString().trim());
                    mRide.setStartRide(mNewWhere.getText().toString().trim());

                    //Adding rides to list
                    sRidesDB.addRide(mNewWhat.getText().toString(),mNewWhere.getText().toString());

                    // Reset text fields
                    mNewWhat.setText("");
                    mNewWhere.setText("");
                    Intent intent = new Intent(StartRideActivity.this, BikeShareActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}