package com.biker.api.BikerAPI;

public class Route {

    private String startingLocation;

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
}
