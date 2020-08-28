package com.alancamargo.weapons.ui.entities

import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiCountry(
    val id: Long,
    val name: String,
    val flagId: String,
    override val text: String? = name
) : UiWeaponListHeader