package com.example.bikeshare;

import android.app.Application;
import android.arch.lifecycle.*;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.*;


public class RideVM extends AndroidViewModel  {
    private RideRepository mRideRepository;
    private LiveData<List<Ride>> mRides;

    public RideVM(Application application) {
        super(application);
        mRideRepository = new RideRepository(application);
        mRides = mRideRepository.getAllRides();
    }

    public void update(Ride ride) {mRideRepository.update(ride);}

    public void insert(Ride ride) {mRideRepository.insert(ride);}

    public LiveData<List<Ride>> getRides() {
        return mRides;
    }
}