package com.warriorminds.countries.utils

import com.warriorminds.countries.models.Country

fun Country.getFlagUrl(size: FlagSize): String
        = "https://raw.githubusercontent.com/hjnilsson/country-flags/master/png${size.size}px/${alpha2Code.toLowerCase()}.png"

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

data class Response(var countries: List<Country> = listOf(),
                    var responseCode: ResponseCode = ResponseCode.ERROR)