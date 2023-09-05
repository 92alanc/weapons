package com.alancamargo.weapons.catalogue.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.ui.WeaponListActivityTest
import com.alancamargo.weapons.core.test.ui.assertTextIsDisplayed
import com.alancamargo.weapons.core.test.ui.withRecyclerViewItemCount
import io.mockk.verify

internal class WeaponListAssertionRobot(private val testSuite: WeaponListActivityTest) {

    fun loadBannerAds() {
        verify { testSuite.mockAdLoader.loadBannerAds(target = any()) }
    }

    fun showWeaponDetails(weaponName: String) {
        assertTextIsDisplayed(weaponName)
    }

    fun headerIsDisplayed(header: String) {
        assertTextIsDisplayed(header)
    }

    fun itemCountIs(expectedCount: Int) {
        onView(withId(R.id.recyclerView)).check(withRecyclerViewItemCount(expectedCount))
    }
}
