package com.alancamargo.weapons.home.ui.viewmodel.weaponsearch

import com.alancamargo.weapons.common.ui.UiWeaponQuery

internal sealed class WeaponSearchViewAction {

    data class NavigateToWeaponList(val query: UiWeaponQuery.ByName) : WeaponSearchViewAction()
}
