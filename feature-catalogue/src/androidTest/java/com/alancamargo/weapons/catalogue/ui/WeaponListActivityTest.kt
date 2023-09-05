package com.alancamargo.weapons.catalogue.ui

import com.alancamargo.weapons.catalogue.ui.robots.given
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.resources.ResourcesHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class WeaponListActivityTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockAdLoader: AdLoader

    @Inject
    lateinit var mockResourcesHelper: ResourcesHelper

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun shouldLoadBannerAds() {
        given {
            launchWithAllWeapons()
        } then {
            loadBannerAds()
        }
    }

    @Test
    fun withAllWeapons_shouldDisplayCorrectNumberOfItems() {
        given {
            launchWithAllWeapons()
        } then {
            itemCountIs(expectedCount = 3)
        }
    }

    @Test
    fun withWeaponsGroupedByType_shouldDisplayHeader() {
        given {
            launchWithWeaponsGroupedByType()
        } then {
            headerIsDisplayed(header = "Melee")
        }
    }

    @Test
    fun withWeaponsGroupedByType_shouldDisplayCorrectNumberOfItems() {
        given {
            launchWithWeaponsGroupedByType()
        } then {
            itemCountIs(expectedCount = 1)
        }
    }

    @Test
    fun clickWeapon_shouldShowWeaponDetails() {
        val weaponName = "Trench club"

        given {
            launchWithAllWeapons()
        } withAction {
            clickWeapon(weaponName)
        } then {
            showWeaponDetails(weaponName)
        }
    }
}
