package com.alancamargo.weapons.catalogue.ui.robots

import com.alancamargo.weapons.catalogue.ui.WeaponListActivityTest
import com.alancamargo.weapons.core.test.ui.performClick

internal class WeaponListActionRobot(private val testSuite: WeaponListActivityTest) {

    fun clickWeapon(weaponName: String) {
        performClick(weaponName)
    }

    infix fun then(assertion: WeaponListAssertionRobot.() -> Unit): WeaponListAssertionRobot {
        return WeaponListAssertionRobot(testSuite).apply(assertion)
    }
}
