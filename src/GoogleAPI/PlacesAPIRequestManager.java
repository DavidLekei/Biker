package GoogleAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class PlacesAPIRequestManager {

    private final String GOOGLE_PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/";
    private String key;

    public PlacesAPIRequestManager(){
        key = getGoogleAPIKey();
    }

    public void sendGoogleAPIRequest(String input){
        String requestParams = "json?key=" + this.key + "&input=" + input + "&inputtype=textquery";

        Scanner scanner;

        try {
            InputStream urlInputStream = getConnectionStream(requestParams);
            scanner = new Scanner(urlInputStream);
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println("Response Body: ");
            System.out.println(responseBody);
        }
        catch(MalformedURLException m){ //TODO: Better exception handling
            m.printStackTrace();
        }
        catch(IOException e){ //TODO: Better exception handling
            e.printStackTrace();
        }
    }


    private InputStream getConnectionStream(String requestParams) throws MalformedURLException, IOException {
        URLConnection connection = new URL(GOOGLE_PLACES_API_URL + requestParams).openConnection();
        return connection.getInputStream();
    }

    //TODO: DO NOT HARDCODE API KEY
    //TODO: Read in API key from file
    //TODO: Add exception handling if key file is not found/read
    private String getGoogleAPIKey(){
        return "AIzaSyAkd1GUKo7po4IWP04adGK9IX257DAsarA";
        //return "not a real key";
    }
}
