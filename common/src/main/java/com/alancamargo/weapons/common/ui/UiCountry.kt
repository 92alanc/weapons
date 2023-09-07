package com.alancamargo.weapons.common.ui

import com.alancamargo.weapons.common.ui.model.UiCountryName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiCountry(
    val id: Long,
    val name: UiCountryName,
    val flagId: String,
    override val text: String? = flagId
) : UiWeaponListHeader