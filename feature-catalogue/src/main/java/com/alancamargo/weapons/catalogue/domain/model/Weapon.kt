package com.alancamargo.weapons.catalogue.domain.model

internal data class Weapon(
    val id: Long,
    val name: String,
    val year: Year?,
    val make: Make?,
    val country: Country?,
    val type: WeaponType,
    val lengthInMm: Int?,
    val massInKg: Float?,
    val calibre: Calibre?,
    val capacityInRounds: Int?,
    val rateOfFireInRpm: Int?,
    val effectiveRangeInM: Int?,
    val photos: List<String>
)
