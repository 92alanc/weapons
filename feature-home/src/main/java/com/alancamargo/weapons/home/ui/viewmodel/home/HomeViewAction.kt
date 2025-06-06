package com.alancamargo.weapons.home.ui.viewmodel.home

import com.alancamargo.weapons.common.ui.UiWeaponQuery

internal sealed class HomeViewAction {

    data class NavigateToWeaponList(val query: UiWeaponQuery) : HomeViewAction()

    data object ShowWeaponSearchDialogue : HomeViewAction()

    data object ShowAppInfo : HomeViewAction()

    data class ShowPrivacyPolicy(val url: String) : HomeViewAction()

    data class ShowFirstAccessInformation(val text: String) : HomeViewAction()
}
