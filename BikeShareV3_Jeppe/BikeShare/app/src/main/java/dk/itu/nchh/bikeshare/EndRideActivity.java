package dk.itu.nchh.bikeshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndRideActivity extends AppCompatActivity {

    private Button mAddRide;
    private TextView mLastAdded;
    private TextView mNewWhat;
    private TextView mNewWhere;
    private Ride mLast = new Ride("", "", "");
    private String bikeName;
    private String bikeStart;
    private static RidesDB shareRides;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_ride);
        shareRides = RidesDB.get(this);

        mLastAdded  = findViewById(R.id.last_ride);
        updateUI();
        mLastAdded.setText("");

        //Button
        mAddRide = findViewById(R.id.add_button);

        //Text
        bikeName = getIntent().getStringExtra("bikeName");
        mNewWhat = findViewById(R.id.what_text);
        mNewWhat.setText(bikeName);

        bikeStart = getIntent().getStringExtra("bikeStart");
        mNewWhere = findViewById(R.id.where_text);
        mNewWhere.setText(bikeStart);

        // View products click event
        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((mNewWhat.getText().length() > 0) &&
                        (mNewWhere.getText().length() > 0)) {
                    mLast.setBikeName(mNewWhat.getText().toString().trim());
                    mLast.setStartRide(mNewWhere.getText().toString().trim());

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
