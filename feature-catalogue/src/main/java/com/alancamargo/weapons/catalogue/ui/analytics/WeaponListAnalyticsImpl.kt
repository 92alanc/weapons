package com.alancamargo.weapons.catalogue.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import javax.inject.Inject

private const val SCREEN_NAME = "weapon-list"

private const val BUTTON_WEAPON = "weapon"
private const val BUTTON_BACK = "back"
private const val BUTTON_NATIVE_BACK = "native-back"

private const val PARAM_WEAPON_NAME = "weapon-name"

internal class WeaponListAnalyticsImpl @Inject constructor(
    private val analyticsManager: AnalyticsManager
) : WeaponListAnalytics {

    override fun trackScreenViewed() {
        analyticsManager.trackScreenViewed(SCREEN_NAME)
    }

    override fun trackWeaponClicked(weaponName: String) {
        val properties = mapOf(PARAM_WEAPON_NAME to weaponName)
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_WEAPON,
            properties = properties
        )
    }

    override fun trackBackClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BACK
        )
    }

    override fun trackNativeBackClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_NATIVE_BACK
        )
    }
}
