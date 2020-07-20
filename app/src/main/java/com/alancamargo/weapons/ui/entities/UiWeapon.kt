package com.alancamargo.weapons.ui.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiWeapon(
    val id: Long,
    val name: String,
    val year: UiYear?,
    val manufacturer: UiManufacturer?,
    val country: UiCountry?,
    val type: UiWeaponType,
    val length: Int?,
    val weight: Float?,
    val calibre: UiCalibre?,
    val capacity: Int?,
    val rateOfFire: Int?,
    val accuracy: Int?,
    val photos: List<String>
) : Parcelable