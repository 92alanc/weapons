package com.alancamargo.weapons.home.ui.viewmodel.weaponsearch

import com.alancamargo.weapons.common.ui.WeaponQuery

internal sealed class WeaponSearchViewAction {

    data class NavigateToWeaponList(val query: WeaponQuery.ByName) : WeaponSearchViewAction()
}
