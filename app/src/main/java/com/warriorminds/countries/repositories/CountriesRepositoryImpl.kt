package com.warriorminds.countries.repositories

import android.content.Context
import com.warriorminds.countries.db.CountriesDatabase
import com.warriorminds.countries.network.CountriesService
import com.warriorminds.countries.network.monitor.NetworkMonitor
import com.warriorminds.countries.utils.Response
import com.warriorminds.countries.utils.ResponseCode
import com.warriorminds.countries.utils.getLong
import com.warriorminds.countries.utils.putLong
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(private val service: CountriesService,
                                                  private val database: CountriesDatabase,
                                                  private val context: Context,
                                                  private val networkMonitor: NetworkMonitor): CountriesRepository {

    private val COUNTRIES_RETRIEVED = "countries_retrieved_network"

    override suspend fun getCountriesAsync(): Response {
        val countriesResponse = Response()
        try {

            val countriesFromDb = database.countriesDao().load()

            if (countriesFromDb.isNullOrEmpty() || shouldFetchListFromNetwork()) {
                val response = service.getAllCountries().await()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    countriesResponse.countries = response.body()!!
                    countriesResponse.responseCode = ResponseCode.SUCCESS
                    database.countriesDao().insert(countriesResponse.countries)
                    context.putLong(Date().time, COUNTRIES_RETRIEVED)
                } else {
                    countriesResponse.responseCode = ResponseCode.ERROR
                }
            } else {
                countriesResponse.countries = countriesFromDb
                countriesResponse.responseCode = ResponseCode.SUCCESS
            }
        } catch (e: Exception) {
            countriesResponse.responseCode = ResponseCode.EXCEPTION
        }
        return countriesResponse
    }

    private fun shouldFetchListFromNetwork() : Boolean {
        val lastDate = context.getLong(COUNTRIES_RETRIEVED)
        return (lastDate + 60*60*24*7*1000) <= System.currentTimeMillis() && networkMonitor.isNetworkAvailable()
    }
}