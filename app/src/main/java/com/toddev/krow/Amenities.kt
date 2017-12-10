package com.toddev.krow

// the ? means that the variable can be of null type
// (need to set all as null for GSON empty constructor requirement)
data class Amenities(var bathrooms: String? = null, var food: String? = null,
                     var noise: String? = null, var outlets: String? = null,
                     var space: String? = null, var wifi: String? = null) {

    //getters and setters come free
}