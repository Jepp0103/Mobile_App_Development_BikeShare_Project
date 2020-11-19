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

public class UpdateRideActivity extends Activity {

    private Button mUpdate;
    private TextView mBikeToUpdate;
    private TextView mWhereToUpdate;
    private TextView mEndToUpdate;
    private Realm rideRealm;
    private Ride mRide = new Ride("", "", "");
    BikeApplication bApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ride);
        rideRealm = Realm.getDefaultInstance();


        // Button
        mUpdate = (Button) findViewById(R.id.update_button);

        // Texts
        mBikeToUpdate = (TextView) findViewById(R.id.what_text_update);
        mBikeToUpdate.setText("");

        mWhereToUpdate = (TextView) findViewById(R.id.where_text_update);
        mWhereToUpdate.setText("");

        mEndToUpdate = (TextView) findViewById(R.id.end_text_update);
        mEndToUpdate.setText("");


        //View products click event
        mUpdate.setOnClickListener(view -> {
            //Setting time and date for ride
            SimpleDateFormat df = new SimpleDateFormat();
            String date = df.format(Calendar.getInstance().getTime());

            mRide.setBikeName(mBikeToUpdate.getText().toString().trim());
            mRide.setStartRide(mWhereToUpdate.getText().toString().trim() + "\n Start date: " + date);
            mRide.setEndRide(mEndToUpdate.getText().toString().trim() + "\n End date: " + date);


            rideRealm.executeTransaction(realm -> realm.insertOrUpdate(mRide));

            //PopUp to show success
            Toast toast = Toast.makeText(UpdateRideActivity.this, "Ride updated", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -200);
            toast.show();

            // Reset text fields
            mBikeToUpdate.setText("");
            mWhereToUpdate.setText("");
            mEndToUpdate.setText("");
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        rideRealm.close();
    }
}
