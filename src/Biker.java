import GoogleAPI.PlacesAPIRequestManager;

public class Biker {

    public static void main(String[] args){

        PlacesAPIRequestManager placesAPI = new PlacesAPIRequestManager();
        placesAPI.sendGoogleAPIRequest("Winnipeg");




        System.out.println("End of Processing.");
    }
}
