package dk.itu.nchh.bikeshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RidesDB {
    private static RidesDB sRidesDB;
    private ArrayList<Ride> mAllRides;
    private Ride mLastRide;

    private RidesDB(Context context) {
        mLastRide = new Ride("","","");

        //Adding some sample Rides for testing purpose
        mAllRides = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            mAllRides.add(new Ride("Hansen_Bike: " + " #" + i, "Start + #" + i, "End" + i ));
        }
       /* mAllRides.add(new Ride("Chuck Norris Bike", "ITU", "Fields"));
        mAllRides.add(new Ride("Chuck Norris Bike", "FIELDS", "Kongens Nytorv"));
        mAllRides.add(new Ride("Bruise LEee", "Købemhavns Station", "Købemhavns Lufthavn"));*/
    }

    public static RidesDB get(Context context) {
        if(sRidesDB == null) sRidesDB = new RidesDB(context);
        return sRidesDB;
    }

    public List<Ride> getRidesDB() {
        return mAllRides;
    }

    public void addRide(String what, String where) {
        mAllRides.add(new Ride(what, where, ""));
    }

    public void endRide(String what, String where) {
        for(Ride r : mAllRides) {
            if(r.getBikeName().equals(what))
                r.setEndRide(where);
        }
    }
}
