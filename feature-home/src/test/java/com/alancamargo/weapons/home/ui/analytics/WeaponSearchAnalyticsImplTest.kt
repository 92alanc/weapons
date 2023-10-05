package com.alancamargo.weapons.home.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

private const val SCREEN_NAME = "weapon-search"
private const val BUTTON_SEARCH = "search"
private const val PARAM_SEARCH_TERM = "search-term"
private const val EVENT_DIALOGUE_CANCELLED = "dialogue-cancelled"

class WeaponSearchAnalyticsImplTest {

    private val mockAnalyticsManager = mockk<AnalyticsManager>(relaxed = true)
    private val analytics = WeaponSearchAnalyticsImpl(mockAnalyticsManager)

    @Test
    fun `trackDialogueViewed should track event correctly`() {
        // WHEN
        analytics.trackDialogueViewed()

        // THEN
        verify { mockAnalyticsManager.trackScreenViewed(SCREEN_NAME) }
    }

    @Test
    fun `trackWeaponSearched should track event correctly`() {
        // GIVEN
        val searchTerm = "lee-enfield"

        // WHEN
        analytics.trackWeaponSearched(searchTerm)

        // THEN
        val properties = mapOf(PARAM_SEARCH_TERM to searchTerm)
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_SEARCH,
                properties = properties
            )
        }
    }

    @Test
    fun `trackDialogueCancelled should track event correctly`() {
        // WHEN
        analytics.trackDialogueCancelled()

        // THEN
        verify {
            mockAnalyticsManager.trackEvent(
                screenName = SCREEN_NAME,
                eventName = EVENT_DIALOGUE_CANCELLED
            )
        }
    }
}
