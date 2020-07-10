import API.BikerAPI.BikerAPIRequestManager;
import API.GoogleAPI.PlacesAPIRequestManager;

public class Biker {

    public static void main(String[] args){

        PlacesAPIRequestManager placesAPI = new PlacesAPIRequestManager();
        BikerAPIRequestManager bikerAPI = new BikerAPIRequestManager();

        System.out.println("----------------- SENDING GOOGLE API REQUEST TEST -------------");
        placesAPI.sendGoogleAPIRequest("Winnipeg");
        System.out.println("---------------------------------------------------------------");

        System.out.println("----------------- SENDING BIKER API REQUEST TEST --------------");
        bikerAPI.getBasicRoute("TESTLOCATION");
        System.out.println("---------------------------------------------------------------");




        System.out.println("End of Processing.");
    }
}
