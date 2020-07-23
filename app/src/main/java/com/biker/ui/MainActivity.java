package com.biker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

import com.biker.api.BikerAPI.BikerAPIManager;
import com.biker.api.BikerAPI.Route.Route;
import com.biker.api.Callbacks.CreateRouteButtonListener;
import com.biker.api.Callbacks.LocationFailureCallback;
import com.biker.api.Callbacks.LocationSuccessCallback;
import com.biker.api.Callbacks.MapRouteCallback;
import com.example.biker.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.CompletableFuture;


public class MainActivity extends AppCompatActivity {

    private SupportMapFragment mapFragment;
    private FusedLocationProviderClient client;
    private CompletableFuture<Location> locationFuture;
    private Route route;
    private Button createRouteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.locationFuture = new CompletableFuture<>();
        this.route = null;

        createMap();
        setUpButtons();

        //buildRoute(locationFuture);
    }

    private void setUpButtons(){
        this.createRouteBtn = this.findViewById(R.id.createRouteBtn);
        createRouteBtn.setOnClickListener(new CreateRouteButtonListener(this, locationFuture));
    }

    private void createMap(){
        System.out.println("Creating Map...");
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        getCurrentLocation();
    }

    private void getCurrentLocation(){
        System.out.println("Getting Current Location...");
        Task<Location> getLocation;

        client = LocationServices.getFusedLocationProviderClient(MainActivity.this);


        if(hasPermission()){
            System.out.println("App Has Permission!");
            getLocation = client.getLastLocation();
            getLocation.addOnSuccessListener(this, new LocationSuccessCallback(mapFragment, locationFuture));
            getLocation.addOnFailureListener(this, new LocationFailureCallback());
        }
        else{
            requestPermission();
        }
    }

    private boolean hasPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            //Toast.makeText(MainActivity.this, "App does not have permission.", Toast.LENGTH_LONG);
            System.out.println("********APP DOES NOT HAVE PERMISSION");
            return false;
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
    }

    public SupportMapFragment getMapFragment(){
        return this.mapFragment;
    }

//    //TODO: Change return type to Route and have this method return a Route object which will then be drawn on the map.
//    public void buildRoute(BikerAPIRequestManager bikerAPI, CompletableFuture locationFuture) {
//
//        this.bikerAPI = new BikerAPIRequestManager();
//        bikerTask = new BikerTask(this, locationFuture, bikerAPI);
//        bikerTask.execute();
//    }

    public void drawRoute(Route route){
        if(route.isEmpty()){
            System.out.println("Failed to Load Route. Please Try Again.");
            //TODO: Throw exception
        }
        else{
            this.route = route;
            System.out.println("Drawing Route on Google Map");
            mapFragment.getMapAsync(new MapRouteCallback(route));
        }
    }

}
