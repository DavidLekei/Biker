package com.biker.api.BikerAPI;

import android.location.Location;

import com.biker.api.LocationAPI.BikerLocation;
import com.google.android.gms.maps.model.LatLng;

/*
This class is not responsible for drawing them to the map, it simply creates an Array of BikerLocation objects,
which define the route to be taken by the user on their ride.

 */

public class Route {

    private BikerLocation startingLocation;
    private BikerLocation[] locations;
    private RouteStep[] routeSteps;

    public Route(){}

    public Route(BikerLocation startingLocation){
        this.startingLocation = startingLocation;
    }

    public BikerLocation getStartingLocation(){
        return this.startingLocation;
    }

    public void setStartingLocation(BikerLocation startingLocation){
        this.startingLocation = startingLocation;
    }

    public void setStartingLocation(Location startingLocation){
        LatLng latlng = new LatLng(startingLocation.getLatitude(), startingLocation.getLongitude());
        this.startingLocation = new BikerLocation(latlng, "Current Location", "", null, "");
    }

    public BikerLocation[] getLocations(){return this.locations;}

    public void setLocations(BikerLocation[] locations){
        this.locations = locations;
    }

    public void setRouteSteps(RouteStep[] routeSteps) { this.routeSteps = routeSteps; }

    //TODO: Implement a better toString that returns more information about the Route, instead of just a single Location name.
    public String toString(){
        StringBuilder sBuilder = new StringBuilder("-------------Current Route-----------");
        for(int i = 0; i < this.routeSteps.length; i++){
            sBuilder.append('\n');
            sBuilder.append(routeSteps[i].toString());
        }

        return sBuilder.toString();
    }
}
