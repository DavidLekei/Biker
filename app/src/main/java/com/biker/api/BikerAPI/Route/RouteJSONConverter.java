package com.biker.api.BikerAPI.Route;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

public class RouteJSONConverter {

    public RouteJSONConverter(){}

    public RouteStep jsonToRouteStep(JSONObject json) {
        return new RouteStep(getDistance(json),
                             getDuration(json),
                             getEndLocation(json),
                             getHTMLInstructions(json),
                             getPolyline(json),
                             getStartLocation(json),
                             getTravelMode(json));
    }

    private String getDistance(JSONObject json){
        return json.optJSONObject("distance").optString("text");
    }

    private String getDuration(JSONObject json){
        return json.optJSONObject("duration").optString("text");
    }

    private LatLng getEndLocation(JSONObject json) {
        JSONObject endLocation = json.optJSONObject("end_location");
        double lat = endLocation.optDouble("lat");
        double lng = endLocation.optDouble("lng");
        return new LatLng(lat, lng);
    }

    private String getHTMLInstructions(JSONObject json) {
        return json.optString("html_instructions");
    }

    private String getPolyline(JSONObject json) {
        return json.optJSONObject("polyline").optString("points");
    }

    private LatLng getStartLocation(JSONObject json) {
        JSONObject startLocation = json.optJSONObject("start_location");
        double lat = startLocation.optDouble("lat");
        double lng = startLocation.optDouble("lng");
        return new LatLng(lat, lng);
    }

    private String getTravelMode(JSONObject json) {
        return json.optString("travel_mode");
    }
}

