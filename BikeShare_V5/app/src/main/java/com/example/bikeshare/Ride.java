package com.example.bikeshare;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ride_table")
public class Ride {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @NonNull
    @ColumnInfo( name = "name")
    private String mBikeName ;
    private String mStartRide ;
    private String mEndRide;

    public Ride (@NonNull String bikeName, String startRide, String endRide) {
        mBikeName = bikeName;
        mStartRide = startRide;
        mEndRide = endRide;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getBikeName () {
        return mBikeName ;
    }

    public void setBikeName ( String bikeName ) {
        mBikeName = bikeName ;
    }

    public String getStartRide () {
        return mStartRide ;
    }

    public void setStartRide ( String startRide ) {
        mStartRide = startRide ;
    }

    public String toString () {
        return mBikeName + " " + mStartRide + " " + " " + mEndRide;
    }


    public void setEndRide(String endRide){
        mEndRide = endRide;
    }

    public String getEndRide(){
        return mEndRide;
    }
}
