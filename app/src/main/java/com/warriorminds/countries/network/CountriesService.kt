package com.warriorminds.countries.network

import com.warriorminds.countries.models.Country
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CountriesService {
    @GET("rest/v2/all")
    fun getAllCountries(): Deferred<Response<List<Country>>>
}