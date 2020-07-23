package com.biker;

import android.location.Location;
import android.os.AsyncTask;

import com.biker.api.BikerAPI.BikerAPIManager;
import com.biker.api.BikerAPI.Route.EmptyRoute;
import com.biker.api.BikerAPI.Route.Route;
import com.biker.api.BikerAPI.Route.RouteBuilder;
import com.biker.api.LocationAPI.LocationJSONConverter;
import com.biker.ui.MainActivity;

import org.json.JSONException;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BikerTask extends AsyncTask<Object, Integer, Route> {

    private CompletableFuture locationFuture;
    private MainActivity activity;
    private RouteBuilder routeBuilder;

    public BikerTask(MainActivity activity,
                     CompletableFuture locationFuture){
        this.activity = activity;
        this.locationFuture = locationFuture;
        this.routeBuilder = new RouteBuilder();
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
        Route route;
        route = routeBuilder.buildRoute(location);
        return route;
    }

}
