package com.example.bikeshare;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BikeHolder extends RecyclerView.ViewHolder {
    private TextView mBikeIdView;
    private TextView mTypeView;
    private TextView mPriceView;
    private ImageView mImageView;

    public BikeHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.list_bikes, parent, false));
        mBikeIdView = itemView.findViewById(R.id.bike_id);
        mTypeView = itemView.findViewById(R.id.type);
        mPriceView = itemView.findViewById(R.id.price);
        mImageView = itemView.findViewById(R.id.show_photo);
    }

    public void bind(Bike bike) {
        mBikeIdView.setText(Integer.toString(bike.getBikeId()));
        mTypeView.setText(bike.getType());
        mPriceView.setText(Integer.toString(bike.getPrice()));
        mImageView.setImageBitmap(BitmapFactory.decodeByteArray(bike.getmImage(), 0, bike.getmImage().length));
    }
}
