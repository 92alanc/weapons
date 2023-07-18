package com.alancamargo.weapons.core.analytics

import com.alancamargo.weapons.core.tools.BundleBuilder

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
