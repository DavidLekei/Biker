package com.biker.api.Callbacks;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapReadyCallback implements OnMapReadyCallback {

    private Location location;

    public MapReadyCallback(Location location){
        this.location = location;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coord = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions marker = new MarkerOptions().position(coord).title("You Are Here");

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 10));
        googleMap.addMarker(marker);
    }
}
