package com.alancamargo.weapons.home.ui.viewmodel.home

import app.cash.turbine.test
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.preferences.PreferencesManager
import com.alancamargo.weapons.home.ui.analytics.HomeAnalytics
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

private const val KEY_SHOW_FIRST_ACCESS_INFORMATION = "show-first-access-information"

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val mockAnalytics = mockk<HomeAnalytics>(relaxed = true)
    private val mockPreferencesManager = mockk<PreferencesManager>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = HomeViewModel(
        mockAnalytics,
        mockPreferencesManager,
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
    fun `start should send ShowFirstAccessInformation action`() = runTest {
        // GIVEN
        every {
            mockPreferencesManager.getBoolean(
                KEY_SHOW_FIRST_ACCESS_INFORMATION,
                defaultValue = true
            )
        } returns true

        // WHEN
        viewModel.start()

        // THEN
        val expected = HomeViewAction.ShowFirstAccessInformation
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `start should set correct state`() = runTest {
        // WHEN
        viewModel.start()

        // THEN
        val expected = HomeViewState(queryTypes = WeaponQueryType.values().toList())
        viewModel.state.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `onDisclaimerDismissed should track button click event`() {
        // WHEN
        viewModel.onDisclaimerDismissed()

        // THEN
        verify { mockAnalytics.trackFirstAccessInformationDismissed() }
    }

    @Test
    fun `onDisclaimerDismissed should set value on preferences manager`() {
        // WHEN
        viewModel.onDisclaimerDismissed()

        // THEN
        verify { mockPreferencesManager.setBoolean(KEY_SHOW_FIRST_ACCESS_INFORMATION, value = false) }
    }

    @Test
    fun `onAllWeaponsClicked should track button click event`() {
        // WHEN
        viewModel.onAllWeaponsClicked()

        // THEN
        verify { mockAnalytics.trackAllWeaponsClicked() }
    }

    @Test
    fun `onAllWeaponsClicked should send NavigateToWeaponList action`() {
        runTest {
            // WHEN
            viewModel.onAllWeaponsClicked()

            // THEN
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.All)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_CALIBRE onQueryItemClicked should track button click event`() {
        // WHEN
        viewModel.onQueryItemClicked(WeaponQueryType.BY_CALIBRE)

        // THEN
        verify { mockAnalytics.trackGroupByCalibreClicked() }
    }

    @Test
    fun `when query type is BY_CALIBRE onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_CALIBRE)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCalibre)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_COUNTRY onQueryItemClicked should track button click event`() {
        // WHEN
        viewModel.onQueryItemClicked(WeaponQueryType.BY_COUNTRY)

        // THEN
        verify { mockAnalytics.trackGroupByCountryClicked() }
    }

    @Test
    fun `when query type is BY_COUNTRY onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_COUNTRY)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCountry)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_MAKE onQueryItemClicked should track button click event`() {
        // WHEN
        viewModel.onQueryItemClicked(WeaponQueryType.BY_MAKE)

        // THEN
        verify { mockAnalytics.trackGroupByMakeClicked() }
    }

    @Test
    fun `when query type is BY_MAKE onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_MAKE)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByMake)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_NAME onQueryItemClicked should track button click event`() {
        // WHEN
        viewModel.onQueryItemClicked(WeaponQueryType.BY_NAME)

        // THEN
        verify { mockAnalytics.trackGroupByNameClicked() }
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
    fun `when query type is BY_TYPE onQueryItemClicked should track button click event`() {
        // WHEN
        viewModel.onQueryItemClicked(WeaponQueryType.BY_TYPE)

        // THEN
        verify { mockAnalytics.trackGroupByTypeClicked() }
    }

    @Test
    fun `when query type is BY_TYPE onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_TYPE)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByType)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_YEAR onQueryItemClicked should track button click event`() {
        // WHEN
        viewModel.onQueryItemClicked(WeaponQueryType.BY_YEAR)

        // THEN
        verify { mockAnalytics.trackGroupByYearClicked() }
    }

    @Test
    fun `when query type is BY_YEAR onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // WHEN
            viewModel.onQueryItemClicked(WeaponQueryType.BY_YEAR)

            // THEN
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByYear)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `onAppInfoClicked should track button click event`() {
        // WHEN
        viewModel.onAppInfoClicked()

        // THEN
        verify { mockAnalytics.trackAppInfoClicked() }
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
    fun `onPrivacyPolicyClicked should track button click event`() {
        // WHEN
        viewModel.onPrivacyPolicyClicked()

        // THEN
        verify { mockAnalytics.trackPrivacyPolicyClicked() }
    }

    @Test
    fun `onPrivacyPolicyClicked should send ShowPrivacyPolicy action`() = runTest {
        // WHEN
        viewModel.onPrivacyPolicyClicked()

        // THEN
        val expected = HomeViewAction.ShowPrivacyPolicy(url = "https://pastebin.com/raw/Krd7c6aJ")
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }
}
