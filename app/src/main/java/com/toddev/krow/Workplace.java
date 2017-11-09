package com.toddev.krow;

import com.google.android.gms.maps.model.LatLng;

public class Workplace{
    private String name;
    private LatLng location;

    //to create a workplace, we need a name and a location
    public Workplace(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }

    //function to get the name of a workplace
    public String getName() {
        return name;
    }

    //function to set the name of a workplace
    public void setName(String name) {
        this.name = name;
    }

    //funcation to get a location of a workplace
    public LatLng getLocation() {
        return location;
    }

    //function to set the lcoation of a workplace
    public void setLocation(LatLng location) {
        this.location = location;
    }







}
