package com.alancamargo.weapons.home.ui.robots

import androidx.test.core.app.ActivityScenario
import com.alancamargo.weapons.home.ui.HomeActivity
import com.alancamargo.weapons.home.ui.HomeActivityTest
import io.mockk.every
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private const val KEY_SHOW_DISCLAIMER = "show-disclaimer"

internal fun HomeActivityTest.given(
    block: HomeTestRobot.() -> Unit
): HomeTestRobot = HomeTestRobot(testSuite = this).apply(block)

internal class HomeTestRobot(private val testSuite: HomeActivityTest) {

    fun launchOnFirstAccess() {
        setIsFirstAccess(isFirstAccess = true)
        launch()
    }

    fun launchAfterFirstAccess() {
        setIsFirstAccess(isFirstAccess = false)
        launch()
    }

    infix fun withAction(action: HomeActionRobot.() -> Unit): HomeActionRobot {
        return HomeActionRobot(testSuite).apply(action)
    }

    infix fun then(assertion: HomeAssertionRobot.() -> Unit): HomeAssertionRobot {
        return HomeAssertionRobot(testSuite).apply(assertion)
    }

    private fun launch() {
        ActivityScenario.launch(HomeActivity::class.java)
        runBlocking { delay(timeMillis = 50) }
    }

    private fun setIsFirstAccess(isFirstAccess: Boolean) {
        every {
            testSuite.mockPreferencesManager.getBoolean(KEY_SHOW_DISCLAIMER, defaultValue = true)
        } returns isFirstAccess
    }
}
