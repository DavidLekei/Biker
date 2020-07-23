package com.biker.api.BikerAPI;

import android.location.Location;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidParameterException;
import java.util.Scanner;

import com.biker.api.BikerAPI.Location.LocationJSONConverter;

import org.json.JSONArray;
import org.json.JSONException;

public class BikerAPIManager {

    private final String BIKER_API_URL_EMULATOR = "http://10.0.2.2:5000/";
    private final String BIKER_API_URL_REAL_DEVICE = "http://localhost:5000/";
    private LocationJSONConverter converter;
    private String key; //TODO: Implement client verification using API_KEYS stored in Redis?

    public BikerAPIManager(){
        converter = new LocationJSONConverter();
    }

    public JSONArray getRouteLocations(Location location){
        JSONArray locations = null;
        String params = converter.locationToParamString(location);
        String endpoint = "getNearbyPlaces?" + params;

        try {
            locations = sendBikerAPIRequest(endpoint);
        }
        catch(IOException | JSONException e){

        }
        return locations;
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
        return new JSONArray(sendAPIRequest(BIKER_API_URL_REAL_DEVICE, requestParams));
    }

    private String sendAPIRequest(String API_URL, String requestParams){
        Scanner scanner;

        try{
            InputStream urlInputStream = getConnectionStream(API_URL + requestParams);
            scanner = new Scanner(urlInputStream, "UTF-8");
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println("Response Length: " + responseBody.length());
            return responseBody;
        }
        catch(MalformedURLException m){
            m.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        return null;
    }

    private InputStream getConnectionStream(String requestDest) throws IOException{
        URLConnection connection = new URL(requestDest).openConnection();
        System.out.println("Content Length: " + connection.getContentLength());
        System.out.println("Content Encoding: " + connection.getContentEncoding());
        return connection.getInputStream();
    }

}
