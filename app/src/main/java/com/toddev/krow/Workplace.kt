package com.toddev.krow

class Workplace(val name: String?=null, val amenities: Amenities?=null, var numrated: Int?=null,
                var rating: Double?=null, val creator_id: String?=null, val description: String?=null,
                val latitude: Double?=null, val longitude: Double?=null, val address: String?=null) {

    fun newRating(userRating: Int): Boolean {
        if (userRating <= 5) {
            // need to be very careful about nullable types when doing math operations. (lots of !!)
            var sumOfRatings = this.rating!! * this.numrated!!
            this.numrated = this.numrated!!+1
            sumOfRatings += userRating.toDouble()
            this.rating = sumOfRatings / this.numrated!!
            return true
        } else {
            return false
        }
    }

    fun getWorkResource(keyword: String): Int {
        when (keyword) {
            "Quiet" -> return R.drawable.quiet
            "Loud" -> return R.drawable.loud
            "Cozy" -> return R.drawable.cozy
            "Spacious" -> return R.drawable.spacious
            "Wifi" -> return R.drawable.wifi
            "No Wifi" -> return R.drawable.no_wifi
            else -> return 0
        }
    }

}
