package com.alancamargo.weapons.home.ui.analytics

internal interface HomeAnalytics {

    fun trackScreenViewed()

    fun trackFirstAccessInformationDismissed()

    fun trackAllWeaponsClicked()

    fun trackGroupByNameClicked()

    fun trackGroupByCountryClicked()

    fun trackGroupByYearClicked()

    fun trackGroupByTypeClicked()

    fun trackGroupByCalibreClicked()

    fun trackGroupByMakeClicked()

    fun trackAppInfoClicked()

    fun trackPrivacyPolicyClicked()
}
