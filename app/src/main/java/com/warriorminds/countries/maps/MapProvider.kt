package com.warriorminds.countries.maps

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLngBounds

interface MapProvider {
    /**
     * Method to be called when the map is created.
     *
     * @param savedInstanceState
     * @param view
     */
    fun onCreate(savedInstanceState: Bundle?, view: View)

    /**
     * Method to be called when the map container goes to onSaveInstance State.
     *
     * @param outState
     */
    fun onSaveInstanceState(outState: Bundle)

    /**
     * Method to be called when the map container goes to on Pause.
     */
    fun onPause()

    /**
     * Method to be called when the map container goes to onResume.
     */
    fun onResume()

    /**
     * Method to be called when the map container goes to onDestroy.
     */
    fun onDestroy()

    /**
     * Method to be called when the map container goes to onLowMemory.
     */
    fun onLowMemory()

    /**
     * Method to be called to move the camera to the specified bounds.
     *
     * @param latLongBounds Bounds to move the camera to.
     */
    fun moveCamera(latLongBounds: LatLngBounds)

    fun moveCamera(latitude: Double, longitude: Double)
}