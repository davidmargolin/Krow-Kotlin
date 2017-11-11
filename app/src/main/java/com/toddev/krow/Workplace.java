package com.toddev.krow;

import com.google.android.gms.maps.model.LatLng;

public class Workplace {
    private String name;
    private LatLng location;
    private Amenities amenities;
    private int numRated;
    private double rating;
    private String creator_id;
    private String description;


    public Workplace(String name, LatLng location, Amenities amenities, int numRated;
                     double rating, String creator_id, String description, String address) {
        this.name = name;
        this.location = location;
        this.amenities = amenities;
        this.numRated = numRated;
        this.rating = rating;
        this.creator_id = creator_id;
        this.description = description;
        this.address = address;
    }

    public boolean newRating(int userRating) {
        if(userRating <= 5) {
            double sumOfratings = this.rating * this.numRated;
            this.numRated++;
            sumOfratings =+ userRating;
            this.rating = sumOfratings/this.num_rated;
            return true;
        }
        else {
            return false;
        }
    }


    public int getNumRated() {
        return numRated;
    }

    public void setNumRated(int numRated) {
        this.numRated = numRated;
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

    //funcation to get a location of a workplace
    public LatLng getLocation() {
        return location;
    }

    //function to set the lcoation of a workplace
    public void setLocation(LatLng location) {
        this.location = location;
    }







}
