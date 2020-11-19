package com.example.bikeshare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.R;
import com.example.bikeshare.Ride;

import io.realm.Realm;

public class EndRideActivity extends Activity {
    // GUI variables
    private TextView mNewWhat;
    private TextView mEnd;
    private Button mEndRide;
    private String bikeName;
    private String bikeStart;
    private Ride mRide = new Ride("", "", "");

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_ride);
        realm = Realm.getDefaultInstance();


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
                    String name = mNewWhat.getText().toString().trim();
                    String end = mEnd.getText().toString().trim();
                    mRide.setBikeName(name);
                    mRide.setEndRide(end);
                    try {
                        mRide.setStartRide(realm.where(Ride.class).equalTo("mBikeName", name).findFirst().getStartRide());
                    } catch (NullPointerException e) {
                        Log.e("Name error", e.toString());
                    }

                    realm.executeTransactionAsync(realm -> realm.insertOrUpdate(mRide));

                    //PopUp to show success
                    Toast toast = Toast.makeText(EndRideActivity.this, "End ride added", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, -200);
                    toast.show();
                    // Reset text fields
                    mNewWhat.setText("");
                    mEnd.setText("");
                }
            }
        });
    }

}
