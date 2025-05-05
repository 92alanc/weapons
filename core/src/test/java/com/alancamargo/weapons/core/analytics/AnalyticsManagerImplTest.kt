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
        // When
        val screenName = "screen"
        analyticsManager.trackScreenViewed(screenName)

        // Then
        verify { mockLogger.debug(message = any()) }
    }

    @Test
    fun `trackScreenViewed should track screen view event on firebase`() {
        // When
        val screenName = "screen"
        analyticsManager.trackScreenViewed(screenName)

        // Then
        verify { mockFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, any()) }
    }

    @Test
    fun `trackButtonClicked should log event`() {
        // When
        val buttonName = "button"
        val screenName = "screen"
        analyticsManager.trackButtonClicked(buttonName, screenName)

        // Then
        verify { mockLogger.debug(message = any()) }
    }

    @Test
    fun `trackButtonClicked should track button click event on firebase`() {
        // When
        val buttonName = "button"
        val screenName = "screen"
        analyticsManager.trackButtonClicked(buttonName, screenName)

        // Then
        val eventName = "button_clicked"
        verify { mockFirebaseAnalytics.logEvent(eventName, any()) }
    }

    @Test
    fun `trackEvent should log event`() {
        // When
        val eventName = "event"
        val screenName = "screen"
        analyticsManager.trackEvent(screenName, eventName)

        // Then
        verify { mockLogger.debug(message = any()) }
    }

    @Test
    fun `trackEvent should track event on firebase`() {
        // When
        val eventName = "event"
        val screenName = "screen"
        analyticsManager.trackEvent(screenName, eventName)

        // Then
        verify { mockFirebaseAnalytics.logEvent(eventName, any()) }
    }
}
