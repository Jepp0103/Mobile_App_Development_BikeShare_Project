package com.example.bikeshare;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SimpleTimeZone;

public class RidesDB {
    private static RidesDB sRidesDB;
    private ArrayList<Ride> mAllRides;

    private RidesDB(Context context) {
        //Adding some sample Rides for testing purpose
        mAllRides = new ArrayList<>();
      /*  for (int i = 0; i < 50; i++) {
            mAllRides.add(new Ride("BikeName #" + i, "StartLocation # " + i, "Ending Ride # " + i));
        }*/
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
        SimpleDateFormat df = new SimpleDateFormat();
        String date = df.format(Calendar.getInstance().getTime());
        mAllRides.add(new Ride(what, where + "\nStart date " + date,""));
    }

    public void endRide(String what, String where) {
        SimpleDateFormat df = new SimpleDateFormat();
        String date = df.format(Calendar.getInstance().getTime());
        for (Ride r : mAllRides)
            if (r.getBikeName().equals(what)) {
                r.setEndRide(where + "\nEnd date " + date);
            }


    }

    public void removeRide(String what) {
        for (int i = 0; i < mAllRides.size(); i++)
            if (what.equals(mAllRides.get(i).getBikeName()))
                mAllRides.remove(i);
    }
}


