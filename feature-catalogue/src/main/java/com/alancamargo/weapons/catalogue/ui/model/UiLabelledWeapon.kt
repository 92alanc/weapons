package com.alancamargo.weapons.catalogue.ui.model

internal data class UiLabelledWeapon(
    val name: String,
    val country: UiLabelledCountry?,
    val year: String?,
    val manufacturer: String?,
    val type: String,
    val calibre: String?,
    val length: String?,
    val mass: String?,
    val capacity: String?,
    val rateOfFire: String?,
    val effectiveRange: String?,
    val photos: List<String>
)
