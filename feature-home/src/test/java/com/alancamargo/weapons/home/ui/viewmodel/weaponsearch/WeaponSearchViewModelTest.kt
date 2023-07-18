package com.alancamargo.weapons.home.ui.viewmodel.weaponsearch

import app.cash.turbine.test
import com.alancamargo.weapons.common.ui.WeaponQuery
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeaponSearchViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val viewModel = WeaponSearchViewModel(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `onOkClicked should send NavigateToWeaponList action`() = runTest {
        // GIVEN
        val weaponName = "Lee-Enfield"

        // WHEN
        viewModel.onOkClicked(weaponName)

        // THEN
        val query = WeaponQuery.ByName(weaponName)
        val expected = WeaponSearchViewAction.NavigateToWeaponList(query)
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }
}
