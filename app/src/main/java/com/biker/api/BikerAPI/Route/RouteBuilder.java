package com.biker.api.BikerAPI.Route;

import com.biker.api.LocationAPI.BikerLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RouteBuilder {

    public RouteBuilder(){}

    //TODO: Make this builder method more robust. Add logic to it so that it checks what fields are in the jsonRoute object and only adds those to the new route object.
    public static Route buildRoute(JSONArray jsonRoute) throws JSONException {
        Route route = new Route();

        //route.setStartingLocation(jsonRoute.getString("startingLocation"));
        //route.setLocations(extractResults(jsonRoute));
        route.setRouteSteps(extractSteps(jsonRoute));

        return route;
    }

    private static BikerLocation[] extractLocations(JSONArray jsonRoute) throws JSONException {
        RouteJSONConverter converter = new RouteJSONConverter();
        BikerLocation[] locations = new BikerLocation[jsonRoute.length()];

        //jsonRoute is an Array of Arrays. Each Array in jsonRoute is an Array of JSONObjects. Each JSONObject refers to a
        //specific step. So since we only
    }

    //Biker API Server responds with an Array of Directions Arrays.
    //Each Element in a Directions Array is a JSONObject that specifies a specific step in the directions.



    //TODO: This feels really ugly. Maybe try to refactor in the future.
    private static RouteStep[] extractSteps(JSONArray jsonRoute) throws JSONException {
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
