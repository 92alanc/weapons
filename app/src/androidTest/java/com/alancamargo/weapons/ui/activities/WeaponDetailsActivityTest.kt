package com.alancamargo.weapons.ui.activities

import com.alancamargo.weapons.ui.activities.robots.weaponDetailsActivity
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class WeaponDetailsActivityTest {

    @Before
    fun setUp() {
        initialiseKoin()
    }

    @Test
    fun shouldDisplayCorrectName() {
        weaponDetailsActivity {
        } assert {
            weaponNameIsCorrect()
        }
    }

    @Test
    fun shouldDisplayCorrectWeaponType() {
        weaponDetailsActivity {
        } assert {
            weaponTypeIsCorrect()
        }
    }

    @Test
    fun shouldLoadAds() {
        weaponDetailsActivity {
        } assert {
            adsAreLoaded()
        }
    }

    private fun initialiseKoin() {
        loadKoinModules(module {
            single<AdLoader>(override = true) { mockk(relaxed = true) }
        })
    }

}