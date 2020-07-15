package com.biker.api.BikerAPI;

import java.security.InvalidParameterException;

import com.biker.api.APIRequestManager;

public class BikerAPIRequestManager extends APIRequestManager {

    private final String BIKER_API_URL = "http://10.0.2.2:5000/";
    private String key; //TODO: Implement client verification using API_KEYS stored in Redis?

    public BikerAPIRequestManager(){

    }

    //locationID is provided by the Google Places API. It will be used by the Server to build a route.
    public Route getBasicRoute(String latlng) throws InvalidParameterException, NullPointerException{

        Route route;
        String requestParams;

        //TODO: Implement custom Exception types.
        if(latlng == null){
            throw new NullPointerException();
        }
        if(latlng == ""){
            throw new InvalidParameterException();
        }


        route = new Route("TODO");
        requestParams = "getBasicRoute?" + latlng;

        sendBikerAPIRequest(requestParams);

        return route;
    }

    private void sendBikerAPIRequest(String requestParams){
        super.sendAPIRequest(BIKER_API_URL, requestParams);
    }

}
