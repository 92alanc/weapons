package com.alancamargo.weapons.catalogue.ui.viewmodel

import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader

internal data class WeaponListViewState(
    val isLoading: Boolean = false,
    val weaponListWithHeader: List<Map.Entry<UiWeaponListHeader?, List<UiWeapon>>>? = null,
    val weapons: List<UiWeapon>? = null,
    val showEmptyState: Boolean = false,
    val showError: Boolean = false
) {

    fun onLoading() = copy(
        isLoading = true,
        weaponListWithHeader = null,
        weapons = null,
        showEmptyState = false,
        showError = false
    )

    fun onFinishedLoading() = copy(isLoading = false)

    fun onWeaponListWithHeaderReceived(
        weaponListWithHeader: List<Map.Entry<UiWeaponListHeader?, List<UiWeapon>>>
    ) = copy(
        weaponListWithHeader = weaponListWithHeader,
        weapons = null,
        showEmptyState = false,
        showError = false
    )

    fun onWeaponsReceived(weapons: List<UiWeapon>) = copy(
        weapons = weapons,
        weaponListWithHeader = null,
        showEmptyState = false,
        showError = false
    )

    fun onEmptyState() = copy(
        weaponListWithHeader = null,
        weapons = null,
        showEmptyState = true,
        showError = false
    )

    fun onError() = copy(
        weaponListWithHeader = null,
        weapons = null,
        showEmptyState = false,
        showError = true
    )
}
