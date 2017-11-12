package com.toddev.krow;

import com.google.android.gms.maps.model.LatLng;

public class Workplace {
    private String name;
    private Amenities amenities;
    private int numrated;
    private double rating;
    private String creator_id;
    private String description;
    private double latitude;
    private double longitude;

    public Workplace(String name, Amenities amenities, int numrated, double rating, String creator_id, String description, double latitude, double longitude, String address) {
        this.name = name;
        this.amenities = amenities;
        this.numrated = numrated;
        this.rating = rating;
        this.creator_id = creator_id;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Workplace(){

    }




    public boolean newRating(int userRating) {
        if(userRating <= 5) {
            double sumOfRatings = this.rating * this.numrated;
            this.numrated++;
            sumOfRatings += userRating;
            this.rating = sumOfRatings/this.numrated;
            return true;
        }
        else {
            return false;
        }
    }


    public int getNumrated() {
        return numrated;
    }

    public void setNumrated(int numrated) {
        this.numrated = numrated;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //to create a workplace, we need a name and a location

    //function to get the name of a workplace
    public String getName() {
        return name;
    }

    //function to set the name of a workplace
    public void setName(String name) {
        this.name = name;
    }


}
