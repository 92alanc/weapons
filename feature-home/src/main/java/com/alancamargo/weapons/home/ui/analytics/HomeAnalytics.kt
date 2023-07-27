package com.alancamargo.weapons.home.ui.analytics

internal interface HomeAnalytics {

    fun trackScreenViewed()

    fun trackAllWeaponsClicked()

    fun trackGroupByNameClicked()

    fun trackGroupByCountryClicked()

    fun trackGroupByYearClicked()

    fun trackGroupByTypeClicked()

    fun trackGroupByCalibreClicked()

    fun trackGroupByManufacturerClicked()

    fun trackAppInfoClicked()

    fun trackPrivacyPolicyClicked()
}
