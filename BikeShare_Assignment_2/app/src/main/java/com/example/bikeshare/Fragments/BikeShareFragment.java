package com.example.bikeshare.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.*;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

//import com.example.bikeshare.Activities.BikeActivity;
import com.example.bikeshare.Activities.BikeActivity;
import com.example.bikeshare.Activities.DeleteRideActivity;
import com.example.bikeshare.Activities.EndRideActivity;
import com.example.bikeshare.Activities.StartRideActivity;
import com.example.bikeshare.Activities.UpdateRideActivity;
import com.example.bikeshare.Bike;
import com.example.bikeshare.BikeApplication;
import com.example.bikeshare.BikeArrayAdapter;
import com.example.bikeshare.R;
import com.example.bikeshare.Ride;
import com.example.bikeshare.RideArrayAdapter;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;

import java.util.*;

import io.realm.Realm;

public class BikeShareFragment extends Fragment {
    // GUI variables
    private Button mRegisterBike;
    private Button mAddRide;
    private Button mEndRide;
    private Button mDeleteRide;
    private Button mUpdateRide;
    private Button mShowRides;
    private Button mShowBikes;
    private Button mHideObjects;
    private Button mCameraButton;
    private Button mCamera;
    private Realm realm;
    private Realm realmTwo;
    private List<Ride> rides;
    private CameraBridgeViewBase mOpenCvCameraView;
    private Mat mat;
    private int cont;
    private int index;
    private List<Bike> bikes;
    private RideArrayAdapter mAdapter;
    private BikeArrayAdapter bAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView bRecyclerView;
    BikeApplication bApp;
    private ArrayList<String> mPermissions = new ArrayList<>();
    private static final int ALL_PERMISSIONS_RESULT = 1011;

    public BikeShareFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Realm
        realm = Realm.getDefaultInstance();

        View view = inflater.inflate(R.layout.fragment_bike_share, container, false);

        rides = realm.where(Ride.class).findAll();
        bikes = realm.where(Bike.class).findAll();

        //Adapter
        mAdapter = new RideArrayAdapter(rides);
        bAdapter = new BikeArrayAdapter(bikes);

        //Recycler Views
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bRecyclerView = (RecyclerView) view.findViewById(R.id.bike_recycler_view);
        bRecyclerView.setAdapter(bAdapter);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Button
        mRegisterBike = (Button) view.findViewById(R.id.registerBike_button);
        mAddRide = (Button) view.findViewById(R.id.add_button);
        mEndRide = (Button) view.findViewById(R.id.end_button);
        mUpdateRide = (Button) view.findViewById(R.id.update_button);
        mDeleteRide = (Button) view.findViewById(R.id.delete_button);
        mShowRides = (Button) view.findViewById(R.id.show_button);
        mShowBikes = (Button) view.findViewById(R.id.show_bikes);
        mHideObjects = (Button) view.findViewById(R.id.hide_button);

        //GPS
        mPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        mPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        mPermissions.add(Manifest.permission.READ_CONTACTS);

        ArrayList<String> mPermissionsToRequest = permissionsToRequest(mPermissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if
            (mPermissionsToRequest.size() >
                    0) {
                requestPermissions(mPermissionsToRequest.toArray(
                        new String[mPermissionsToRequest.size()]),
                        ALL_PERMISSIONS_RESULT);
            }
        }

        //View products click event
        mRegisterBike.setOnClickListener(view10 -> {
            Intent intent = new Intent(getActivity(), BikeActivity.class);
            startActivity(intent);
        });

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

        mShowBikes.setOnClickListener(view14 -> {
            bAdapter.notifyDataSetChanged();
            bRecyclerView.setVisibility(View.VISIBLE);
        });

        mHideObjects.setOnClickListener(view14 -> {
            mRecyclerView.setVisibility(View.GONE);
            bRecyclerView.setVisibility(View.GONE);
        });

        return view;
    }

    private ArrayList<String> permissionsToRequest(ArrayList<String> permissions) {
        ArrayList<String> result = new ArrayList<>();
        for (String permission : permissions)
            if (!hasPermission(permission))
                result.add(permission);
        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return Objects.requireNonNull(getActivity())
                    .checkSelfPermission(permission) ==
                    PackageManager.PERMISSION_GRANTED;
        return true;
    }

}

