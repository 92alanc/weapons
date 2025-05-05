package com.alancamargo.weapons.home.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

private const val SCREEN_NAME = "home"

private const val BUTTON_ALL_WEAPONS = "all-weapons"
private const val BUTTON_BY_NAME = "group-by-name"
private const val BUTTON_BY_COUNTRY = "group-by-country"
private const val BUTTON_BY_YEAR = "group-by-year"
private const val BUTTON_BY_TYPE = "group-by-type"
private const val BUTTON_BY_CALIBRE = "group-by-calibre"
private const val BUTTON_BY_MAKE = "group-by-make"
private const val BUTTON_APP_INFO = "app-info"
private const val BUTTON_PRIVACY_POLICY = "privacy-policy"
private const val BUTTON_FIRST_ACCESS_INFORMATION_DISMISSED = "first-access-information-dismissed"

class HomeAnalyticsImplTest {

    private val mockAnalyticsManager = mockk<AnalyticsManager>(relaxed = true)
    private val analytics = HomeAnalyticsImpl(mockAnalyticsManager)

    @Test
    fun `trackScreenViewed should track event correctly`() {
        // When
        analytics.trackScreenViewed()

        // Then
        verify { mockAnalyticsManager.trackScreenViewed(SCREEN_NAME) }
    }

    @Test
    fun `trackFirstAccessInformationDismissed should track event correctly`() {
        // When
        analytics.trackFirstAccessInformationDismissed()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_FIRST_ACCESS_INFORMATION_DISMISSED
            )
        }
    }

    @Test
    fun `trackAllWeaponsClicked should track event correctly`() {
        // When
        analytics.trackAllWeaponsClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_ALL_WEAPONS
            )
        }
    }

    @Test
    fun `trackGroupByNameClicked should track event correctly`() {
        // When
        analytics.trackGroupByNameClicked()

        // Then
        mockAnalyticsManager.trackButtonClicked(
            screenName = SCREEN_NAME,
            buttonName = BUTTON_BY_NAME
        )
    }

    @Test
    fun `trackGroupByCountryClicked should track event correctly`() {
        // When
        analytics.trackGroupByCountryClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_BY_COUNTRY
            )
        }
    }

    @Test
    fun `trackGroupByYearClicked should track event correctly`() {
        // When
        analytics.trackGroupByYearClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_BY_YEAR
            )
        }
    }

    @Test
    fun `trackGroupByTypeClicked should track event correctly`() {
        // When
        analytics.trackGroupByTypeClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_BY_TYPE
            )
        }
    }

    @Test
    fun `trackGroupByCalibreClicked should track event correctly`() {
        // When
        analytics.trackGroupByCalibreClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_BY_CALIBRE
            )
        }
    }

    @Test
    fun `trackGroupByMakeClicked should track event correctly`() {
        // When
        analytics.trackGroupByMakeClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_BY_MAKE
            )
        }
    }

    @Test
    fun `trackAppInfoClicked should track event correctly`() {
        // When
        analytics.trackAppInfoClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_APP_INFO
            )
        }
    }

    @Test
    fun `trackPrivacyPolicyClicked should track event correctly`() {
        // When
        analytics.trackPrivacyPolicyClicked()

        // Then
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_PRIVACY_POLICY
            )
        }
    }
}
