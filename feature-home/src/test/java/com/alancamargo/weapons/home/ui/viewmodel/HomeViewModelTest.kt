package com.alancamargo.weapons.home.ui.viewmodel

import app.cash.turbine.test
import com.alancamargo.weapons.common.ui.WeaponQuery
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val viewModel = HomeViewModel(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `getQueryTypes should set correct state`() = runTest {
        // WHEN
        viewModel.getQueryTypes()

        // THEN
        val expected = HomeViewState(queryTypes = WeaponQueryType.values().toList())
        viewModel.state.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `when query type is ALL onQueryItemClicked should send NavigateToWeaponsList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.ALL)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponsList(WeaponQuery.All)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_CALIBRE onQueryItemClicked should send NavigateToWeaponsList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_CALIBRE)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByCalibre)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_COUNTRY onQueryItemClicked should send NavigateToWeaponsList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_COUNTRY)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByCountry)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_MANUFACTURER onQueryItemClicked should send NavigateToWeaponsList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_MANUFACTURER)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByManufacturer)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_NAME onQueryItemClicked should send ShowWeaponSearchDialogue action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_NAME)

            // THEN
            val expected = HomeViewAction.ShowWeaponSearchDialogue
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_TYPE onQueryItemClicked should send NavigateToWeaponsList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_TYPE)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByType)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_YEAR onQueryItemClicked should send NavigateToWeaponsList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_YEAR)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByYear)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `onAppInfoClicked should send ShowAppInfo action`() = runTest {
        // WHEN
        viewModel.onAppInfoClicked()

        // THEN
        val expected = HomeViewAction.ShowAppInfo
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `onPrivacyPolicyClicked should send ShowPrivacyPolicy action`() = runTest {
        // WHEN
        viewModel.onPrivacyPolicyClicked()

        // THEN
        val expected = HomeViewAction.ShowPrivacyPolicy
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }
}
