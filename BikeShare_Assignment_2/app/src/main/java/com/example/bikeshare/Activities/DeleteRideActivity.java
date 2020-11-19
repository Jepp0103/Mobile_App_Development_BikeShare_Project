package com.example.bikeshare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.BikeApplication;
import com.example.bikeshare.R;
import com.example.bikeshare.Ride;

import io.realm.Realm;
import io.realm.RealmResults;

public class DeleteRideActivity extends Activity {

    private Button mDelete;
    private TextView mBikeToDelete;
    private Realm rideRealm;
    private Ride mRide = new Ride("", "", "");
    BikeApplication bApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ride);
        rideRealm = Realm.getDefaultInstance();


        // Button
        mDelete = (Button) findViewById(R.id.delete_button);

        // Texts
        mBikeToDelete = (TextView) findViewById(R.id.what_text_delete);
        mBikeToDelete.setText("");


        //View products click event
        mDelete.setOnClickListener(view -> {
          String bikeName  = mBikeToDelete.getText().toString().trim();
          mRide = rideRealm.where(Ride.class).equalTo("mBikeName", bikeName).findFirst();

          if(mRide != null) {
              delete_from_database(bikeName);
              //PopUp to show success
              Toast toast = Toast.makeText(DeleteRideActivity.this, "Ride deleted", Toast.LENGTH_SHORT);
              toast.setGravity(Gravity.CENTER, 0, -200);
              toast.show();
          } else {
              Toast toastWrong = Toast.makeText(DeleteRideActivity.this, "Ride not found", Toast.LENGTH_SHORT);
              toastWrong.setGravity(Gravity.CENTER, 0, -200);
              toastWrong.show();
          }

            // Reset text fields
            mBikeToDelete.setText("");
        });
    }

    private void delete_from_database(String bikeName){
        try {
            final RealmResults<Ride> rides = rideRealm.where(Ride.class).equalTo("mBikeName", bikeName).findAll();
            rideRealm.executeTransaction(realm -> rides.deleteFromRealm(0));
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("Bike does not exist", e.toString());
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        rideRealm.close();
    }
}
