package com.alancamargo.weapons.catalogue.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

private const val SCREEN_NAME = "weapon-details"

class WeaponDetailsAnalyticsImplTest {

    private val mockAnalyticsManager = mockk<AnalyticsManager>(relaxed = true)
    private val analytics = WeaponDetailsAnalyticsImpl(mockAnalyticsManager)

    @Test
    fun `trackScreenViewed should track event correctly`() {
        // When
        analytics.trackScreenViewed()

        // Then
        verify { mockAnalyticsManager.trackScreenViewed(SCREEN_NAME) }
    }
}
