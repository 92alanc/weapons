package com.alancamargo.weapons.core.analytics

import com.alancamargo.weapons.core.log.Logger
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
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
        properties: Map<String, Any>?
    ) {
        val props = mutableMapOf<String, Any>().apply {
            put(PARAM_BUTTON_NAME, buttonName)
            properties?.forEach { (key, value) ->
                put(key, value)
            }
        }

        trackEvent(
            screenName = screenName,
            eventName = EVENT_BUTTON_CLICKED,
            properties = props
        )
    }

    override fun trackEvent(
        screenName: String,
        eventName: String,
        properties: Map<String, Any>?
    ) {
        logEvent(screenName = screenName, eventName = eventName, properties = properties)

        firebaseAnalytics.logEvent(eventName) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)

            properties?.forEach { (key, value) ->
                when (value) {
                    is Long -> param(key, value)
                    is Double -> param(key, value)
                    is String -> param(key, value)
                }
            }
        }
    }

    private fun logEvent(screenName: String, eventName: String, properties: Map<String, Any>?) {
        val props = properties?.entries?.joinToString { (key, value) -> "$key: $value" }
        val message = "$LOG_PREFIX Event: $eventName, Screen: $screenName, Properties: $props"
        logger.debug(message)
    }
}
