package com.alancamargo.weapons.common.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class UiWeaponQuery : Parcelable {

    @Parcelize
    object All : UiWeaponQuery()

    @Parcelize
    data class ByName(val name: String) : UiWeaponQuery()

    @Parcelize
    object ByYear : UiWeaponQuery()

    @Parcelize
    object ByCountry : UiWeaponQuery()

    @Parcelize
    object ByType : UiWeaponQuery()

    @Parcelize
    object ByCalibre : UiWeaponQuery()

    @Parcelize
    object ByMake : UiWeaponQuery()
}