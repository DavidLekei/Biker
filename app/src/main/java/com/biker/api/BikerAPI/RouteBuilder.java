package com.biker.api.BikerAPI;

import com.biker.api.LocationAPI.BikerLocation;
import com.biker.api.LocationAPI.LocationJSONConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RouteBuilder {

    public RouteBuilder(){}

    //TODO: Make this builder method more robust. Add logic to it so that it checks what fields are in the jsonRoute object and only adds those to the new route object.
    public static Route buildRoute(JSONObject jsonRoute) throws JSONException {
        Route route = new Route();

        //route.setStartingLocation(jsonRoute.getString("startingLocation"));
        route.setLocations(extractResults(jsonRoute));

        return route;
    }

    //TODO: This feels really ugly. Maybe try to refactor in the future.
    private static BikerLocation[] extractResults(JSONObject jsonRoute) throws JSONException {
        LocationJSONConverter converter = new LocationJSONConverter();
        JSONArray results = jsonRoute.getJSONArray("results");
        BikerLocation[] route = new BikerLocation[results.length()];

        for(int i = 0; i < results.length(); i++){
            JSONObject jsonLocation = (JSONObject)results.get(i);
            BikerLocation location = converter.jsonToLocation(jsonLocation);
            route[i] = location;
        }

        return route;
    }


}
