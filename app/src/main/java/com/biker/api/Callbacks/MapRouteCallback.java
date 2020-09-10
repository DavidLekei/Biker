package com.biker.api.Callbacks;

import com.biker.api.BikerAPI.Route.Route;
import com.biker.api.BikerAPI.Location.BikerLocation;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapRouteCallback implements OnMapReadyCallback {

    private Route route;
    private final float DEFAULT_ZOOM = 15.0f;
    private final float CITY_LEVEL_ZOOM = 10.0f;
    private final float STREET_LEVEL_ZOOM = 15.0f;
    private final float BUILDING_LEVEL_ZOOM = 20.0f;

    public MapRouteCallback(Route route){
        this.route = route;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        System.out.println("onMapReady(): Map is Ready.");
        BikerLocation startingLocation = this.route.getStartingLocation();
        BikerLocation[] locations = this.route.getLocations();

        zoomToStart(googleMap, startingLocation);


        for(int i = 0; i < locations.length; i++){
            drawMarker(googleMap, locations[i]);
        }

        for( BikerLocation location: locations)
        {
            drawLine(googleMap, startingLocation, location);
            startingLocation = location;
        }
    }

    private void drawMarker(GoogleMap googleMap, BikerLocation location){
        LatLng coord = location.getLatLng();
        MarkerOptions marker = new MarkerOptions().position(coord).title(location.getName());

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 10));
        googleMap.addMarker(marker);
    }

    private void drawLine(GoogleMap googleMap, BikerLocation start, BikerLocation end)
    {
        LatLng startCoord = start.getLatLng();
        LatLng endCoord = end.getLatLng();

        googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(startCoord, endCoord));
    }

    private void zoomToStart(GoogleMap googleMap, BikerLocation startingLocation)
    {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startingLocation.getLatLng(), DEFAULT_ZOOM));
        googleMap.animateCamera(CameraUpdateFactory.zoomBy(DEFAULT_ZOOM));
    }
}
