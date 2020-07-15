package com.biker.api.LocationAPI;

import android.location.Location;
import android.location.LocationManager;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationJSONConverter {

    public LocationJSONConverter(){}

    public JSONObject locationToJSON(Location location) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("latitude", location.getLatitude());
        json.put("longitude", location.getLongitude());
        return json;
    }


    public Location jsonToLocation(JSONObject json) throws JSONException {
        double latitude = json.getDouble("latitude");
        double longitude = json.getDouble("longitude");

        Location location = new Location("Provider");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return location;
    }

    public String locationToParamString(Location location){
        return "latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude();
    }
}
