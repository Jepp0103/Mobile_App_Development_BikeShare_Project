package com.example.bikeshare.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;
import com.example.bikeshare.Activities.DeleteRideActivity;
import com.example.bikeshare.Activities.EndRideActivity;
import com.example.bikeshare.Activities.StartRideActivity;
import com.example.bikeshare.RideVM;
import com.example.bikeshare.R;
import com.example.bikeshare.Ride;
import com.example.bikeshare.RideArrayAdapter;
import com.example.bikeshare.RidesDB;

import java.util.*;

public class BikeShareFragment extends Fragment {
    // GUI variables
    private Button mAddRide;
    private Button mEndRide;
    private Button mDeleteRide;
    private Button mShowRides;
    private Button mInsertbutton;
    private TextView mInsertBikeName;
    private RideVM mRideVM;

    private static RidesDB sRidesDB;
    RideArrayAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bike_share, container, false);

        // Singleton
         sRidesDB = RidesDB.get(getActivity());
         final List<Ride> rides = sRidesDB.getRidesDB();


        //Adapter
        mAdapter = new RideArrayAdapter(rides);

        //Recycler View
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //View model
        mRideVM = ViewModelProviders.of(this).get(RideVM.class);

        mRideVM.getRides().observe(this, new Observer<List<Ride>>() {
            @Override
            public void onChanged(@Nullable List<Ride> rides) {
                mAdapter.setRides(rides);
            }
        });
        mRideVM.insert(new Ride("bike", "CPH", "Odense"));


        // Button
        mAddRide = (Button) view.findViewById(R.id.add_button);
        mEndRide = (Button) view.findViewById(R.id.end_button);
        mDeleteRide = (Button) view.findViewById(R.id.delete_button);
        mShowRides = (Button) view.findViewById(R.id.show_button);
//      mInsertbutton = (Button) view.findViewById(R.id.insert_button);

        //View products click event
        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StartRideActivity.class);
                startActivity(intent);
            }
        });

        mEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EndRideActivity.class);
                startActivity(intent);
            }
        });

        mDeleteRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DeleteRideActivity.class);
                startActivity(intent);
            }
        });

        mShowRides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mRecyclerView.setAdapter(mAdapter);
            }
        });

     /*   mInsertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        String text = mInsertBikeName.getText().toString();
                if (!text.isEmpty()) {
                    mRideVM.insert(new Ride(text).toString());
                    mEditText.setText("");
                }
            }
        });*/

        return view;
    }
}

