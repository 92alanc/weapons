package com.alancamargo.weapons.webview.ui.robots

import com.alancamargo.weapons.webview.ui.WebViewActivityTest
import io.mockk.verify

internal class WebViewAssertionRobot(private val testSuite: WebViewActivityTest) {

    fun loadBannerAds() {
        verify { testSuite.mockAdLoader.loadBannerAds(target = any()) }
    }
}
