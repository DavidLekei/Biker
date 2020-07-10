package com.biker;

import android.os.AsyncTask;

import com.biker.api.BikerAPI.BikerAPIRequestManager;
import com.biker.api.GoogleAPI.PlacesAPIRequestManager;

public class BikerCLI extends AsyncTask<String, String, String> {

    public BikerCLI(){}


    @Override
    protected String doInBackground(String... strings){

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
}
