package com.toddev.krow;

import com.google.android.gms.maps.model.LatLng;

public class Amenities{
    private String bathrooms;

    private boolean food;
    private String noise;
    private String outlets;
    private String space;
    private String wifi;

    public Amenities(String bathrooms, boolean food, String noise, String outlets, String space, String wifi) {
        this.bathrooms = bathrooms;
        this.food = food;
        this.noise = noise;
        this.outlets = outlets;
        this.space = space;
        this.wifi = wifi;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        if(bathrooms == 'clean' || bathrooms == 'dirty' || bathrooms == 'none'){
            this.bathrooms = bathrooms;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        if(food) {
            this.food = food;
            return true;
        }
        else {
            return false;
        }
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        if(noise == 'quiet' || noise == 'loud') {
            this.noise = noise;
            return true;
        }
        else {
            return false;
        }
    }

    public String getOutlets() {
        return outlets;
    }

    public void setOutlets(String outlets) {
        if(outlets == 'many' || outlets == 'little') {
            this.outlets = outlets;
            return true;
        }
        else {
            return false;
        }
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        if(space == 'roomy' || space == 'cozy') {
            this.space = space;
            return true;
        }
        else {
            return false;
        }
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        if(wifi == 'fast' || wifi == 'slow') {
            this.wifi = wifi;
            return true;
        }
        else{
            return false;
        }

    }

}