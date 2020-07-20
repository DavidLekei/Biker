package com.biker.api.BikerAPI;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class RouteJSONConverter {

    public RouteJSONConverter(){}

    public RouteStep jsonToRouteStep(JSONObject json) throws JSONException {
        return new RouteStep(getDistance(json),
                             getDuration(json),
                             getEndLocation(json),
                             getHTMLInstructions(json),
                             getPolyline(json),
                             getStartLocation(json),
                             getTravelMode(json));
    }

    private String getDistance(JSONObject json) throws JSONException {
        return json.getJSONObject("distance").getString("text");
    }

    private String getDuration(JSONObject json) throws JSONException {
        return json.getJSONObject("duration").getString("text");
    }

    private LatLng getEndLocation(JSONObject json) throws JSONException {
        JSONObject endLocation = json.getJSONObject("end_location");
        double lat = endLocation.getDouble("lat");
        double lng = endLocation.getDouble("lng");
        return new LatLng(lat, lng);
    }

    private String getHTMLInstructions(JSONObject json) throws JSONException {
        return json.getString("html_instructions");
    }

    private String getPolyline(JSONObject json) throws JSONException {
        return json.getJSONObject("polyline").getString("points");
    }

    private LatLng getStartLocation(JSONObject json) throws JSONException {
        JSONObject startLocation = json.getJSONObject("start_location");
        double lat = startLocation.getDouble("lat");
        double lng = startLocation.getDouble("lng");
        return new LatLng(lat, lng);
    }

    private String getTravelMode(JSONObject json) throws JSONException {
        return json.getString("travel_mode");
    }
}

