package com.alancamargo.weapons.catalogue.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import javax.inject.Inject

private const val SCREEN_NAME = "weapon-details"

internal class WeaponDetailsAnalyticsImpl @Inject constructor(
    private val analyticsManager: AnalyticsManager
) : WeaponDetailsAnalytics {

    override fun trackScreenViewed() {
        analyticsManager.trackScreenViewed(SCREEN_NAME)
    }
}
