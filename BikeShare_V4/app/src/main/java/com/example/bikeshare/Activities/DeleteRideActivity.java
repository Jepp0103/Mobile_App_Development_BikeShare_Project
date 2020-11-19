package com.example.bikeshare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.R;
import com.example.bikeshare.RidesDB;

public class DeleteRideActivity extends Activity {

    private Button mDelete;
    private TextView mBikeToDelete;
    private static RidesDB sRidesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ride);
        sRidesDB = RidesDB.get(this);


        // Button
        mDelete = (Button) findViewById(R.id.delete_button);

        // Texts
        mBikeToDelete = (TextView) findViewById(R.id.what_text_delete);
        mBikeToDelete.setText("");


        //View products click event
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bikeName = mBikeToDelete.getText().toString().trim();

                sRidesDB.removeRide(bikeName);

                // Reset text fields
                mBikeToDelete.setText("");
            }
        });
    }
}
