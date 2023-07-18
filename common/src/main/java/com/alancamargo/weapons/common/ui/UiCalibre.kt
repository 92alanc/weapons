package com.alancamargo.weapons.common.ui

import kotlinx.parcelize.Parcelize

@Parcelize
data class UiCalibre(
    val id: Long,
    val name: String,
    override val text: String? = name
) : UiWeaponListHeader