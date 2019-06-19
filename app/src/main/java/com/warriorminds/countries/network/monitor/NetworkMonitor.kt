package com.warriorminds.countries.network.monitor

interface NetworkMonitor {
    fun isNetworkAvailable(): Boolean
}