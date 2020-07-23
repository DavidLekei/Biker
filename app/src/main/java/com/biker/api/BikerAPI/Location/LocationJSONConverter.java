package com.biker.api.BikerAPI.Location;

import android.location.Location;

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
        JSONObject location = result.optJSONObject("location");
        double lat = location.optDouble("lat");
        double lng = location.optDouble("lng");

        return new LatLng(lat, lng);
    }

    private String getName(JSONObject result) throws JSONException {
        return result.optString("name");
    }

    private String getPlaceId(JSONObject result) throws JSONException{
        return result.optString("place_id");
    }

    private String[] getTypes(JSONObject result) throws JSONException{
        String[] types;
        JSONArray resultTypes = result.optJSONArray("types");
        types = new String[resultTypes.length()];

        for(int i = 0; i < resultTypes.length(); i++){
            types[i] = resultTypes.optString(i);
        }

        return types;
    }

    private String getAddress(JSONObject result) throws JSONException{
        return result.optString("address");
    }

    public String locationToParamString(Location location){
        return "latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude();
    }
}
