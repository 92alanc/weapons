package com.alancamargo.weapons.catalogue.data.mapping

import com.alancamargo.weapons.catalogue.domain.model.CountryName

internal fun String.toCountryName() = when (this) {
    "british-empire" -> CountryName.BRITISH_EMPIRE
    "german-empire" -> CountryName.GERMAN_EMPIRE
    "russian-empire" -> CountryName.RUSSIAN_EMPIRE
    "austro-hungarian-empire" -> CountryName.AUSTRO_HUNGARIAN_EMPIRE
    "france" -> CountryName.FRANCE
    "japanese-empire" -> CountryName.JAPANESE_EMPIRE
    "italy" -> CountryName.ITALY
    "romania" -> CountryName.ROMANIA
    "belgium" -> CountryName.BELGIUM
    "united-states" -> CountryName.UNITED_STATES
    "ottoman-empire" -> CountryName.OTTOMAN_EMPIRE
    else -> CountryName.UNKNOWN
}
