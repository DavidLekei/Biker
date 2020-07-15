package com.biker;

import android.location.Location;
import android.os.AsyncTask;

import com.biker.api.BikerAPI.BikerAPIRequestManager;
import com.biker.api.BikerAPI.Route;
import com.biker.api.GoogleAPI.PlacesAPIRequestManager;
import com.biker.api.LocationAPI.LocationJSONConverter;
import com.biker.ui.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BikerTask extends AsyncTask<Object, Integer, Route> {

    private CompletableFuture locationFuture;
    private PlacesAPIRequestManager placesAPI;
    private BikerAPIRequestManager bikerAPI;
    private MainActivity activity;
    private LocationJSONConverter converter;

    public BikerTask(MainActivity activity,
                     CompletableFuture locationFuture,
                     PlacesAPIRequestManager placesAPI,
                     BikerAPIRequestManager bikerAPI){
        this.activity = activity;
        this.locationFuture = locationFuture;
        this.placesAPI = placesAPI;
        this.bikerAPI = bikerAPI;
        this.converter = new LocationJSONConverter();
    }


    @Override
    protected Route doInBackground(Object... objects){

        Route route = null;
//        System.out.println("----------------- SENDING GOOGLE API REQUEST TEST -------------");
//        placesAPI.sendGoogleAPIRequest("Winnipeg");
//        System.out.println("---------------------------------------------------------------");


        try {
            Location location = (Location)locationFuture.get();
            System.out.println("*******Location recieved: " + location.toString() + " ***********");
            route = getRoute(converter.locationToParamString(location));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("*******Location recieved: " + locations[0].toString() + " ***********");

        //System.out.println("----------------- SENDING BIKER API REQUEST TEST --------------");
        //bikerAPI.getBasicRoute("TESTLOCATION");
        //System.out.println("---------------------------------------------------------------");

        return route;
    }


    @Override
    protected void onPostExecute(Route route){
        activity.drawRoute(route);
    }

    private Route getRoute(String requestParams){
        Route route = bikerAPI.getBasicRoute(requestParams);
        return null;
    }

}
