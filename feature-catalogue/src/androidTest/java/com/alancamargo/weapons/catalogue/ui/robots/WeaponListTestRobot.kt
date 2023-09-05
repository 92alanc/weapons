package com.alancamargo.weapons.catalogue.ui.robots

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.weapons.catalogue.ui.WeaponListActivity
import com.alancamargo.weapons.catalogue.ui.WeaponListActivityTest
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

internal fun WeaponListActivityTest.given(
    block: WeaponListTestRobot.() -> Unit
): WeaponListTestRobot = WeaponListTestRobot(testSuite = this).apply(block)

internal class WeaponListTestRobot(private val testSuite: WeaponListActivityTest) {

    fun launchWithAllWeapons() {
        launch(UiWeaponQuery.All)
    }

    fun launchWithWeaponsGroupedByType() {
        launch(UiWeaponQuery.ByType)
    }

    infix fun withAction(action: WeaponListActionRobot.() -> Unit): WeaponListActionRobot {
        return WeaponListActionRobot(testSuite).apply(action)
    }

    infix fun then(assertion: WeaponListAssertionRobot.() -> Unit): WeaponListAssertionRobot {
        return WeaponListAssertionRobot(testSuite).apply(assertion)
    }

    private fun launch(query: UiWeaponQuery) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val args = WeaponListActivity.Args(query)
        val intent = WeaponListActivity.getIntent(
            context,
            args
        )

        ActivityScenario.launch<WeaponListActivity>(intent)
        runBlocking { delay(timeMillis = 100) }
    }
}
