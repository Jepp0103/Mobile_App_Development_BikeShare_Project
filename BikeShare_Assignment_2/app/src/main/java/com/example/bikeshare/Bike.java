package com.example.bikeshare;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Bike extends RealmObject {

    @PrimaryKey
    private int mBikeId;
    private String mType;
    private int mPrice;
    private byte[] mImage;

    public Bike(int bikeId, String type, int price, byte[] image) {
        mBikeId = bikeId;
        mType = type;
        mPrice = price;
        mImage = image;
    }

    public Bike() {
    }

    @NonNull
    public int getBikeId() {
        return mBikeId;
    }

    public void setBikeId(int bikeId) {
        mBikeId = bikeId;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public String toString() {
        return mBikeId + " " + mType + " " + mPrice;
    }

    public byte[] getmImage() {
        return mImage;
    }

    public void setmImage(byte[] mImage) {
        this.mImage = mImage;
    }
}