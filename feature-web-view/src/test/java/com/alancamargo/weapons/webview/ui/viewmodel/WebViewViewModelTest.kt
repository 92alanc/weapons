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
        // When
        viewModel.start()

        // Then
        verify { mockAnalytics.trackScreenViewed() }
    }

    @Test
    fun `onRefresh should track button click event`() {
        // When
        viewModel.onRefresh()

        // Then
        verify { mockAnalytics.trackRefreshClicked() }
    }

    @Test
    fun `onRefresh should send Refresh action`() = runTest {
        // When
        viewModel.onRefresh()

        // Then
        viewModel.action.test {
            assertThat(awaitItem()).isEqualTo(WebViewViewAction.Refresh)
        }
    }

    @Test
    fun `onBackClicked should track button click event`() {
        // When
        viewModel.onBackClicked()

        // Then
        verify { mockAnalytics.trackBackClicked() }
    }

    @Test
    fun `onBackClicked should send Finish action`() = runTest {
        // When
        viewModel.onBackClicked()

        // Then
        viewModel.action.test {
            assertThat(awaitItem()).isEqualTo(WebViewViewAction.Finish)
        }
    }
}
