package com.warriorminds.countries.utils

import android.content.Context
import com.warriorminds.countries.models.Country
import java.text.DecimalFormat

const val DEFAULT_PREFS = "default"

fun Country.getFlagUrl(size: FlagSize): String =
    "https://raw.githubusercontent.com/hjnilsson/country-flags/master/png${size.size}px/${alpha2Code?.toLowerCase()}.png"

fun Int.getCommaFormattedNumber(): String {
    val formatter = DecimalFormat("###,###,###")
    return formatter.format(this)
}

fun Double.getCommaFormattedNumber(): String {
    val formatter = DecimalFormat("###,###,###.##")
    return formatter.format(this)
}

fun Country.moreInformation(): String {
    val currencies = currencies?.joinToString(", ") {
        "${it.name} (${it.symbol})"
    }
    val languages = languages?.joinToString(", ") {
        "${it.name} (${it.nativeName})"
    }
    val regionalBlocks = regionalBlocs?.joinToString(", ") {
        "${it.name} (${it.acronym})"
    }
    return "People in $name are known as $demonym.\n" +
            "$name's native name is $nativeName.\n" +
            "In $name people use the following currency/currencies: $currencies.\n" +
            "They speak $languages.\n" +
            "This country belongs to $regionalBlocks."
}

fun Context.getLong(key: String) = getSharedPreferences(DEFAULT_PREFS, 0).getLong(key, 0)

fun Context.putLong(value: Long, key: String) {
    getSharedPreferences(DEFAULT_PREFS, 0).edit().putLong(key, value).apply()
}

enum class FlagSize(val size: Int) {
    SMALL(100),
    MEDIUM(250),
    BIG(1000)
}

enum class ResponseCode {
    SUCCESS,
    ERROR,
    EXCEPTION
}

data class Response(
    var countries: List<Country> = listOf(),
    var responseCode: ResponseCode = ResponseCode.ERROR
)