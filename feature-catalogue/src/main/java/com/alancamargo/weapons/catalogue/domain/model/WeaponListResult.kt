package com.alancamargo.weapons.catalogue.domain.model

internal sealed class WeaponListResult {

    data class Success(val weapons: Map<WeaponListHeader?, List<Weapon>>) : WeaponListResult()

    object Error : WeaponListResult()
}
