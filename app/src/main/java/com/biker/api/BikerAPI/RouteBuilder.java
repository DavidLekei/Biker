package com.biker.api.BikerAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class RouteBuilder {

    public RouteBuilder(){}

    //TODO: Make this builder method more robust. Add logic to it so that it checks what fields are in the jsonRoute object and only adds those to the new route object.
    public static Route buildRoute(JSONObject jsonRoute) throws JSONException {
        Route route = new Route();

        route.setStartingLocation(jsonRoute.getString("startingLocation"));

        return route;
    }


}
