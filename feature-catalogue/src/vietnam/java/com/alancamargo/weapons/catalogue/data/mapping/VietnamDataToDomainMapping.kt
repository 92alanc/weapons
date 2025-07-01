package com.alancamargo.weapons.catalogue.data.mapping

import com.alancamargo.weapons.catalogue.domain.model.CountryName

internal fun String.toCountryName() = when (this) {
    "south-vietnam" -> CountryName.SOUTH_VIETNAM
    "united-states" -> CountryName.UNITED_STATES
    "australia" -> CountryName.AUSTRALIA
    "north-vietnam" -> CountryName.NORTH_VIETNAM
    "vietcong" -> CountryName.VIETCONG
    else -> CountryName.UNKNOWN
}
