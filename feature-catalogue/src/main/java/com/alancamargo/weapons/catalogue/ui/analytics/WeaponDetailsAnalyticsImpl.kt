package com.alancamargo.weapons.catalogue.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import javax.inject.Inject

private const val SCREEN_NAME = "weapon-details"

private const val BUTTON_BACK = "back"
private const val BUTTON_NATIVE_BACK = "native-back"

internal class WeaponDetailsAnalyticsImpl @Inject constructor(
    private val analyticsManager: AnalyticsManager
) : WeaponDetailsAnalytics {

    override fun trackScreenViewed() {
        analyticsManager.trackScreenViewed(SCREEN_NAME)
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
