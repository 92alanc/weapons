package com.alancamargo.weapons.home.ui.analytics

internal interface WeaponSearchAnalytics {

    fun trackDialogueViewed()

    fun trackWeaponSearched(searchTerm: String)

    fun trackDialogueCancelled()
}
