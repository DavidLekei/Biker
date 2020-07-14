package com.biker.api.Callbacks;

import android.location.Location;
import android.widget.Toast;

import com.biker.ui.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationSuccessCallback implements OnSuccessListener<Location> {

    private SupportMapFragment mapFragment;

    public LocationSuccessCallback(SupportMapFragment mapFragment){
        super();
        this.mapFragment = mapFragment;
    }

    @Override
    public void onSuccess(final Location location) {
        if(location != null){
            mapFragment.getMapAsync(new MapReadyCallback(location));
        }
        else{
            //TODO: Display error
            System.out.println("Could not retrieve Current Location");
        }
    }
}
