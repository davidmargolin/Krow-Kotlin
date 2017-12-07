package com.toddev.krow;

public class Amenities{
    private String bathrooms;
    private String food;
    private String noise;
    private String outlets;
    private String space;
    private String wifi;

    public Amenities(){

    }

    public Amenities(String bathrooms, String food, String noise, String outlets, String space, String wifi) {
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

    public boolean setBathrooms(String bathrooms) {
        if(bathrooms == "clean" || bathrooms == "dirty" || bathrooms == "none"){
            this.bathrooms = bathrooms;
            return true;
        }
        else{
            return false;
        }
    }


    public String getFood() {
        return food;
    }

    public Boolean setFood(String food) {
        if(food=="yes"||food=="no") {
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

    public boolean setNoise(String noise) {
        if(noise == "quiet" || noise == "loud") {
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

    public boolean setOutlets(String outlets) {
        if(outlets == "yes" || outlets == "none") {
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

    public boolean setSpace(String space) {
        if(space == "spacious" || space == "cozy") {
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

    public boolean setWifi(String wifi) {
        if(wifi == "yes" || wifi == "no") {
            this.wifi = wifi;
            return true;
        }
        else{
            return false;
        }

    }

}