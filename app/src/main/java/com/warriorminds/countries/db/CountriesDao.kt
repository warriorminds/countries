package com.warriorminds.countries.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.warriorminds.countries.models.Country

@Dao
interface CountriesDao {
    @Insert(onConflict = REPLACE)
    fun insert(countries: List<Country>)

    @Query(value = "SELECT * FROM country ORDER BY name ASC")
    fun load(): List<Country>
}