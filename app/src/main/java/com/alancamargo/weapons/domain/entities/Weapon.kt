package com.alancamargo.weapons.domain.entities

data class Weapon(
    val id: Long,
    val name: String,
    val year: Int,
    val manufacturer: Manufacturer,
    val country: Country,
    val type: WeaponType,
    val length: Float,
    val weight: Float,
    val calibre: Calibre,
    val capacity: Int,
    val rateOfFire: Int,
    val accuracy: Int,
    val photos: List<String>
)