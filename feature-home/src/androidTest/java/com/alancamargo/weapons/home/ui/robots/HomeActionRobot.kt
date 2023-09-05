package com.alancamargo.weapons.home.ui.robots

import com.alancamargo.weapons.core.test.ui.openMenu
import com.alancamargo.weapons.core.test.ui.performClick
import com.alancamargo.weapons.core.test.ui.typeText
import com.alancamargo.weapons.home.R
import com.alancamargo.weapons.home.ui.HomeActivityTest

internal class HomeActionRobot(private val testSuite: HomeActivityTest) {

    fun clickAllWeapons() {
        performClick(R.id.btAllWeapons)
    }

    fun clickByName() {
        performClick(text = "By name")
    }

    fun clickByCountry() {
        performClick(text = "By country")
    }

    fun clickByYear() {
        performClick(text = "By year")
    }

    fun clickByCalibre() {
        performClick(text = "By calibre")
    }

    fun clickByType() {
        performClick(text = "By type")
    }

    fun clickByMake() {
        performClick(text = "By make")
    }

    fun clickAbout() {
        openMenu()
        performClick(text = "About")
    }

    fun clickPrivacyPolicy() {
        openMenu()
        performClick(text = "Privacy policy")
    }

    fun typeWeaponName(weaponName: String) {
        typeText(R.id.edtSearch, weaponName)
    }

    fun clickOk() {
        performClick(R.id.btOk)
    }

    infix fun then(
        assertion: HomeAssertionRobot.() -> Unit
    ): HomeAssertionRobot = HomeAssertionRobot(testSuite).apply(assertion)
}
