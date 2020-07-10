package com.example.biker.src;

import android.os.AsyncTask;

import com.example.biker.src.API.BikerAPI.BikerAPIRequestManager;
import com.example.biker.src.API.GoogleAPI.PlacesAPIRequestManager;

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
