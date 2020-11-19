package com.example.bikeshare.Fragments;

import android.content.Intent;
import android.support.v4.app.*;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import com.example.bikeshare.Activities.DeleteRideActivity;
import com.example.bikeshare.Activities.EndRideActivity;
import com.example.bikeshare.Activities.StartRideActivity;
import com.example.bikeshare.Activities.UpdateRideActivity;
import com.example.bikeshare.R;
import com.example.bikeshare.Ride;
import com.example.bikeshare.RideArrayAdapter;


import java.util.*;

import io.realm.Realm;

public class BikeShareFragment extends Fragment {
    // GUI variables
    private Button mAddRide;
    private Button mEndRide;
    private Button mDeleteRide;
    private Button mUpdateRide;
    private Button mShowRides;
    private Button mHideRides;

    private Realm realm;
    private List<Ride> rides;

    RideArrayAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();

        View view = inflater.inflate(R.layout.fragment_bike_share, container, false);

        rides = realm.where(Ride.class).findAll();

        //Adapter
        mAdapter = new RideArrayAdapter(rides);

        //Recycler View
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Button
        mAddRide = (Button) view.findViewById(R.id.add_button);
        mEndRide = (Button) view.findViewById(R.id.end_button);
        mUpdateRide = (Button) view.findViewById(R.id.update_button);
        mDeleteRide = (Button) view.findViewById(R.id.delete_button);
        mShowRides = (Button) view.findViewById(R.id.show_button);
        mHideRides = (Button) view.findViewById(R.id.hide_button);


        //View products click event
        mAddRide.setOnClickListener(view13 -> {
            Intent intent = new Intent(getActivity(), StartRideActivity.class);
            startActivity(intent);
        });

        mEndRide.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), EndRideActivity.class);
            startActivity(intent);
        });

        mDeleteRide.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), DeleteRideActivity.class);
            startActivity(intent);
        });

        mUpdateRide.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), UpdateRideActivity.class);
            startActivity(intent);
        });

        mShowRides.setOnClickListener(view14 -> {
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
        });

        mHideRides.setOnClickListener(view14 -> mRecyclerView.setVisibility(View.GONE));
        return view;
    }
}

