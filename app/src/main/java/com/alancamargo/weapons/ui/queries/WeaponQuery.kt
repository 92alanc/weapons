package com.alancamargo.weapons.ui.queries

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class WeaponQuery : Parcelable {

    @Parcelize
    object All : WeaponQuery()

    @Parcelize
    data class ByName(val name: String) : WeaponQuery()

    @Parcelize
    data class ByYear(val year: Int) : WeaponQuery()

    @Parcelize
    data class ByCountry(val countryId: Long) : WeaponQuery()

    @Parcelize
    data class ByType(val typeId: Long) : WeaponQuery()

    @Parcelize
    data class ByCalibre(val calibreId: Long) : WeaponQuery()

    @Parcelize
    data class ByManufacturer(val manufacturerId: Long) : WeaponQuery()

}