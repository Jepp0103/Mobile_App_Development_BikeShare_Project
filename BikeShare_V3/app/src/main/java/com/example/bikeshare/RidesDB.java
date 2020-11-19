package com.example.bikeshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RidesDB {
    private static RidesDB sRidesDB;
    private ArrayList<Ride> mAllRides;

    private RidesDB(Context context) {
        //Adding some sample Rides for testing purpose
        mAllRides = new ArrayList<>();

    }

    public static RidesDB get(Context context) {
        if (sRidesDB == null)
            sRidesDB = new RidesDB(context);
        return sRidesDB;
    }

    public List<Ride> getRidesDB() {
        return mAllRides;
    }

    public void addRide(String what, String where) {
        mAllRides.add(new Ride(what, where, ""));
    }

    public void endRide(String what, String where) {
        for (Ride r : mAllRides)
            if (r.getBikeName().equals(what)) {
                r.setEndRide(where);
            };
    }
}

