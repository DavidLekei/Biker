package com.biker;

import android.location.Location;

public class CurrentLocation {

    private static Location currentLocation;
    private static boolean isSet;
    public CurrentLocation(){
        isSet = false;
        currentLocation = null;
    }

    public static void setCurrentLocation(Location location){
        currentLocation = location;
        isSet = true;
    }
}
