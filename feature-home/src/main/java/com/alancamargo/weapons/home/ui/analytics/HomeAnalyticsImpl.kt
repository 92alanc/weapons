package com.alancamargo.weapons.home.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import javax.inject.Inject

private const val SCREEN_NAME = "home"

private const val BUTTON_ALL_WEAPONS = "all-weapons"
private const val BUTTON_BY_NAME = "group-by-name"
private const val BUTTON_BY_COUNTRY = "group-by-country"
private const val BUTTON_BY_YEAR = "group-by-year"
private const val BUTTON_BY_TYPE = "group-by-type"
private const val BUTTON_BY_CALIBRE = "group-by-calibre"
private const val BUTTON_BY_MAKE = "group-by-make"
private const val BUTTON_APP_INFO = "app-info"
private const val BUTTON_PRIVACY_POLICY = "privacy-policy"
private const val BUTTON_FIRST_ACCESS_INFORMATION_DISMISSED = "first-access-information-dismissed"

internal class HomeAnalyticsImpl @Inject constructor(
    private val analyticsManager: AnalyticsManager
) : HomeAnalytics {

    override fun trackScreenViewed() {
        analyticsManager.trackScreenViewed(SCREEN_NAME)
    }

    override fun trackFirstAccessInformationDismissed() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_FIRST_ACCESS_INFORMATION_DISMISSED
        )
    }

    override fun trackAllWeaponsClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_ALL_WEAPONS
        )
    }

    override fun trackGroupByNameClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BY_NAME
        )
    }

    override fun trackGroupByCountryClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BY_COUNTRY
        )
    }

    override fun trackGroupByYearClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BY_YEAR
        )
    }

    override fun trackGroupByTypeClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BY_TYPE
        )
    }

    override fun trackGroupByCalibreClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BY_CALIBRE
        )
    }

    override fun trackGroupByMakeClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BY_MAKE
        )
    }

    override fun trackAppInfoClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_APP_INFO
        )
    }

    override fun trackPrivacyPolicyClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_PRIVACY_POLICY
        )
    }
}
