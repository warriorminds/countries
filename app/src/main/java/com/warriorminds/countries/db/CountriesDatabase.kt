package com.warriorminds.countries.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.warriorminds.countries.db.converters.*
import com.warriorminds.countries.models.Country

@Database(entities = [Country::class], version = 3)
@TypeConverters(
    value = [CountryListConverter::class, DoubleListConverter::class, LanguageListConverter::class, StringListConverter::class,
        TranslationConverter::class, RegionalBlockListConverter::class, CurrencyListConverter::class]
)
abstract class CountriesDatabase : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao
}