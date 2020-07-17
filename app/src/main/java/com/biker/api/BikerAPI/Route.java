package com.biker.api.BikerAPI;

import org.json.JSONArray;

import java.util.Dictionary;
import java.util.List;

public class Route {

    private String startingLocation;
    private JSONArray results;

    public Route(){}

    public Route(String startingLocation){
        this.startingLocation = startingLocation;
    }

    public String getStartingLocation(){
        return this.startingLocation;
    }
    public JSONArray getResults(){return this.results;}

    public void setStartingLocation(String startingLocation){
        this.startingLocation = startingLocation;
    }

    public void setResults(JSONArray results){
        this.results = results;
    }
}
