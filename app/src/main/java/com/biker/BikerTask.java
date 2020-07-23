package com.biker;

import android.location.Location;
import android.os.AsyncTask;

import com.biker.api.BikerAPI.BikerAPIRequestManager;
import com.biker.api.BikerAPI.Route;
import com.biker.api.BikerAPI.RouteBuilder;
import com.biker.api.GoogleAPI.PlacesAPIRequestManager;
import com.biker.api.LocationAPI.LocationJSONConverter;
import com.biker.ui.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BikerTask extends AsyncTask<Object, Integer, Route> {

    private CompletableFuture locationFuture;
    private BikerAPIRequestManager bikerAPI;
    private MainActivity activity;
    private LocationJSONConverter converter;

    public BikerTask(MainActivity activity,
                     CompletableFuture locationFuture,
                     BikerAPIRequestManager bikerAPI){
        this.activity = activity;
        this.locationFuture = locationFuture;
        this.bikerAPI = bikerAPI;
        this.converter = new LocationJSONConverter();
    }


    @Override
    protected Route doInBackground(Object... objects){

        Route route = null;

        try {
            Location location = (Location)locationFuture.get();
            System.out.println("*******Location recieved: " + location.toString() + " ***********");
            route = getRoute(location);
        } catch (ExecutionException | InterruptedException | JSONException | IOException e) {
            e.printStackTrace();
        }

        return route;
    }


    @Override
    protected void onPostExecute(Route route){
        activity.drawRoute(route);
    }

    private Route getRoute(Location location) throws JSONException, IOException {
            Route route = null;
            try {
                route = RouteBuilder.buildRoute(bikerAPI.getBasicRoute(location));
                route.setStartingLocation(location);
            }
            catch(ProtocolException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return route;
    }

}
