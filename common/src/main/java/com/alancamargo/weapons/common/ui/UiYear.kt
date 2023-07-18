package com.alancamargo.weapons.common.ui

import kotlinx.parcelize.Parcelize

@Parcelize
data class UiYear(
    val id: Long,
    val year: Int,
    override val text: String? = year.toString()
) : UiWeaponListHeader