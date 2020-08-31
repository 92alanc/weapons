package com.alancamargo.weapons.ui.activities

import androidx.test.espresso.intent.Intents
import com.alancamargo.weapons.ui.activities.robots.mainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
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

}