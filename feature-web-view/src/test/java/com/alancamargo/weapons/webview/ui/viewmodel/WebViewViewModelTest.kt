package com.alancamargo.weapons.webview.ui.viewmodel

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WebViewViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val viewModel = WebViewViewModel(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
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
    fun `onBackClicked should send Finish action`() = runTest {
        // WHEN
        viewModel.onBackClicked()

        // THEN
        viewModel.action.test {
            assertThat(awaitItem()).isEqualTo(WebViewViewAction.Finish)
        }
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
