package com.biker.api.Callbacks;

import com.biker.api.BikerAPI.Route.Route;
import com.biker.api.BikerAPI.Location.BikerLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapRouteCallback implements OnMapReadyCallback {

    private Route route;

    public MapRouteCallback(Route route){
        this.route = route;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        BikerLocation startingLocation = this.route.getStartingLocation();
        BikerLocation[] locations = this.route.getLocations();

        for(int i = 0; i < locations.length; i++){
            drawMarker(googleMap, locations[i]);
        }
    }

    private void drawMarker(GoogleMap googleMap, BikerLocation location){
        LatLng coord = location.getLatLng();
        MarkerOptions marker = new MarkerOptions().position(coord).title(location.getName());

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 10));
        googleMap.addMarker(marker);
    }
}
