package com.alancamargo.weapons.catalogue.domain.model

internal sealed class WeaponQuery {

    data object All : WeaponQuery()

    data class ByName(val name: String) : WeaponQuery()

    data object ByYear : WeaponQuery()

    data object ByCountry : WeaponQuery()

    data object ByType : WeaponQuery()

    data object ByCalibre : WeaponQuery()

    data object ByMake : WeaponQuery()
}
