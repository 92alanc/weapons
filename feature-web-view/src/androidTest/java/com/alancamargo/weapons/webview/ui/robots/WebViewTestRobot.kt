package com.alancamargo.weapons.webview.ui.robots

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.weapons.webview.R
import com.alancamargo.weapons.webview.ui.WebViewActivity
import com.alancamargo.weapons.webview.ui.WebViewActivityTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

internal fun WebViewActivityTest.given(block: WebViewTestRobot.() -> Unit): WebViewTestRobot {
    return WebViewTestRobot(testSuite = this).apply(block)
}

internal class WebViewTestRobot(private val testSuite: WebViewActivityTest) {

    fun launch() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val args = WebViewActivity.Args(
            titleRes = R.string.refresh,
            url = "https://test.com"
        )

        val intent = WebViewActivity.getIntent(
            context = context,
            args = args
        )

        ActivityScenario.launch<WebViewActivity>(intent)
        runBlocking { delay(timeMillis = 50) }
    }

    infix fun then(assertion: WebViewAssertionRobot.() -> Unit): WebViewAssertionRobot {
        return WebViewAssertionRobot(testSuite).apply(assertion)
    }
}
