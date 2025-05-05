package com.alancamargo.weapons.home.ui.viewmodel.weaponsearch

import app.cash.turbine.test
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.home.ui.analytics.WeaponSearchAnalytics
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
class WeaponSearchViewModelTest {

    private val mockAnalytics = mockk<WeaponSearchAnalytics>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = WeaponSearchViewModel(
        mockAnalytics,
        testDispatcher
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `start should track dialogue view event`() {
        // When
        viewModel.start()

        // Then
        verify { mockAnalytics.trackDialogueViewed() }
    }

    @Test
    fun `onOkClicked should track button click event`() {
        // Given
        val weaponName = "Gewehr"

        // When
        viewModel.onOkClicked(weaponName)

        // Then
        verify { mockAnalytics.trackWeaponSearched(weaponName) }
    }

    @Test
    fun `onOkClicked should send NavigateToWeaponList action`() = runTest {
        // Given
        val weaponName = "Lee-Enfield"

        // When
        viewModel.onOkClicked(weaponName)

        // Then
        val query = UiWeaponQuery.ByName(weaponName)
        val expected = WeaponSearchViewAction.NavigateToWeaponList(query)
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `onCancel should track dialogue cancel event`() {
        // When
        viewModel.onCancel()

        // Then
        verify { mockAnalytics.trackDialogueCancelled() }
    }
}
