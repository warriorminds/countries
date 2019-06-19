package com.warriorminds.countries.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.warriorminds.countries.models.*

class CountryListConverter {
    private val gson = Gson()

    @TypeConverter
    fun objectToString(obj: List<Country>) = gson.toJson(obj)

    @TypeConverter
    fun stringToList(data: String?): List<Country> {
        data?.let {
            return gson.fromJson(data, object : TypeToken<List<Country>>() {}.type)
        }
        return mutableListOf()
    }
}

class DoubleListConverter {
    private val gson = Gson()

    @TypeConverter
    fun objectToString(obj: List<Double>) = gson.toJson(obj)

    @TypeConverter
    fun stringToList(data: String?): List<Double> {
        data?.let {
            return gson.fromJson(data, object : TypeToken<List<Double>>() {}.type)
        }
        return mutableListOf()
    }
}

class LanguageListConverter {
    private val gson = Gson()

    @TypeConverter
    fun objectToString(obj: List<Language>) = gson.toJson(obj)

    @TypeConverter
    fun stringToList(data: String?): List<Language> {
        data?.let {
            return gson.fromJson(data, object : TypeToken<List<Language>>() {}.type)
        }
        return mutableListOf()
    }
}

class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun objectToString(obj: List<String>) = gson.toJson(obj)

    @TypeConverter
    fun stringToList(data: String?): List<String> {
        data?.let {
            return gson.fromJson(data, object : TypeToken<List<String>>() {}.type)
        }
        return mutableListOf()
    }
}

class TranslationConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToObject(data: String?): Translation {

        return gson.fromJson(data, object : TypeToken<Translation>() {}.type)
    }

    @TypeConverter
    fun objectToString(obj: Translation) = gson.toJson(obj)
}

class RegionalBlockListConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToObject(data: String?): List<RegionalBlock> {

        return gson.fromJson(data, object : TypeToken<List<RegionalBlock>>() {}.type)
    }

    @TypeConverter
    fun objectToString(obj: List<RegionalBlock>) = gson.toJson(obj)
}

class CurrencyListConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToObject(data: String?): List<Currency> {

        return gson.fromJson(data, object : TypeToken<List<Currency>>() {}.type)
    }

    @TypeConverter
    fun objectToString(obj: List<Currency>) = gson.toJson(obj)
}