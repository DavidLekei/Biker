package com.biker.api.BikerAPI;

import android.location.Location;

import java.security.InvalidParameterException;

import com.biker.api.APIRequestManager;
import com.biker.api.LocationAPI.LocationJSONConverter;

import org.json.JSONException;
import org.json.JSONObject;

public class BikerAPIRequestManager extends APIRequestManager {

    private final String BIKER_API_URL = "http://10.0.2.2:5000/";
    private String key; //TODO: Implement client verification using API_KEYS stored in Redis?

    public BikerAPIRequestManager(){

    }

    //locationID is provided by the Google Places API. It will be used by the Server to build a route.
    public JSONObject getBasicRoute(Location location) throws InvalidParameterException, NullPointerException, JSONException {

        JSONObject route;
        String requestParams;
        String latlng;

        //TODO: Implement custom Exception types.
        if(location == null){
            throw new NullPointerException();
        }


        //route = new Route("TODO");
        latlng = new LocationJSONConverter().locationToParamString(location);
        requestParams = "getBasicRoute?" + latlng;

        route = sendBikerAPIRequest(requestParams);

        return route;
    }

    private JSONObject sendBikerAPIRequest(String requestParams) throws JSONException {
        return new JSONObject(super.sendAPIRequest(BIKER_API_URL, requestParams));
    }

}
