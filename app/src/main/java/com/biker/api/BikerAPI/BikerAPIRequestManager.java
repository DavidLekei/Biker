package com.biker.api.BikerAPI;

import android.location.Location;

import java.io.IOException;
import java.security.InvalidParameterException;

import com.biker.api.APIRequestManager;
import com.biker.api.LocationAPI.LocationJSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BikerAPIRequestManager extends APIRequestManager {

    private final String BIKER_API_URL = "http://10.0.2.2:5000/";
    private LocationJSONConverter converter;
    private String key; //TODO: Implement client verification using API_KEYS stored in Redis?

    public BikerAPIRequestManager(){
        converter = new LocationJSONConverter();
    }

    //locationID is provided by the Google Places API. It will be used by the Server to build a route.
    public JSONArray getBasicRoute(Location location) throws InvalidParameterException, NullPointerException, JSONException, IOException {

        JSONArray route;
        String requestParams;
        String latlng;

        //TODO: Implement custom Exception types.
        if(location == null){
            throw new NullPointerException();
        }


        latlng = converter.locationToParamString(location);
        requestParams = "getBasicRouteTest?" + latlng;

        route = sendBikerAPIRequest(requestParams);

        return route;
    }

    private JSONArray sendBikerAPIRequest(String requestParams) throws JSONException, IOException {
        return new JSONArray(super.sendAPIRequest(BIKER_API_URL, requestParams));
    }

}
