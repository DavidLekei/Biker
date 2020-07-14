package com.biker;

import android.content.Context;
import android.os.AsyncTask;

import com.biker.api.BikerAPI.BikerAPIRequestManager;
import com.biker.api.GoogleAPI.PlacesAPIRequestManager;
import com.biker.api.LocationAPI.BikerLocationManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class BikerCLI extends AsyncTask<String, String, String> {

    private FusedLocationProviderClient fusedLocationClient;
    private Context mainContext;

    public BikerCLI(Context context){
        this.mainContext = context;
    }


    @Override
    protected String doInBackground(String... strings){


        BikerLocationManager locationManager = new BikerLocationManager(mainContext);
        locationManager.getCurrentLocation();

        PlacesAPIRequestManager placesAPI = new PlacesAPIRequestManager();
        BikerAPIRequestManager bikerAPI = new BikerAPIRequestManager();

        System.out.println("----------------- SENDING GOOGLE API REQUEST TEST -------------");
        placesAPI.sendGoogleAPIRequest("Winnipeg");
        System.out.println("---------------------------------------------------------------");

        System.out.println("----------------- SENDING BIKER API REQUEST TEST --------------");
        bikerAPI.getBasicRoute("TESTLOCATION");
        System.out.println("---------------------------------------------------------------");




        return("End of Processing.");
    }

    private Context getContext(){
        return this.mainContext;
    }
}
