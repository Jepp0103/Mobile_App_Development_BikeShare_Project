package com.example.bikeshare;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import java.util.*;

public class RideArrayAdapter extends RecyclerView.Adapter<RideHolder>{
    private List<Ride> mRides;

    public RideArrayAdapter(List<Ride> rides) {
        mRides = rides;
    }

    @Override
    public RideHolder onCreateViewHolder(ViewGroup p, int v) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(p.getContext());
        return new RideHolder(layoutInflater, p);
    }

    @Override
    public void onBindViewHolder(RideHolder holder, int i) {
        Ride ride = mRides.get(i);
        holder.bind(ride);
    }


    public void setRides(List<Ride> rides){
        mRides = rides;
        notifyDataSetChanged();
}


    @Override
    public int getItemCount() {
        return mRides.size();
    }
}
