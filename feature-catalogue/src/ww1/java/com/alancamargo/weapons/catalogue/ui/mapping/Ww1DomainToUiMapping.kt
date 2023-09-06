package com.alancamargo.weapons.catalogue.ui.mapping

import com.alancamargo.weapons.catalogue.domain.model.CountryName
import com.alancamargo.weapons.common.ui.model.UiCountryName

internal fun CountryName.toUi() = when (this) {
    CountryName.BRITISH_EMPIRE -> UiCountryName.BRITISH_EMPIRE
    CountryName.GERMAN_EMPIRE -> UiCountryName.GERMAN_EMPIRE
    CountryName.RUSSIAN_EMPIRE -> UiCountryName.RUSSIAN_EMPIRE
    CountryName.AUSTRO_HUNGARIAN_EMPIRE -> UiCountryName.AUSTRO_HUNGARIAN_EMPIRE
    CountryName.FRANCE -> UiCountryName.FRANCE
    CountryName.JAPANESE_EMPIRE -> UiCountryName.JAPANESE_EMPIRE
    CountryName.DENMARK -> UiCountryName.DENMARK
    CountryName.SPAIN -> UiCountryName.SPAIN
    CountryName.BELGIUM -> UiCountryName.BELGIUM
    CountryName.ITALY -> UiCountryName.ITALY
    CountryName.ROMANIA -> UiCountryName.ROMANIA
    CountryName.UNITED_STATES -> UiCountryName.UNITED_STATES
    CountryName.UNKNOWN -> UiCountryName.UNKNOWN
}
