import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HttpTest{

	private final static String BIKER_API_URL = "http://localhost:5000/";

	public static void main(String[] args){
		String params = "getBasicRouteTest?latitude=37.4219983&longitude=-122.084";
		sendAPIRequest(BIKER_API_URL, params);
	}

    public static String sendAPIRequest(String API_URL, String requestParams){
        Scanner scanner;

        System.out.println("Sending Request...");

        try{
            InputStream urlInputStream = getConnectionStream(API_URL + requestParams);
            scanner = new Scanner(urlInputStream, "UTF-8");
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println("Response Length: " + responseBody.length());
            return responseBody;
        }
        catch(MalformedURLException m){
            m.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        return null;
    }

    private static InputStream getConnectionStream(String requestDest) throws IOException{
        URLConnection connection = new URL(requestDest).openConnection();
        System.out.println("Content Length: " + connection.getContentLength());
        System.out.println("Content Encoding: " + connection.getContentEncoding());
        return connection.getInputStream();
    }

}