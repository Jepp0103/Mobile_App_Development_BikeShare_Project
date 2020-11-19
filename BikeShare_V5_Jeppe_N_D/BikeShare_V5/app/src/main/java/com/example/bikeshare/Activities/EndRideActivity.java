package com.example.bikeshare.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.R;
import com.example.bikeshare.Ride;
import com.example.bikeshare.RidesDB;

public class EndRideActivity extends Activity {
    // GUI variables
    private TextView mNewWhat;
    private TextView mNewWhere;
    private Button mEndRide;
    private Button mGoBack;
    private String bikeName;
    private String bikeStart;
    private Ride mRide = new Ride("", "", "");
    private static RidesDB sRidesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_ride);
        sRidesDB = RidesDB.get(this);


        // Button
        mEndRide = (Button) findViewById(R.id.end_button);

        // Texts
        bikeName = getIntent().getStringExtra("bikeName");
        mNewWhat = (TextView) findViewById(R.id.what_text);
        mNewWhat.setText(bikeName);

        bikeStart = getIntent().getStringExtra("bikeStart");
        mNewWhere = (TextView) findViewById(R.id.end_text);
        mNewWhere.setText(bikeStart);


        //View products click event
        mEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mNewWhat.getText().length() > 0) && (mNewWhere.getText().length() > 0)) {
                    mRide.setBikeName(mNewWhat.getText().toString().trim());
                    mRide.setStartRide(mNewWhere.getText().toString().trim());

                    sRidesDB.endRide(mNewWhat.getText().toString(),mNewWhere.getText().toString());

                    //PopUp to show success
                    Toast toast = Toast.makeText(EndRideActivity.this, "End ride added", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, -200);
                    toast.show();
                    // Reset text fields
                    mNewWhat.setText("");
                    mNewWhere.setText("");
                }
            }
        });
    }

}
