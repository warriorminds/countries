package com.warriorminds.countries.network.monitor

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkMonitorImpl @Inject constructor(val context: Context): NetworkMonitor {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isNetworkAvailable(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}