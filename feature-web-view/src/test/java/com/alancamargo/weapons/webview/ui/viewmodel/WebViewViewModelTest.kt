package com.alancamargo.weapons.webview.ui.viewmodel

import app.cash.turbine.test
import com.alancamargo.weapons.webview.ui.analytics.WebViewAnalytics
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WebViewViewModelTest {

    private val mockAnalytics = mockk<WebViewAnalytics>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = WebViewViewModel(
        mockAnalytics,
        testDispatcher
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `start should track screen view event`() {
        // WHEN
        viewModel.start()

        // THEN
        verify { mockAnalytics.trackScreenViewed() }
    }

    @Test
    fun `onRefresh should track button click event`() {
        // WHEN
        viewModel.onRefresh()

        // THEN
        verify { mockAnalytics.trackRefreshClicked() }
    }

    @Test
    fun `onRefresh should send Refresh action`() = runTest {
        // WHEN
        viewModel.onRefresh()

        // THEN
        viewModel.action.test {
            assertThat(awaitItem()).isEqualTo(WebViewViewAction.Refresh)
        }
    }

    @Test
    fun `onBackClicked should track button click event`() {
        // WHEN
        viewModel.onBackClicked()

        // THEN
        verify { mockAnalytics.trackBackClicked() }
    }

    @Test
    fun `onBackClicked should send Finish action`() = runTest {
        // WHEN
        viewModel.onBackClicked()

        // THEN
        viewModel.action.test {
            assertThat(awaitItem()).isEqualTo(WebViewViewAction.Finish)
        }
    }

    @Test
    fun `onNativeBackClicked should track button click event`() {
        // WHEN
        viewModel.onNativeBackClicked()

        // THEN
        verify { mockAnalytics.trackNativeBackClicked() }
    }

    @Test
    fun `onNativeBackClicked should send Finish action`() = runTest {
        // WHEN
        viewModel.onNativeBackClicked()

        // THEN
        viewModel.action.test {
            assertThat(awaitItem()).isEqualTo(WebViewViewAction.Finish)
        }
    }
}
