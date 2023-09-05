package com.alancamargo.weapons.home.ui.robots

import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.test.ui.assertTextIsDisplayed
import com.alancamargo.weapons.home.ui.HomeActivityTest
import io.mockk.verify

internal class HomeAssertionRobot(private val testSuite: HomeActivityTest) {

    fun loadBannerAds() {
        verify { testSuite.mockAdLoader.loadBannerAds(target = any()) }
    }

    fun navigateToWeaponList() {
        verify {
            testSuite.mockWeaponListActivityNavigation.startActivity(
                context = any(),
                query = any()
            )
        }
    }

    fun navigateToWeaponList(weaponName: String) {
        verify {
            testSuite.mockWeaponListActivityNavigation.startActivity(
                context = any(),
                query = UiWeaponQuery.ByName(weaponName)
            )
        }
    }

    fun showSearchDialogue() {
        assertTextIsDisplayed(text = "Find weapons by name")
    }

    fun navigateToWebView() {
        verify {
            testSuite.mockWebViewActivityNavigation.startActivity(
                context = any(),
                titleRes = any(),
                url = any()
            )
        }
    }

    fun showAppInfoDialogue() {
        verify {
            testSuite.mockDialogueHelper.showDialogue(
                context = any(),
                title = any(),
                messageRes = any(),
                onDismiss = null
            )
        }
    }

    fun showDisclaimerDialogue() {
        verify {
            testSuite.mockDialogueHelper.showDialogue(
                context = any(),
                title = any(),
                messageRes = any(),
                onDismiss = any()
            )
        }
    }

    fun doNotShowDisclaimerDialogue() {
        verify(exactly = 0) {
            testSuite.mockDialogueHelper.showDialogue(
                context = any(),
                title = any(),
                messageRes = any(),
                onDismiss = any()
            )
        }
    }
}
