package com.alancamargo.weapons.core.analytics

import com.alancamargo.weapons.core.log.Logger
import com.google.firebase.analytics.FirebaseAnalytics
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AnalyticsManagerImplTest {

    private val mockFirebaseAnalytics = mockk<FirebaseAnalytics>(relaxed = true)
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val analyticsManager = AnalyticsManagerImpl(mockFirebaseAnalytics, mockLogger)

    @Test
    fun `trackScreenViewed should log event`() {
        // WHEN
        val screenName = "screen"
        analyticsManager.trackScreenViewed(screenName)

        // THEN
        verify { mockLogger.debug(message = any()) }
    }

    @Test
    fun `trackScreenViewed should track screen view event on firebase`() {
        // WHEN
        val screenName = "screen"
        analyticsManager.trackScreenViewed(screenName)

        // THEN
        verify { mockFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, any()) }
    }

    @Test
    fun `trackButtonClicked should log event`() {
        // WHEN
        val buttonName = "button"
        val screenName = "screen"
        analyticsManager.trackButtonClicked(buttonName, screenName)

        // THEN
        verify { mockLogger.debug(message = any()) }
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
    fun `trackEvent should log event`() {
        // WHEN
        val eventName = "event"
        val screenName = "screen"
        analyticsManager.trackEvent(screenName, eventName)

        // THEN
        verify { mockLogger.debug(message = any()) }
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
