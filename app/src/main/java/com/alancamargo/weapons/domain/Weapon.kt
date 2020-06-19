package com.alancamargo.weapons.domain

data class Weapon(
    val id: Long,
    val name: String,
    val year: Int,
    val manufacturer: String,
    val countries: List<Country>,
    val type: WeaponType,
    val length: Float,
    val weight: Float,
    val calibre: Float,
    val capacity: Int,
    val rateOfFire: Int,
    val approximateRange: Int,
    val photos: List<String>
)