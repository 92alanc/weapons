package com.alancamargo.weapons.ui.entities

import kotlinx.parcelize.Parcelize

@Parcelize
data class UiWeaponType(
    val id: Long,
    val name: String,
    override val text: String? = name
) : UiWeaponListHeader