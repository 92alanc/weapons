package com.alancamargo.weapons.webview.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

private const val SCREEN_NAME = "web-view"

private const val BUTTON_REFRESH = "refresh"
private const val BUTTON_BACK = "back"

class WebViewAnalyticsImplTest {

    private val mockAnalyticsManager = mockk<AnalyticsManager>(relaxed = true)
    private val analytics = WebViewAnalyticsImpl(mockAnalyticsManager)

    @Test
    fun `trackScreenViewed should track event correctly`() {
        // When
        analytics.trackScreenViewed()

        // Then
        verify { mockAnalyticsManager.trackScreenViewed(SCREEN_NAME) }
    }

    @Test
    fun `trackRefreshClicked should track event correctly`() {
        // When
        analytics.trackRefreshClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_REFRESH
            )
        }
    }

    @Test
    fun `trackBackClicked should track event correctly`() {
        // When
        analytics.trackBackClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_BACK
            )
        }
    }
}
