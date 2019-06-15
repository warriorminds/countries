package com.warriorminds.countries.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.warriorminds.countries.R
import com.warriorminds.countries.maps.MapProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MapFragment : Fragment() {

    companion object {
        const val LATITUDE = "latitude_arg"
        const val LONGITUDE = "longitude_arg"
    }

    @Inject
    lateinit var mapProvider: MapProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapProvider.onCreate(savedInstanceState, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val latitude = arguments?.getDouble(LATITUDE)
        val longitude = arguments?.getDouble(LONGITUDE)
        if (latitude != null && longitude != null) {
            mapProvider.moveCamera(latitude, longitude)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mapProvider.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        mapProvider.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapProvider.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapProvider.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapProvider.onLowMemory()
    }


}
