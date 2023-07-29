package com.alancamargo.weapons.webview.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import javax.inject.Inject

private const val SCREEN_NAME = "web-view"

private const val BUTTON_REFRESH = "refresh"
private const val BUTTON_BACK = "back"
private const val BUTTON_NATIVE_BACK = "native-back"

internal class WebViewAnalyticsImpl @Inject constructor(
    private val analyticsManager: AnalyticsManager
) : WebViewAnalytics {

    override fun trackScreenViewed() {
        analyticsManager.trackScreenViewed(SCREEN_NAME)
    }

    override fun trackRefreshClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_REFRESH
        )
    }

    override fun trackBackClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BACK
        )
    }

    override fun trackNativeBackClicked() {
        analyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_NATIVE_BACK
        )
    }
}
