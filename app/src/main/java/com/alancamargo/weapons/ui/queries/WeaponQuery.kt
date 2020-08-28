package com.alancamargo.weapons.ui.queries

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class WeaponQuery : Parcelable {

    @Parcelize
    object All : WeaponQuery()

    @Parcelize
    data class ByName(val name: String) : WeaponQuery()

    @Parcelize
    object ByYear : WeaponQuery()

    @Parcelize
    object ByCountry : WeaponQuery()

    @Parcelize
    object ByType : WeaponQuery()

    @Parcelize
    object ByCalibre : WeaponQuery()

    @Parcelize
    object ByManufacturer : WeaponQuery()

}