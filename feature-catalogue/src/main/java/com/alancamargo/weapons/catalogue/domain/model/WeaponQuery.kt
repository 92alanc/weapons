package com.alancamargo.weapons.catalogue.domain.model

internal sealed class WeaponQuery {

    object All : WeaponQuery()

    data class ByName(val name: String) : WeaponQuery()

    object ByYear : WeaponQuery()

    object ByCountry : WeaponQuery()

    object ByType : WeaponQuery()

    object ByCalibre : WeaponQuery()

    object ByManufacturer : WeaponQuery()
}
