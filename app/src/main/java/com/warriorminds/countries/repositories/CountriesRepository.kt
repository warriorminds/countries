package com.warriorminds.countries.repositories

import com.warriorminds.countries.utils.Response

interface CountriesRepository {
    suspend fun getCountriesAsync(): Response
}