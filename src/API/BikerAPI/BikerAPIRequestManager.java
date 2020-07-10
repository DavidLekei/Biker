package API.BikerAPI;

import java.security.InvalidParameterException;

public class BikerAPIRequestManager {

    private final String BIKER_API_URL = "localhost:5000/";
    private String key; //TODO: Implement client verification using API_KEYS stored in Redis?

    public BikerAPIRequestManager(){

    }

    //locationID is provided by the Google Places API. It will be used by the Server to build a route.
    public Route getBasicRoute(String locationID) throws InvalidParameterException, NullPointerException{

        //TODO: Implement custom Exception types.
        if(locationID == null){
            throw new NullPointerException();
        }
        if(locationID == ""){
            throw new InvalidParameterException();
        }


        Route route = new Route("TODO");
        sendBikerAPIRequest(locationID);

        return route;
    }

    private void sendBikerAPIRequest(String locationID){

    }

}
