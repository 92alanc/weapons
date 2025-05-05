package com.alancamargo.weapons.home.ui.viewmodel.home

import app.cash.turbine.test
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.preferences.PreferencesManager
import com.alancamargo.weapons.core.remoteconfig.RemoteConfigManager
import com.alancamargo.weapons.home.ui.analytics.HomeAnalytics
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
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

private const val KEY_FIRST_ACCESS_INFO = "first_access_info"
private const val KEY_SHOW_FIRST_ACCESS_INFORMATION = "show-first-access-information"

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val mockAnalytics = mockk<HomeAnalytics>(relaxed = true)
    private val mockPreferencesManager = mockk<PreferencesManager>(relaxed = true)
    private val mockRemoteConfigManager = mockk<RemoteConfigManager>()
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = HomeViewModel(
        mockAnalytics,
        mockPreferencesManager,
        mockRemoteConfigManager,
        testDispatcher,
        viewActionDelay = 0
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
    fun `start should send ShowFirstAccessInformation action`() = runTest {
        // Given
        every {
            mockPreferencesManager.getBoolean(
                KEY_SHOW_FIRST_ACCESS_INFORMATION,
                defaultValue = true
            )
        } returns true

        val text = "This app is not a game."
        coEvery {
            mockRemoteConfigManager.getString(KEY_FIRST_ACCESS_INFO)
        } returns text

        // When
        viewModel.start()

        // Then
        val expected = HomeViewAction.ShowFirstAccessInformation(text)
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `start should set correct state`() = runTest {
        // When
        viewModel.start()

        // Then
        val expected = HomeViewState(queryTypes = WeaponQueryType.entries.toList())
        viewModel.state.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `onDisclaimerDismissed should track button click event`() {
        // When
        viewModel.onDisclaimerDismissed()

        // Then
        verify { mockAnalytics.trackFirstAccessInformationDismissed() }
    }

    @Test
    fun `onDisclaimerDismissed should set value on preferences manager`() {
        // When
        viewModel.onDisclaimerDismissed()

        // Then
        verify { mockPreferencesManager.setBoolean(KEY_SHOW_FIRST_ACCESS_INFORMATION, value = false) }
    }

    @Test
    fun `onAllWeaponsClicked should track button click event`() {
        // When
        viewModel.onAllWeaponsClicked()

        // Then
        verify { mockAnalytics.trackAllWeaponsClicked() }
    }

    @Test
    fun `onAllWeaponsClicked should send NavigateToWeaponList action`() {
        runTest {
            // When
            viewModel.onAllWeaponsClicked()

            // Then
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.All)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_CALIBRE onQueryItemClicked should track button click event`() {
        // When
        viewModel.onQueryItemClicked(WeaponQueryType.BY_CALIBRE)

        // Then
        verify { mockAnalytics.trackGroupByCalibreClicked() }
    }

    @Test
    fun `when query type is BY_CALIBRE onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // When
            viewModel.onQueryItemClicked(WeaponQueryType.BY_CALIBRE)

            // Then
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCalibre)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_COUNTRY onQueryItemClicked should track button click event`() {
        // When
        viewModel.onQueryItemClicked(WeaponQueryType.BY_COUNTRY)

        // Then
        verify { mockAnalytics.trackGroupByCountryClicked() }
    }

    @Test
    fun `when query type is BY_COUNTRY onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // When
            viewModel.onQueryItemClicked(WeaponQueryType.BY_COUNTRY)

            // Then
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCountry)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_MAKE onQueryItemClicked should track button click event`() {
        // When
        viewModel.onQueryItemClicked(WeaponQueryType.BY_MAKE)

        // Then
        verify { mockAnalytics.trackGroupByMakeClicked() }
    }

    @Test
    fun `when query type is BY_MAKE onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // When
            viewModel.onQueryItemClicked(WeaponQueryType.BY_MAKE)

            // Then
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByMake)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_NAME onQueryItemClicked should track button click event`() {
        // When
        viewModel.onQueryItemClicked(WeaponQueryType.BY_NAME)

        // Then
        verify { mockAnalytics.trackGroupByNameClicked() }
    }

    @Test
    fun `when query type is BY_NAME onQueryItemClicked should send ShowWeaponSearchDialogue action`() {
        runTest {
            // When
            viewModel.onQueryItemClicked(WeaponQueryType.BY_NAME)

            // Then
            val expected = HomeViewAction.ShowWeaponSearchDialogue
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_TYPE onQueryItemClicked should track button click event`() {
        // When
        viewModel.onQueryItemClicked(WeaponQueryType.BY_TYPE)

        // Then
        verify { mockAnalytics.trackGroupByTypeClicked() }
    }

    @Test
    fun `when query type is BY_TYPE onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // When
            viewModel.onQueryItemClicked(WeaponQueryType.BY_TYPE)

            // Then
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByType)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `when query type is BY_YEAR onQueryItemClicked should track button click event`() {
        // When
        viewModel.onQueryItemClicked(WeaponQueryType.BY_YEAR)

        // Then
        verify { mockAnalytics.trackGroupByYearClicked() }
    }

    @Test
    fun `when query type is BY_YEAR onQueryItemClicked should send NavigateToWeaponList action`() {
        runTest {
            // When
            viewModel.onQueryItemClicked(WeaponQueryType.BY_YEAR)

            // Then
            val expected = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByYear)
            viewModel.action.test {
                val actual = awaitItem()
                assertThat(actual).isEqualTo(expected)
            }
        }
    }

    @Test
    fun `onAppInfoClicked should track button click event`() {
        // When
        viewModel.onAppInfoClicked()

        // Then
        verify { mockAnalytics.trackAppInfoClicked() }
    }

    @Test
    fun `onAppInfoClicked should send ShowAppInfo action`() = runTest {
        // When
        viewModel.onAppInfoClicked()

        // Then
        val expected = HomeViewAction.ShowAppInfo
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `onPrivacyPolicyClicked should track button click event`() {
        // When
        viewModel.onPrivacyPolicyClicked()

        // Then
        verify { mockAnalytics.trackPrivacyPolicyClicked() }
    }

    @Test
    fun `onPrivacyPolicyClicked should send ShowPrivacyPolicy action`() = runTest {
        // When
        viewModel.onPrivacyPolicyClicked()

        // Then
        val expected = HomeViewAction.ShowPrivacyPolicy(url = "https://pastebin.com/raw/Krd7c6aJ")
        viewModel.action.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
        }
    }
}
