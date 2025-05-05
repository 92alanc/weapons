package com.alancamargo.weapons.catalogue.ui.mapping

import com.alancamargo.weapons.catalogue.domain.model.CountryName
import com.alancamargo.weapons.common.ui.model.UiCountryName

internal fun CountryName.toUi() = when (this) {
    CountryName.BRITISH_EMPIRE -> UiCountryName.BRITISH_EMPIRE
    CountryName.NAZI_GERMANY -> UiCountryName.NAZI_GERMANY
    CountryName.SOVIET_UNION -> UiCountryName.SOVIET_UNION
    CountryName.FRANCE -> UiCountryName.FRANCE
    CountryName.JAPANESE_EMPIRE -> UiCountryName.JAPANESE_EMPIRE
    CountryName.FASCIST_ITALY -> UiCountryName.FASCIST_ITALY
    CountryName.UNITED_STATES -> UiCountryName.UNITED_STATES
    CountryName.UNKNOWN -> UiCountryName.UNKNOWN
}
