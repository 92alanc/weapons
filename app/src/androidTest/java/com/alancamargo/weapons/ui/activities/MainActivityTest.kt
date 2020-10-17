package com.alancamargo.weapons.ui.activities

import com.alancamargo.weapons.ui.activities.robots.mainActivity
import com.alancamargo.weapons.ui.navigation.WeaponListActivityNavigation
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivityTest {

    @Before
    fun setUp() {
        initialiseKoin()
    }

    @Test
    fun clickAllWeapons_shouldLaunchWeaponListActivity() {
        mainActivity {
        } clickAllWeapons {
            weaponListActivityIsLaunched()
        }
    }

    @Test
    fun clickByName_shouldShowNameSearchDialogue() {
        mainActivity {
        } clickByName {
            nameSearchDialogueIsDisplayed()
        }
    }

    @Test
    fun clickByCountry_shouldLaunchWeaponListActivity() {
        mainActivity {
        } clickByCountry {
            weaponListActivityIsLaunched()
        }
    }

    @Test
    fun clickByYear_shouldLaunchWeaponListActivity() {
        mainActivity {
        } clickByYear {
            weaponListActivityIsLaunched()
        }
    }

    @Test
    fun clickByType_shouldLaunchWeaponListActivity() {
        mainActivity {
        } clickByType {
            weaponListActivityIsLaunched()
        }
    }

    @Test
    fun clickByCalibre_shouldLaunchWeaponListActivity() {
        mainActivity {
        } clickByCalibre {
            weaponListActivityIsLaunched()
        }
    }

    @Test
    fun clickByManufacturer_shouldWeaponCountryListActivity() {
        mainActivity {
        } clickByManufacturer {
            weaponListActivityIsLaunched()
        }
    }

    private fun initialiseKoin() {
        loadKoinModules(module {
            single<WeaponListActivityNavigation>(override = true) { mockk(relaxed = true) }
        })
    }

}
