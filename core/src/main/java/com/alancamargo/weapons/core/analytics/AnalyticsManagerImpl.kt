package com.alancamargo.weapons.core.analytics

import androidx.core.os.bundleOf
import com.alancamargo.weapons.core.tools.BundleBuilder
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import javax.inject.Inject

private const val EVENT_BUTTON_CLICKED = "button_clicked"

private const val PARAM_BUTTON_NAME = "button_name"

internal class AnalyticsManagerImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsManager {

    override fun trackScreenViewed(screenName: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        }
    }

    override fun trackButtonClicked(
        screenName: String,
        buttonName: String,
        properties: (BundleBuilder.() -> Unit)?
    ) {
        val params = properties?.let {
            BundleBuilder().apply(it)
        }?.build()?.apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            putString(PARAM_BUTTON_NAME, buttonName)
        } ?: bundleOf(
            FirebaseAnalytics.Param.SCREEN_NAME to screenName,
            PARAM_BUTTON_NAME to buttonName
        )

        firebaseAnalytics.logEvent(EVENT_BUTTON_CLICKED, params)
    }

    override fun trackEvent(
        screenName: String,
        eventName: String,
        properties: (BundleBuilder.() -> Unit)?
    ) {
        val params = properties?.let {
            BundleBuilder().apply(it)
        }?.build()?.apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        } ?: bundleOf(FirebaseAnalytics.Param.SCREEN_NAME to screenName)

        firebaseAnalytics.logEvent(eventName, params)
    }
}
