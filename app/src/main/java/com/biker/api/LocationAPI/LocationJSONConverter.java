package com.biker.api.LocationAPI;

import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
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


    public BikerLocation jsonToLocation(JSONObject result) throws JSONException {
        LatLng latlng = getLatLng(result);
        String name = getName(result);
        String placeId = getPlaceId(result);
        String[] types = getTypes(result);
        String address = getAddress(result);

        return new BikerLocation(latlng, name, placeId, types, address);
    }

    private LatLng getLatLng(JSONObject result) throws JSONException {
        JSONObject location = result.getJSONObject("geometry").getJSONObject("location");
        double lat = location.getDouble("lat");
        double lng = location.getDouble("lng");

        return new LatLng(lat, lng);
    }

    private String getName(JSONObject result) throws JSONException {
        return result.getString("name");
    }

    private String getPlaceId(JSONObject result) throws JSONException{
        return result.getString("place_id");
    }

    private String[] getTypes(JSONObject result) throws JSONException{
        String[] types;
        JSONArray resultTypes = result.getJSONArray("types");
        types = new String[resultTypes.length()];

        for(int i = 0; i < resultTypes.length(); i++){
            types[i] = resultTypes.getString(i);
        }

        return types;
    }

    private String getAddress(JSONObject result) throws JSONException{
        return result.getString("vicinity");
    }

    public String locationToParamString(Location location){
        return "latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude();
    }
}
