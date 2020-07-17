package com.biker.api.BikerAPI;

import com.biker.api.LocationAPI.BikerLocation;

/*
This class is not responsible for drawing them to the map, it simply creates an Array of BikerLocation objects,
which define the route to be taken by the user on their ride.

 */

public class Route {

    private String startingLocation;
    private BikerLocation[] locations;

    public Route(){}

    public Route(String startingLocation){
        this.startingLocation = startingLocation;
    }

    public String getStartingLocation(){
        return this.startingLocation;
    }

    public void setStartingLocation(String startingLocation){
        this.startingLocation = startingLocation;
    }

    public BikerLocation[] getLocations(){return this.locations;}

    public void setLocations(BikerLocation[] locations){
        this.locations = locations;
    }

    public String toString(){
        String location = this.locations[0].getName();
        return location;
    }
}
