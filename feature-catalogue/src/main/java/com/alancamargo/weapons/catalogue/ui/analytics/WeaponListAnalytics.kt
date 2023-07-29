package com.alancamargo.weapons.catalogue.ui.analytics

internal interface WeaponListAnalytics {

    fun trackScreenViewed()

    fun trackWeaponClicked(weaponName: String)

    fun trackBackClicked()

    fun trackNativeBackClicked()
}
