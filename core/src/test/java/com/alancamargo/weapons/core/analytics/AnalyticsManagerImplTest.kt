package com.alancamargo.weapons.core.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AnalyticsManagerImplTest {

    private val mockFirebaseAnalytics = mockk<FirebaseAnalytics>(relaxed = true)
    private val analyticsManager = AnalyticsManagerImpl(mockFirebaseAnalytics)

    @Test
    fun `trackScreenViewed should track screen view event on firebase`() {
        // WHEN
        val screenName = "screen"
        analyticsManager.trackScreenViewed(screenName)

        // THEN
        verify { mockFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, any()) }
    }

    @Test
    fun `trackButtonClicked should track button click event on firebase`() {
        // WHEN
        val buttonName = "button"
        val screenName = "screen"
        analyticsManager.trackButtonClicked(buttonName, screenName)

        // THEN
        val eventName = "button_clicked"
        verify { mockFirebaseAnalytics.logEvent(eventName, any()) }
    }

    @Test
    fun `trackEvent should track event on firebase`() {
        // WHEN
        val eventName = "event"
        val screenName = "screen"
        analyticsManager.trackEvent(screenName, eventName)

        // THEN
        verify { mockFirebaseAnalytics.logEvent(eventName, any()) }
    }
}
