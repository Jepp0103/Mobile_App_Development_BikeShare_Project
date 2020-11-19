package dk.itu.nchh.bikeshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class BikeShareActivity extends Activity {


    // GUI variables
    private Button mAddRide;
    private Button mEndRide;
    private ListView mListView;
    private static RidesDB sRidesDB;
    private RideArrayAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);

        //SingleTon
        sRidesDB = RidesDB.get(this);
        List<Ride> values = sRidesDB.getRidesDB();

        //Adapter
        mAdapter = new RideArrayAdapter(this, values);
        mListView = (ListView) findViewById(R.id.main_list_view);
        mListView.setAdapter(mAdapter);


        //Button
        mAddRide = (Button) findViewById(R.id.add_button);
        mEndRide = (Button) findViewById(R.id.end_button);

        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BikeShareActivity.this, StartRideActivity.class);
                startActivity(intent);
            }
        });

        mEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BikeShareActivity.this, EndRideActivity.class);
                startActivity(intent);
            }
        });

    }

}

