package com.alancamargo.weapons.home.ui

import com.alancamargo.weapons.core.consent.UserConsentManager
import com.alancamargo.weapons.core.design.dialogue.DialogueHelper
import com.alancamargo.weapons.core.preferences.PreferencesManager
import com.alancamargo.weapons.home.ui.robots.given
import com.alancamargo.weapons.navigation.WeaponListActivityNavigation
import com.alancamargo.weapons.navigation.WebViewActivityNavigation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class HomeActivityTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockUserConsentManager: UserConsentManager

    @Inject
    lateinit var mockWeaponListActivityNavigation: WeaponListActivityNavigation

    @Inject
    lateinit var mockWebViewActivityNavigation: WebViewActivityNavigation

    @Inject
    lateinit var mockDialogueHelper: DialogueHelper

    @Inject
    lateinit var mockPreferencesManager: PreferencesManager

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun shouldGetUserConsent() {
        given {
            launchAfterFirstAccess()
        } then {
            getUserConsent()
        }
    }

    @Test
    fun onFirstAccess_shouldShowFirstAccessInformation() {
        given {
            launchOnFirstAccess()
        } then {
            showFirstAccessInformation()
        }
    }

    @Test
    fun afterFirstAccess_shouldNotShowFirstAccessInformation() {
        given {
            launchAfterFirstAccess()
        } then {
            doNotShowFirstAccessInformation()
        }
    }

    @Test
    fun clickAllWeapons_shouldNavigateToWeaponList() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickAllWeapons()
        } then {
            navigateToWeaponList()
        }
    }

    @Test
    fun clickByName_shouldShowSearchDialogue() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickByName()
        } then {
            showSearchDialogue()
        }
    }

    @Test
    fun clickOk_shouldNavigateToWeaponList() {
        val weaponName = "Gewehr 98"

        given {
            launchAfterFirstAccess()
        } withAction {
            clickByName()
            typeWeaponName(weaponName)
            clickOk()
        } then {
            navigateToWeaponList(weaponName)
        }
    }

    @Test
    fun clickByCountry_shouldNavigateToWeaponList() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickByCountry()
        } then {
            navigateToWeaponList()
        }
    }

    @Test
    fun clickByYear_shouldNavigateToWeaponList() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickByYear()
        } then {
            navigateToWeaponList()
        }
    }

    @Test
    fun clickByCalibre_shouldNavigateToWeaponList() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickByCalibre()
        } then {
            navigateToWeaponList()
        }
    }

    @Test
    fun clickByType_shouldNavigateToWeaponList() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickByType()
        } then {
            navigateToWeaponList()
        }
    }

    @Test
    fun clickByMake_shouldNavigateToWeaponList() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickByMake()
        } then {
            navigateToWeaponList()
        }
    }

    @Test
    fun clickAbout_shouldShowAppInfoDialogue() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickAbout()
        } then {
            showAppInfoDialogue()
        }
    }

    @Test
    fun clickPrivacyPolicy_shouldNavigateToWebView() {
        given {
            launchAfterFirstAccess()
        } withAction {
            clickPrivacyPolicy()
        } then {
            navigateToWebView()
        }
    }
}
