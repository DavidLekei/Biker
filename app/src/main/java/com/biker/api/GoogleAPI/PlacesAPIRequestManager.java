package com.biker.api.GoogleAPI;

import com.biker.api.APIRequestManager;

public class PlacesAPIRequestManager extends APIRequestManager {

    private final String GOOGLE_PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/";
    private String key;

    public PlacesAPIRequestManager(){
        key = getGoogleAPIKey();
    }

    public void sendGoogleAPIRequest(String input){
        String requestParams = "json?key=" + this.key + "&input=" + input + "&inputtype=textquery";

        super.sendAPIRequest(GOOGLE_PLACES_API_URL, requestParams);
    }


    //TODO: DO NOT HARDCODE API KEY
    //TODO: Read in API key from file
    //TODO: Add exception handling if key file is not found/read
    private String getGoogleAPIKey(){
        return "AIzaSyAkd1GUKo7po4IWP04adGK9IX257DAsarA";
        //return "not a real key";
    }
}
