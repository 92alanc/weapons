package com.alancamargo.weapons.domain

data class Weapon(
    val id: Long,
    val name: String,
    val year: Int,
    val manufacturer: String,
    val countryId: Long,
    val typeId: Long,
    val length: Float,
    val weight: Float,
    val calibre: String,
    val capacity: Int,
    val rateOfFire: Int,
    val accuracy: Int,
    val photos: List<String>
)