package com.example.bikeshare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class RideHolder extends RecyclerView.ViewHolder {
    private TextView mBikeNameView;
    private TextView mStartView;
    private TextView mEndView;

    public RideHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.list_item_ride, parent, false));
        mBikeNameView = itemView.findViewById(R.id.what_bike_ride);
        mStartView = itemView.findViewById(R.id.start_ride);
        mEndView = itemView.findViewById(R.id.end_ride);
    }
    public void bind(Ride ride) {
        mBikeNameView.setText(ride.getBikeName());
        mStartView.setText(ride.getStartRide());
        mEndView.setText(ride.getEndRide());
    }
}
