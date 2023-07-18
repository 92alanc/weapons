package com.alancamargo.weapons.home.ui.viewmodel

import com.alancamargo.weapons.common.ui.WeaponQuery

internal sealed class HomeViewAction {

    data class NavigateToWeaponsList(val query: WeaponQuery) : HomeViewAction()

    object ShowWeaponSearchDialogue : HomeViewAction()

    object ShowAppInfo : HomeViewAction()

    object ShowPrivacyPolicy : HomeViewAction()
}
