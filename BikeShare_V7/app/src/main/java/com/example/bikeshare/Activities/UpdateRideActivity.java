package com.example.bikeshare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.R;
import com.example.bikeshare.Ride;

import io.realm.Realm;
import io.realm.RealmResults;

public class UpdateRideActivity extends Activity {

    private Button mUpdate;
    private TextView mBikeToUpdate;
    private Realm realm;
    private Ride mRide = new Ride("", "", "");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ride);
        realm = Realm.getDefaultInstance();


        // Button
        mUpdate = (Button) findViewById(R.id.update_button);

        // Texts
        mBikeToUpdate = (TextView) findViewById(R.id.what_text_update);
        mBikeToUpdate.setText("");


        //View products click event
        mUpdate.setOnClickListener(view -> {
            String bikeName  = mBikeToUpdate.getText().toString().trim();
            delete_from_database(bikeName);

            //PopUp to show success
            Toast toast = Toast.makeText(UpdateRideActivity.this, "Ride updated", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -200);
            toast.show();

            // Reset text fields
            mBikeToUpdate.setText("");
        });
    }

    private void delete_from_database(String bikeName){
        try {
            final RealmResults<Ride> rides = realm.where(Ride.class).equalTo("mBikeName", bikeName).findAll();
            realm.executeTransaction(realm -> rides.deleteFromRealm(0));
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("Bike does not exist", e.toString());
        }
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}
