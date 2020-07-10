package API;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public abstract class APIRequestManager {

    public void sendAPIRequest(String API_URL, String requestParams){
        Scanner scanner;

        try{
            InputStream urlInputStream = getConnectionStream(API_URL + requestParams);
            scanner = new Scanner(urlInputStream);
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println("Response Body Test: " );
            System.out.println(responseBody);
        }
        catch(MalformedURLException m){
            m.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    private InputStream getConnectionStream(String requestDest) throws MalformedURLException, IOException{
        URLConnection connection = new URL(requestDest).openConnection();
        return connection.getInputStream();
    }

}
