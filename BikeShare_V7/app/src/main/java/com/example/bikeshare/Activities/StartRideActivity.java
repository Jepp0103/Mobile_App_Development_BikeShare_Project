package com.example.bikeshare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.R;
import com.example.bikeshare.Ride;

import io.realm.Realm;

public class StartRideActivity extends Activity {
    // GUI variables
    private Button mAddRide;
    private TextView mNewWhat;
    private TextView mNewWhere;
    private Ride mRide = new Ride("", "", "");
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);

        realm = Realm.getDefaultInstance();

        // Button
        mAddRide = (Button) findViewById(R.id.addRide_button);

        // Texts
        mNewWhat = (TextView) findViewById(R.id.what_text);
        mNewWhere = (TextView) findViewById(R.id.where_text);



        //View products click event
        mAddRide.setOnClickListener(view -> {
            if ((mNewWhat.getText().length() > 0) && (mNewWhere.getText().length() > 0)) {
                mRide.setBikeName(mNewWhat.getText().toString().trim());
                mRide.setStartRide(mNewWhere.getText().toString().trim());

                realm.executeTransactionAsync(realm -> realm.insert(mRide));

                //PopUp to show success
                Toast toast = Toast.makeText(StartRideActivity.this, "Start ride added", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, -200);
                toast.show();

                // Reset text fields
                mNewWhat.setText("");
                mNewWhere.setText("");
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}