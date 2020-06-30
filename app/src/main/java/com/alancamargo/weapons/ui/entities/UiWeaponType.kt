package com.alancamargo.weapons.ui.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiWeaponType(val id: Long, val name: String) : Parcelable, SimpleTextEntity {

    override fun getText(): String = name

}