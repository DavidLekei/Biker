package com.biker.api.LocationAPI;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class BikerLocationManager {

    private FusedLocationProviderClient fusedLocationProviderClient;

    public BikerLocationManager(Context context){
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

//    public LocationManager getLocation(){
//        return (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//    }

    public void getCurrentLocation(){
        
    }
}
