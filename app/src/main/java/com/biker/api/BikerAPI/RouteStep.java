package com.biker.api.BikerAPI;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class RouteStep {

    private String distance;
    private String duration;
    private LatLng endLocation;
    private String htmlInstructions;
    private String polyline;
    private LatLng startLocation;
    private String travelMode;

    public RouteStep(String distance,
                     String duration,
                     LatLng endLocation,
                     String htmlInstructions,
                     String polyline,
                     LatLng startLocation,
                     String travelMode){
        this.distance = distance;
        this.duration = duration;
        this.endLocation = endLocation;
        this.htmlInstructions = htmlInstructions;
        this.polyline = polyline;
        this.startLocation = startLocation;
        this.travelMode = travelMode;
    }

    public String getDistance() throws JSONException {
        return this.distance;
    }

    public String getDuration() throws JSONException {
        return this.duration;
    }

    public LatLng getEndLocation() throws JSONException {
        return this.endLocation;
    }

    public String getHtmlInstructions() throws JSONException {
        return this.htmlInstructions;
    }

    public String getPolyline() throws JSONException {
        return this.polyline;
    }

    public LatLng getStartingLocation() throws JSONException {
        return this.startLocation;
    }

    public String getTravelMode(){
        return this.travelMode;
    }

}
