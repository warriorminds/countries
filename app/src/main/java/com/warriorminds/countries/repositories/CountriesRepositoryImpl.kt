package com.warriorminds.countries.repositories

import com.warriorminds.countries.network.CountriesService
import com.warriorminds.countries.utils.Response
import com.warriorminds.countries.utils.ResponseCode
import java.lang.Exception
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(private val service: CountriesService): CountriesRepository {
    override suspend fun getCountriesAsync(): Response {
        val countriesResponse = Response()
        try {
            val response = service.getAllCountries().await()
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                countriesResponse.countries = response.body()!!
                countriesResponse.responseCode = ResponseCode.SUCCESS
            } else {
                countriesResponse.responseCode = ResponseCode.ERROR
            }
        } catch (e: Exception) {
            countriesResponse.responseCode = ResponseCode.EXCEPTION
        }
        return countriesResponse
    }
}