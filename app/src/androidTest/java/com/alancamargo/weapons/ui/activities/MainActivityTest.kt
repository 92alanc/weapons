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
    fun clickByCountry_shouldLaunchCountryListActivity() {
        mainActivity {
        } clickByCountry {
            countryListActivityIsLaunched()
        }
    }

    @Test
    fun clickByYear_shouldLaunchCountryListActivity() {
        mainActivity {
        } clickByYear {
            yearListActivityIsLaunched()
        }
    }

    @Test
    fun clickByType_shouldLaunchCountryListActivity() {
        mainActivity {
        } clickByType {
            typeListActivityIsLaunched()
        }
    }

    @Test
    fun clickByCalibre_shouldLaunchCountryListActivity() {
        mainActivity {
        } clickByCalibre {
            calibreListActivityIsLaunched()
        }
    }

    @Test
    fun clickByManufacturer_shouldLaunchCountryListActivity() {
        mainActivity {
        } clickByManufacturer {
            manufacturerListActivityIsLaunched()
        }
    }

}