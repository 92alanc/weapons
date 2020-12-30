package com.alancamargo.weapons.ui.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiWeapon(
    val id: Long,
    val name: String,
    val year: UiYear?,
    val manufacturer: UiManufacturer?,
    val country: UiCountry?,
    val type: UiWeaponType,
    val lengthInMm: Int?,
    val massInKg: Float?,
    val calibre: UiCalibre?,
    val capacityInRounds: Int?,
    val rateOfFireInRpm: Int?,
    val effectiveRangeInM: Int?,
    val photos: List<String>
) : Parcelable