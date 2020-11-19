package com.example.bikeshare;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Database(entities = {Ride.class}, version = 1)
public abstract class RidesDB extends RoomDatabase {
    public abstract RideDao rideDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile RidesDB sRidesDB;
    private static ArrayList<Ride> mAllRides;

    public RidesDB() {
        mAllRides = new ArrayList<>();
    }

    public static RidesDB get(final Context context) {
        if (sRidesDB == null)
            synchronized (RidesDB.class) {
                if (sRidesDB == null)
                    sRidesDB =
                            Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    RidesDB.class, "ride_database")
                                    .fallbackToDestructiveMigration()
                                    .addCallback(sRoomDatabaseCallback)
                                    .build();
            }
        return sRidesDB;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(sRidesDB).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RideDao rideDao;

        PopulateDbAsync(RidesDB db) {
            rideDao = db.rideDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            rideDao.deleteAll();

            Ride ride = new Ride("Jeppes bike", "CPH", "Odense");
            rideDao.insert(ride);
            ride = new Ride("Nicolais bike", "Ålborg", "Århus");
            rideDao.insert(ride);
            return null;
        }
    }

    public List<Ride> getRidesDB() {
        return mAllRides;
    }

    public void addRide(String what, String where) {
        SimpleDateFormat df = new SimpleDateFormat();
        String date = df.format(Calendar.getInstance().getTime());
        mAllRides.add(new Ride(what, where + "\nStart date " + date, ""));
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


