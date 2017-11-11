package com.toddev.krow;

import com.google.android.gms.maps.model.LatLng;

public class Workplace {
    private String name;
    private LatLng location;
    private Amenities amenities;
    private int num_rated;
    private double rating;
    private String creator_id;
    private String description;

    public Workplace(String name, LatLng location, Amenities amenities, int num_rated,
                     double rating, String creator_id, String description, String address) {
        this.name = name;
        this.location = location;
        this.amenities = amenities;
        this.num_rated = numRated;
        this.rating = rating;
        this.creator_id = creator_id;
        this.description = description;
        this.address = address;
    }

    public void newRating(int userRating) {
        if(userRating <= 5) {
            double sum = this.rating * this.numRated;
            numRated++
            this.rating = this.rating + userRating;
            this.rating = this.rating/

        }
    }
    public int getNum_rated() {
        return num_rated;
    }

    public void setNum_rated(int num_rated) {
        this.num_rated = num_rated;
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
