package com.alancamargo.weapons.catalogue.ui.mapping

import com.alancamargo.weapons.catalogue.domain.model.WeaponQuery
import com.alancamargo.weapons.common.ui.UiWeaponQuery

internal fun UiWeaponQuery.toDomain() = when (this) {
    is UiWeaponQuery.All -> WeaponQuery.All
    is UiWeaponQuery.ByManufacturer -> WeaponQuery.ByManufacturer
    is UiWeaponQuery.ByCalibre -> WeaponQuery.ByCalibre
    is UiWeaponQuery.ByCountry -> WeaponQuery.ByCountry
    is UiWeaponQuery.ByType -> WeaponQuery.ByType
    is UiWeaponQuery.ByYear -> WeaponQuery.ByYear
    is UiWeaponQuery.ByName -> WeaponQuery.ByName(name)
}
