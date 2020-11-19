package com.example.bikeshare;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class StartRideActivity extends Activity {
    // GUI variables
    private Button mAddRide;
    private TextView mLastAdded;
    private TextView mNewWhat;
    private TextView mNewWhere;
    private Ride mLast = new Ride("", "", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);
        mLastAdded = (TextView) findViewById(R.id.last_thing);
        updateUI();
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
                    mLast.setBikeName(mNewWhat.getText().toString().trim());
                    mLast.setStartRide(mNewWhere.getText().toString().trim());

                    // Reset text fields
                    mNewWhat.setText("");
                    mNewWhere.setText("");
                    updateUI();
                }
            }
        });
    }

    private void updateUI() {
        mLastAdded.setText(mLast.toString());
    }
}