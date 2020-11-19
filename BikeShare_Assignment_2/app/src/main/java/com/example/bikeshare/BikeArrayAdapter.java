package com.example.bikeshare;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import java.util.*;

public class BikeArrayAdapter extends RecyclerView.Adapter<BikeHolder>{
    private List<Bike> mBikes;

    public BikeArrayAdapter(List<Bike> bikes) {
        mBikes = bikes;
    }

    @Override
    public BikeHolder onCreateViewHolder(ViewGroup p, int v) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(p.getContext());
        return new BikeHolder(layoutInflater, p);
    }

    @Override
    public void onBindViewHolder(BikeHolder holder, int i) {
        Bike bike = mBikes.get(i);
        holder.bind(bike);
    }

    public void setBikes(List<Bike> bikes){
        mBikes = bikes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() { return mBikes.size();
    }
}
