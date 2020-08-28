package com.alancamargo.weapons.ui.entities

import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiWeaponType(
    val id: Long,
    val name: String,
    override val text: String? = name
) : UiWeaponListHeader