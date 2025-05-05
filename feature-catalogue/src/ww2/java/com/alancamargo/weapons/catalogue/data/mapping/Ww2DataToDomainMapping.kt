package com.alancamargo.weapons.catalogue.data.mapping

import com.alancamargo.weapons.catalogue.domain.model.CountryName

internal fun String.toCountryName() = when (this) {
    "british-empire" -> CountryName.BRITISH_EMPIRE
    "nazi-germany" -> CountryName.NAZI_GERMANY
    "soviet-union" -> CountryName.SOVIET_UNION
    "france" -> CountryName.FRANCE
    "japanese-empire" -> CountryName.JAPANESE_EMPIRE
    "fascist-italy" -> CountryName.FASCIST_ITALY
    "united-states" -> CountryName.UNITED_STATES
    else -> CountryName.UNKNOWN
}
