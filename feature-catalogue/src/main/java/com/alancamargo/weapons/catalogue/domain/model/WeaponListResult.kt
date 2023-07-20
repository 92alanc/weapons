package com.alancamargo.weapons.catalogue.domain.model

internal sealed class WeaponListResult {

    data class Success(val weapons: List<Weapon>) : WeaponListResult()

    object Error : WeaponListResult()
}
