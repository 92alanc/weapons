package com.alancamargo.weapons.core.analytics

interface AnalyticsManager {

    fun trackScreenViewed(screenName: String)

    fun trackButtonClicked(
        screenName: String,
        buttonName: String,
        properties: (BundleBuilder.() -> Unit)? = null
    )

    fun trackEvent(
        screenName: String,
        eventName: String,
        properties: (BundleBuilder.() -> Unit)? = null
    )
}
