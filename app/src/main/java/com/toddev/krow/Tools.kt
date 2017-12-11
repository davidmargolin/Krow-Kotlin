package com.toddev.krow

import android.support.v4.app.Fragment
import com.google.android.gms.maps.GoogleMap

// instead of a static method in the Workplace Class.
fun getWorkResource(keyword: String): Int {
    when (keyword) {
    //spacing
        "Cozy" -> return R.drawable.ic_seat_legroom_reduced_grey600_24dp
        "Spacious" -> return R.drawable.ic_seat_legroom_extra_grey600_24dp
    //noise
        "Loud" -> return R.drawable.ic_action_volume_up
        "Quiet" -> return R.drawable.ic_action_volume_down
    //wifi
        "Wifi" -> return R.drawable.ic_action_wifi
        "No Wifi" -> return R.drawable.ic_action_signal_wifi_off
    //bathrooms
        "Bathroom" -> return R.drawable.ic_action_bathroom
        "No Bathroom" -> return R.drawable.ic_action_bathroom
    //food
        "Food" -> return R.drawable.ic_action_restaurant_menu
        "No Food" -> return R.drawable.ic_action_restaurant_menu
    //outlets
        "Power" -> return R.drawable.ic_action_power
        "No Power" -> return R.drawable.ic_action_power
        else -> return 0
    }
}

fun workSpaceWithInfo(place: Workplace, map: GoogleMap): WorkSpaceInfoFragment {
    val fragment = WorkSpaceInfoFragment()
    fragment.setObject(place, map)
    return fragment
}

fun listFragWithList(workplaces: List<Workplace>): Fragment {
    val fragment = WorkSpaceListFragment()
    fragment.setList(workplaces)
    return fragment
}