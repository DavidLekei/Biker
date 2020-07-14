package com.biker;

import android.location.Location;
import android.os.AsyncTask;

import com.biker.api.BikerAPI.BikerAPIRequestManager;
import com.biker.api.GoogleAPI.PlacesAPIRequestManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BikerTask extends AsyncTask<Object, Integer, Location> {

    private CompletableFuture<Location> locationFuture;
    private PlacesAPIRequestManager placesAPI;
    private BikerAPIRequestManager bikerAPI;

    public BikerTask(CompletableFuture<Location> locationFuture,
                     PlacesAPIRequestManager placesAPI,
                     BikerAPIRequestManager bikerAPI){
        this.locationFuture = locationFuture;
        this.placesAPI = placesAPI;
        this.bikerAPI = bikerAPI;
    }


    @Override
    protected Location doInBackground(Object... objects){

        Location currentLocation = null;

        try {
            currentLocation = locationFuture.get();
        }
        catch(ExecutionException ee){
            ee.printStackTrace();
        }
        catch(InterruptedException ie){
            ie.printStackTrace();
        }


        System.out.println("----------------- SENDING GOOGLE API REQUEST TEST -------------");
        placesAPI.sendGoogleAPIRequest("Winnipeg");
        System.out.println("---------------------------------------------------------------");

        //System.out.println("----------------- SENDING BIKER API REQUEST TEST --------------");
        //bikerAPI.getBasicRoute("TESTLOCATION");
        //System.out.println("---------------------------------------------------------------");

        return currentLocation;
    }

    @Override
    protected void onPostExecute(Location location){
        System.out.println("******Location recieved: " + location.toString() + "*********");
    }

}
