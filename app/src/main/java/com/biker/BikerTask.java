package com.biker;

import android.location.Location;
import android.os.AsyncTask;

import com.biker.api.BikerAPI.BikerAPIRequestManager;
import com.biker.api.GoogleAPI.PlacesAPIRequestManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BikerTask extends AsyncTask<Object, Integer, Location> {

    private CompletableFuture<Location> locationFuture;

    public BikerTask(CompletableFuture<Location> locationFuture){
        this.locationFuture = locationFuture;
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

        PlacesAPIRequestManager placesAPI = new PlacesAPIRequestManager();
        BikerAPIRequestManager bikerAPI = new BikerAPIRequestManager();

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
