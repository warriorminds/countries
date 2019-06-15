package com.warriorminds.countries.maps

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.warriorminds.countries.R
import javax.inject.Inject

class GoogleMapProvider @Inject constructor(): MapProvider {

    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?, view: View) {
        val mapContainer = view.findViewById<FrameLayout>(R.id.map_container)
        mapView = mapContainer.findViewById(R.id.map)

        mapView?.let {
            it.onCreate(savedInstanceState)
            it.getMapAsync { googleMap ->
                googleMap.uiSettings.isMapToolbarEnabled = false
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mapView?.onSaveInstanceState(outState)
    }

    override fun onPause() {
        mapView?.onPause()
    }

    override fun onResume() {
        mapView?.onResume()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        mapView?.onLowMemory()
    }

    override fun moveCamera(latLongBounds: LatLngBounds) {
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLongBounds, 0)
        mapView?.getMapAsync { googleMap ->
            googleMap.moveCamera(cameraUpdate)
        }
    }

    override fun moveCamera(latitude: Double, longitude: Double) {
        val latLong = LatLng(latitude, longitude)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLong, 5.0f)
        mapView?.getMapAsync {
            val builder = LatLngBounds.builder()
            builder.include(latLong)
            it.addMarker(MarkerOptions().position(latLong))
            it.moveCamera(cameraUpdate)
        }
    }
}