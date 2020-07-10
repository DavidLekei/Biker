package BikerAPI;

public class BikerAPIRequestManager {

    private final String BIKER_API_URL = "localhost:5000";
    private String key; //TODO: Implement client verification using API_KEYS stored in Redis?

    public BikerAPIRequestManager(){

    }

    //locationID is provided by the Google Places API. It will be used by the Server to build a route.
    public Route getBasicRoute(String locationID){

        Route route = new Route("TODO");

        return route;
    }

}
