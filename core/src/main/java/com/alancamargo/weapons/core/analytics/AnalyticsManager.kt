package com.alancamargo.weapons.core.analytics

interface AnalyticsManager {

    fun trackScreenViewed(screenName: String)

    fun trackButtonClicked(
        screenName: String,
        buttonName: String,
        properties: Map<String, Any>? = null
    )

    fun trackEvent(
        screenName: String,
        eventName: String,
        properties: Map<String, Any>? = null
    )
}
