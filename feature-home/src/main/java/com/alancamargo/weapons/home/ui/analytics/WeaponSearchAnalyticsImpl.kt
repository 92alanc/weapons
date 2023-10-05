package com.alancamargo.weapons.home.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import javax.inject.Inject

private const val SCREEN_NAME = "weapon-search"
private const val BUTTON_SEARCH = "search"
private const val PARAM_SEARCH_TERM = "search-term"
private const val EVENT_DIALOGUE_CANCELLED = "dialogue-cancelled"

internal class WeaponSearchAnalyticsImpl @Inject constructor(
    private val analyticsManager: AnalyticsManager
) : WeaponSearchAnalytics {

    override fun trackDialogueViewed() {
        analyticsManager.trackScreenViewed(SCREEN_NAME)
    }

    override fun trackWeaponSearched(searchTerm: String) {
        val properties = mapOf(PARAM_SEARCH_TERM to searchTerm)
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_SEARCH,
            properties = properties
        )
    }

    override fun trackDialogueCancelled() {
        analyticsManager.trackEvent(
            screenName = SCREEN_NAME,
            eventName = EVENT_DIALOGUE_CANCELLED
        )
    }
}
