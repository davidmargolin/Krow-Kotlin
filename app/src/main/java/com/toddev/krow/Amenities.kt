package com.toddev.krow

// the ? means that the variable can be of null type
class Amenities(var bathrooms: String?, var food: String?, var noise: String?, var outlets: String?,
                var space: String?, var wifi: String?) {

    // secondary constructor has to call this() or super()
    /* empty constructor needed for GSON (I used empty constructor as primary at first but both ways
    are pretty much the same. Either a bunch of variables initialized as null at the beginning or a
    constructor that sets everything to null but only for the one GSON use case. I commented out the
    alternative below*/
    constructor() : this(null, null, null, null, null, null) {
        this.bathrooms = bathrooms
        this.food = food
        this.noise = noise
        this.outlets = outlets
        this.space = space
        this.wifi = wifi
    }

    //getters and setters come free
}

/*
class Amenities() {
    // sets everything to null by default, think this is worse than the above version
    var bathrooms: String? = null
    var food: String? = null
    var noise: String? = null
    var outlets: String? = null
    var space: String? = null
    var wifi: String? = null

    constructor(bathrooms: String?, food: String?, noise: String?, outlets: String?, space: String?,
                wifi: String) : this() {
        this.bathrooms = bathrooms
        this.food = food
        this.noise = noise
        this.outlets = outlets
        this.space = space
        this.wifi = wifi
    }
}
*/
