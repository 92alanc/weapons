package com.alancamargo.weapons.core.analytics

import androidx.core.os.bundleOf
import com.alancamargo.weapons.core.log.Logger
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

private const val EVENT_BUTTON_CLICKED = "button_clicked"
private const val PARAM_BUTTON_NAME = "button_name"
private const val LOG_PREFIX = "Analytics"

internal class AnalyticsManagerImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val logger: Logger
) : AnalyticsManager {

    override fun trackScreenViewed(screenName: String) {
        trackEvent(screenName = screenName, eventName = FirebaseAnalytics.Event.SCREEN_VIEW)
    }

    override fun trackButtonClicked(
        screenName: String,
        buttonName: String,
        properties: (BundleBuilder.() -> Unit)?
    ) {
        trackEvent(
            screenName = screenName,
            eventName = EVENT_BUTTON_CLICKED
        ) {
            PARAM_BUTTON_NAME withValue buttonName
        }
    }

    override fun trackEvent(
        screenName: String,
        eventName: String,
        properties: (BundleBuilder.() -> Unit)?
    ) {
        logEvent(screenName = screenName, eventName = eventName, properties = properties)

        val params = properties?.let {
            BundleBuilder().apply(it)
        }?.build()?.apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        } ?: bundleOf(FirebaseAnalytics.Param.SCREEN_NAME to screenName)

        firebaseAnalytics.logEvent(eventName, params)
    }

    private fun logEvent(
        screenName: String,
        eventName: String,
        properties: (BundleBuilder.() -> Unit)? = null
    ) {
        val propertiesString = properties?.let {
            BundleBuilder().apply(it)
        }?.asString()
        val message = "$LOG_PREFIX Event: $eventName, Screen: $screenName, Properties: $propertiesString"
        logger.debug(message)
    }
}
