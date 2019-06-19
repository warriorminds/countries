package com.warriorminds.countries.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(indices = [(Index("name", unique = true))])
data class Country(
                   @PrimaryKey
                   val name: String,
                   val topLevelDomain: List<String>?,
                   val alpha2Code: String?,
                   val alpha3Code: String?,
                   val callingCodes: List<String>?,
                   val capital: String?,
                   val altSpellings: List<String>?,
                   val region: String?,
                   val subregion: String?,
                   val population: Int?,
                   val latlng: List<Double>?,
                   val demonym: String?,
                   val area: Double?,
                   val gini: Double?,
                   val timezones: List<String>?,
                   val borders: List<String>?,
                   val nativeName: String?,
                   val numericCode: String?,
                   val currencies: List<Currency>?,
                   val languages: List<Language>?,
                   val translations: Translation?,
                   val flag: String?,
                   val regionalBlocs: List<RegionalBlock>?,
                   val cioc: String?): Serializable

data class Currency(val code: String,
                    val name: String,
                    val symbol: String): Serializable

data class Language(val iso639_1: String,
                    val iso639_2: String,
                    val name: String,
                    val nativeName: String): Serializable

data class Translation(val de: String,
                       val es: String,
                       val fr: String,
                       val ja: String,
                       val it: String,
                       val br: String,
                       val pt: String,
                       val nl: String,
                       val hr: String,
                       val fa: String): Serializable

data class RegionalBlock(val acronym: String,
                         val name: String,
                         val otherAcronyms: List<String>,
                         val otherNames: List<String>): Serializable