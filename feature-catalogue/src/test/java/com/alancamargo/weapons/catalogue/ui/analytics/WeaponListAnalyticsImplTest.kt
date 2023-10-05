package com.alancamargo.weapons.catalogue.ui.analytics

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

private const val SCREEN_NAME = "weapon-list"

private const val BUTTON_WEAPON = "weapon"
private const val BUTTON_BACK = "back"
private const val BUTTON_NATIVE_BACK = "native-back"

private const val PARAM_WEAPON_NAME = "weapon-name"

class WeaponListAnalyticsImplTest {

    private val mockAnalyticsManager = mockk<AnalyticsManager>(relaxed = true)
    private val analytics = WeaponListAnalyticsImpl(mockAnalyticsManager)

    @Test
    fun `trackScreenViewed should track event correctly`() {
        // WHEN
        analytics.trackScreenViewed()

        // THEN
        verify { mockAnalyticsManager.trackScreenViewed(SCREEN_NAME) }
    }

    @Test
    fun `trackWeaponClicked should track event correctly`() {
        // GIVEN
        val weaponName = "weapon"

        // WHEN
        analytics.trackWeaponClicked(weaponName)

        // THEN
        val properties = mapOf(PARAM_WEAPON_NAME to weaponName)
        verify {
            mockAnalyticsManager.trackButtonClicked(
                screenName = SCREEN_NAME,
                buttonName = BUTTON_WEAPON,
                properties = properties
            )
        }
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
