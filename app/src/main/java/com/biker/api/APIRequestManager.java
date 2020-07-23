package com.biker.api;

import android.renderscript.ScriptGroup;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class APIRequestManager {

    private OkHttpClient client;

    public String sendAPIRequest(String API_URL, String requestParams, int i) throws IOException{

        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();

        Request request = new Request.Builder()
                .url(API_URL+requestParams)
                .addHeader("Connection", "close")
                .build();

        Response response = client.newCall(request).execute();

//        String contentLength = response.headers().get("Content-Length");
//        String contentType = response.headers().get("Content-Type");
//        String contentEncoding = response.headers().get("Content-Encoding");
//        String charset = response.headers().get("charset");
//        System.out.println("************************\nResponse Length: " + contentLength + "" +
//                "\nResponse Type: " + contentType +
//                "\nContent Encoding: " + contentEncoding +
//                "\nCharset: " + charset + "****************************");

        //String s = response.body().string();
        //response.body().close();
        //response.close();

        //return s;
        return response.body().string();
    }

    public String sendAPIRequest(String API_URL, String requestParams){
        Scanner scanner;

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

    private InputStream getConnectionStream(String requestDest) throws IOException{
        URLConnection connection = new URL(requestDest).openConnection();
        System.out.println("Content Length: " + connection.getContentLength());
        System.out.println("Content Encoding: " + connection.getContentEncoding());
        return connection.getInputStream();
    }

}
