package com.alancamargo.weapons.webview.ui

import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.webview.ui.robots.given
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class WebViewActivityTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockAdLoader: AdLoader

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun shouldLoadBannerAds() {
        given {
            launch()
        } then {
            loadBannerAds()
        }
    }
}
