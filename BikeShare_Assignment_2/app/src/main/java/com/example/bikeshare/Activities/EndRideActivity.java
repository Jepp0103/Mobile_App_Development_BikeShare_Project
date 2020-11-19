package com.example.bikeshare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.BikeApplication;
import com.example.bikeshare.R;
import com.example.bikeshare.Ride;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

public class EndRideActivity extends Activity {
    // GUI variables
    private TextView mNewWhat;
    private TextView mEnd;
    private Button mEndRide;
    private String bikeName;
    private String bikeStart;
    Realm rideRealm;
    BikeApplication bApp;

    private Ride mRide = new Ride("", "", "");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_ride);
        rideRealm = Realm.getDefaultInstance();


        // Button
        mEndRide = (Button) findViewById(R.id.end_button);

        // Texts
        bikeName = getIntent().getStringExtra("bikeName");
        mNewWhat = (TextView) findViewById(R.id.what_text);
        mNewWhat.setText(bikeName);

        bikeStart = getIntent().getStringExtra("bikeStart");
        mEnd = (TextView) findViewById(R.id.end_text);
        mEnd.setText(bikeStart);


        //View products click event
        mEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mNewWhat.getText().length() > 0) && (mEnd.getText().length() > 0)) {
                    //Setting time and date for ride
                    SimpleDateFormat df = new SimpleDateFormat();
                    String date = df.format(Calendar.getInstance().getTime());

                    String bikeName = mNewWhat.getText().toString().trim();
                    String end = mEnd.getText().toString().trim();
                    mRide.setBikeName(bikeName);
                    mRide.setEndRide(end + "\n End date: " + date);
                     try {
                            mRide.setStartRide(rideRealm.where(Ride.class).equalTo("mBikeName", bikeName).findFirst().getStartRide());
                        } catch (NullPointerException e) {
                            Log.e("Name error", e.toString());
                        }

                        //PopUp to show success
                        Toast toast = Toast.makeText(EndRideActivity.this, "End ride added", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, -200);
                        toast.show();

                    rideRealm.executeTransactionAsync(realm -> realm.insertOrUpdate(mRide));


                    // Reset text fields
                    mNewWhat.setText("");
                    mEnd.setText("");
                }
            }
        });

    }

}
