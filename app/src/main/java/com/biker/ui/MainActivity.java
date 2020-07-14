package com.biker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.biker.api.Callbacks.LocationFailureCallback;
import com.biker.api.Callbacks.LocationSuccessCallback;
import com.example.biker.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private SupportMapFragment mapFragment;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMap();
    }

    private void createMap(){
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        getCurrentLocation();
    }

    private void getCurrentLocation(){
        Task<Location> getLocation;

        client = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if(hasPermission()){
            getLocation = client.getLastLocation();
            getLocation.addOnSuccessListener(this, new LocationSuccessCallback(mapFragment));
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

}
