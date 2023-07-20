package com.alancamargo.weapons.home.ui.viewmodel.home

import com.alancamargo.weapons.common.ui.UiWeaponQuery

internal sealed class HomeViewAction {

    data class NavigateToWeaponList(val query: UiWeaponQuery) : HomeViewAction()

    object ShowWeaponSearchDialogue : HomeViewAction()

    object ShowAppInfo : HomeViewAction()

    object ShowPrivacyPolicy : HomeViewAction()
}
