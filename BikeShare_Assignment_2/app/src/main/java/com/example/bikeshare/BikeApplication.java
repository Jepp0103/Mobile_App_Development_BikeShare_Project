package com.example.bikeshare;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BikeApplication extends Application {

    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("test").build();
        Realm.setDefaultConfiguration(configuration);
    }
}


