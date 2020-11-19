package dk.itu.nchh.bikeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartRideActivity extends AppCompatActivity {

    // GUI variables
    private Button mAddRide;
    private TextView mLastAdded;
    private TextView mNewWhat;
    private TextView mNewWhere;
    private Ride mLast = new Ride("", "", "");
    private static RidesDB shareRides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);
        shareRides = RidesDB.get(this);


        mLastAdded  = findViewById(R.id.last_ride);
        updateUI();
        mLastAdded.setText("");

        //Button
        mAddRide = findViewById(R.id.add_button);

        //Text

        mNewWhat = findViewById(R.id.what_text);
        mNewWhere = findViewById(R.id.where_text);

        // View products click event
        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((mNewWhat.getText().length() > 0) &&
                        (mNewWhere.getText().length() > 0)) {
                    mLast.setBikeName(mNewWhat.getText().toString().trim());
                    mLast.setStartRide(mNewWhere.getText().toString().trim());

                    Intent intent = new Intent(StartRideActivity.this, EndRideActivity.class);
                    intent.putExtra("bikeName",mNewWhat.getText());
                    intent.putExtra("bikeStart", mNewWhere.getText());
                    startActivity(intent);

                    //resetting the fields
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