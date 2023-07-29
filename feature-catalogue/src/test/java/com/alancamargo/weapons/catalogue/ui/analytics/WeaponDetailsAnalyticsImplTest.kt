package com.alancamargo.weapons.catalogue.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

private const val SCREEN_NAME = "weapon-details"

private const val BUTTON_BACK = "back"
private const val BUTTON_NATIVE_BACK = "native-back"

class WeaponDetailsAnalyticsImplTest {

    private val mockAnalyticsManager = mockk<AnalyticsManager>(relaxed = true)
    private val analytics = WeaponDetailsAnalyticsImpl(mockAnalyticsManager)

    @Test
    fun `trackScreenViewed should track event correctly`() {
        // WHEN
        analytics.trackScreenViewed()

        // THEN
        verify { mockAnalyticsManager.trackScreenViewed(SCREEN_NAME) }
    }

    @Test
    fun `trackBackClicked should track event correctly`() {
        // WHEN
        analytics.trackBackClicked()

        // THEN
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_BACK
            )
        }
    }

    @Test
    fun `trackNativeBackClicked should track event correctly`() {
        // WHEN
        analytics.trackNativeBackClicked()

        // THEN
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_NATIVE_BACK
            )
        }
    }
}
