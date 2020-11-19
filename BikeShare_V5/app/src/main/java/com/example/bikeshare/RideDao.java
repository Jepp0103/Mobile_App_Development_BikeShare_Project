package com.example.bikeshare;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.*;

@Dao
public interface RideDao {
    @Insert
    void insert(Ride ride);

    @Query("UPDATE ride_table SET mEndRide=:endRide WHERE name =:bikeName")
//    @Update
    void update(String endRide, String bikeName);

    @Delete
    void delete(Ride ride);

    @Query("DELETE FROM ride_table")
    void deleteAll();

    @Query("SELECT * FROM ride_table " +
            "ORDER BY id")
    LiveData<List<Ride>> getAllRides();

    //  @Query("SELECT * FROM ride_table " +
    //        "WHERE name LIKE :bikeName LIMIT 1")
}
