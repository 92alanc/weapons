package com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist

import com.alancamargo.weapons.common.ui.UiWeapon

internal sealed class WeaponListViewAction {

    data class NavigateToWeaponDetails(val weapon: UiWeapon) : WeaponListViewAction()

    object Finish : WeaponListViewAction()
}
