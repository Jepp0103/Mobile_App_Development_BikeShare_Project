package com.example.bikeshare;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.*;

public class RideRepository {
    private RideDao mRideDao;
    private LiveData<List<Ride>> mRides;

    public RideRepository(Application application) {
        RidesDB db = RidesDB.get(application);
        mRideDao = db.rideDao();
        mRides = mRideDao.getAllRides();
    }

    public void insert(Ride ride) {
        new insertAsyncTask(mRideDao).execute(ride);
    }

    public void update(Ride ride) {

        new updateAsyncTask(mRideDao).execute(ride);
    }


    public LiveData<List<Ride>> getAllRides() {
        return mRides;
    }

    private static class insertAsyncTask extends AsyncTask<Ride, Void, Void> {
        private RideDao mRideDao;

        public insertAsyncTask(RideDao rideDao) {
            mRideDao = rideDao;
        }

        @Override
        protected Void doInBackground(final Ride... params) {
            mRideDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Ride, Void, Void> {
        private RideDao mRideDao;

        public updateAsyncTask(RideDao rideDao) {
            mRideDao = rideDao;
        }

        @Override
        protected Void doInBackground(final Ride... params) {
            mRideDao.update(params[0].getEndRide(), params[0].getBikeName());
            return null;
        }
    }
}