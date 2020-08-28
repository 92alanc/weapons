package com.alancamargo.weapons.ui.entities

import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiYear(
    val id: Long,
    val year: Int,
    override val text: String? = year.toString()
) : UiWeaponListHeader