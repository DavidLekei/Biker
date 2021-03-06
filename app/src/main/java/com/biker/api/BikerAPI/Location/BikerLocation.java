package com.biker.api.BikerAPI.Location;

import com.google.android.gms.maps.model.LatLng;

public class BikerLocation {

    private LatLng latlng;
    private String name;
    private String placeId;
    private String[] types;
    private String address;

    public BikerLocation(LatLng latlng, String name, String placeId, String[] types, String address){
        this.latlng = latlng;
        this.name = name;
        this.placeId = placeId;
        this.types = types;
        this.address = address;
    }

    public LatLng getLatLng(){return this.latlng;}
    public String getName(){return this.name;}
    public String getPlaceId(){return this.placeId;}
    public String[] getTypes(){return this.types;}
    public String getAddress(){return this.address;}

    public String toString(){
        return this.name + "\n\t" + this.address + "\n\t" + this.placeId + "\n\t" + this.types + "\n\t" + this.latlng + "\n\n";
    }
}
