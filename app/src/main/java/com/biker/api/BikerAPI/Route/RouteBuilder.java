package com.biker.api.BikerAPI.Route;

import android.location.Location;

import com.biker.api.BikerAPI.BikerAPIManager;
import com.biker.api.BikerAPI.Location.BikerLocation;
import com.biker.api.BikerAPI.Location.LocationJSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RouteBuilder {

    private BikerAPIManager bikerAPI;

    public RouteBuilder(){
        this.bikerAPI = new BikerAPIManager();
    }

    public Route buildRoute(Location location) throws JSONException, IOException {
        Route route = new Route();

        BikerLocation[] locations = extractLocations(bikerAPI.getRouteLocations(location));

        //route.setStartingLocation(jsonRoute.getString("startingLocation"));
        //route.setLocations(extractResults(jsonRoute));
        //route.setRouteSteps(extractSteps(jsonRoute));

        route.setStartingLocation(location);
        route.setLocations(locations);

        return route;
    }

    //TODO: Implement
    private BikerLocation[] extractLocations(JSONArray jsonLocations) throws JSONException {
        LocationJSONConverter converter = new LocationJSONConverter();
        BikerLocation[] locations = new BikerLocation[jsonLocations.length()];

        //jsonRoute is an Array of Arrays. Each Array in jsonRoute is an Array of JSONObjects. Each JSONObject refers to a
        //specific step. So since we only
        for(int i = 0; i < jsonLocations.length(); i++){
            locations[i] = converter.jsonToLocation(jsonLocations.optJSONObject(i));
            System.out.println(locations[i]);
        }

        return locations;
    }

    //Biker API Server responds with an Array of Directions Arrays.
    //Each Element in a Directions Array is a JSONObject that specifies a specific step in the directions.



    //TODO: This feels really ugly. Maybe try to refactor in the future.
    private RouteStep[] extractSteps(JSONArray jsonRoute) throws JSONException {
        RouteJSONConverter converter = new RouteJSONConverter();
        RouteStep[] route = new RouteStep[jsonRoute.length()];

        System.out.println("------------------------------------------------------\nEXTRACTING STEPS\n------------------------------------------------------\n");
        System.out.println(jsonRoute);
        System.out.println("------------------------------------------------------\nEND OF STEPS\n------------------------------------------------------\n");


        for(int i = 0; i < jsonRoute.length(); i++){
            JSONArray steps = jsonRoute.optJSONArray(i);
            for(int j = 0; j < steps.length(); j++){
                JSONObject jsonStep = (JSONObject)steps.get(j);
                RouteStep location = converter.jsonToRouteStep(jsonStep);
                route[i] = location;
            }

        }

        return route;
    }


}
