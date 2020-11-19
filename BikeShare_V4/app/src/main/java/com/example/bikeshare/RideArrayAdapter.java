package com.example.bikeshare;


import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.*;

public class RideArrayAdapter extends ArrayAdapter<Ride> {
    private final Context mContext;
    private final List<Ride> mValues;

    public RideArrayAdapter(Context context, List<Ride> values) {
        super(context, R.layout.list_item_ride, values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(int position, View concertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_ride, parent, false);

        TextView bikeView = (TextView) rowView.findViewById(R.id.what_bike_ride);
        bikeView.append(mValues.get(position).getBikeName());

        TextView startView = (TextView) rowView.findViewById(R.id.start_ride);
        startView.append(mValues.get(position).getStartRide());

        TextView endView = (TextView) rowView.findViewById(R.id.end_ride);
        endView.append(mValues.get(position).getEndRide());

        return rowView;
    }
}