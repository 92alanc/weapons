package com.alancamargo.weapons.catalogue.ui.mapping

import com.alancamargo.weapons.catalogue.domain.model.CountryName
import com.alancamargo.weapons.common.ui.model.UiCountryName

internal fun CountryName.toUi() = when (this) {
    CountryName.SOUTH_VIETNAM -> UiCountryName.SOUTH_VIETNAM
    CountryName.UNITED_STATES -> UiCountryName.UNITED_STATES
    CountryName.AUSTRALIA -> UiCountryName.AUSTRALIA
    CountryName.NORTH_VIETNAM -> UiCountryName.NORTH_VIETNAM
    CountryName.VIETCONG -> UiCountryName.VIETCONG
    CountryName.UNKNOWN -> UiCountryName.UNKNOWN
}
