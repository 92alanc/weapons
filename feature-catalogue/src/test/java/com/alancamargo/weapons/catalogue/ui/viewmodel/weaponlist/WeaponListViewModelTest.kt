package com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist

import app.cash.turbine.test
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.model.WeaponQuery
import com.alancamargo.weapons.catalogue.domain.usecase.GetWeaponsUseCase
import com.alancamargo.weapons.catalogue.testtools.stubUiWeapon
import com.alancamargo.weapons.catalogue.testtools.stubWeaponListMap
import com.alancamargo.weapons.catalogue.testtools.stubWeaponListWithHeaderMap
import com.alancamargo.weapons.catalogue.ui.analytics.WeaponListAnalytics
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.log.Logger
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeaponListViewModelTest {

    private val mockGetWeaponsUseCase = mockk<GetWeaponsUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val mockResourcesHelper = mockk<ResourcesHelper>()
    private val mockAnalytics = mockk<WeaponListAnalytics>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = WeaponListViewModel(
        mockGetWeaponsUseCase,
        mockLogger,
        mockResourcesHelper,
        mockAnalytics,
        testDispatcher
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { mockResourcesHelper.getString(stringId = any()) } returns "string"
    }

    @Test
    fun `handleQuery should track screen view event`() {
        // GIVEN
        every { mockGetWeaponsUseCase(WeaponQuery.All) } returns flowOf(WeaponListResult.Error)

        // WHEN
        viewModel.handleQuery(UiWeaponQuery.All)

        // THEN
        verify { mockAnalytics.trackScreenViewed() }
    }

    @Test
    fun `with no results handleQuery should set correct view state`() = runTest {
        // GIVEN
        val result = WeaponListResult.Success(emptyMap())
        every { mockGetWeaponsUseCase(WeaponQuery.All) } returns flowOf(result)

        // WHEN
        viewModel.handleQuery(UiWeaponQuery.All)

        // THEN
        viewModel.state.test {
            val initialState = WeaponListViewState()
            assertThat(awaitItem()).isEqualTo(initialState)
            val loading = initialState.onLoading()
            assertThat(awaitItem()).isEqualTo(loading)
            val empty = loading.onEmptyState()
            assertThat(awaitItem()).isEqualTo(empty)
            val finalState = empty.onFinishedLoading()
            assertThat(awaitItem()).isEqualTo(finalState)
        }
    }

    @Test
    fun `with weapon list handleQuery should set correct view state`() = runTest {
        // GIVEN
        val result = WeaponListResult.Success(stubWeaponListMap())
        every { mockGetWeaponsUseCase(WeaponQuery.All) } returns flowOf(result)

        // WHEN
        viewModel.handleQuery(UiWeaponQuery.All)

        // THEN
        viewModel.state.test {
            val initialState = WeaponListViewState()
            assertThat(awaitItem()).isEqualTo(initialState)
            val loading = initialState.onLoading()
            assertThat(awaitItem()).isEqualTo(loading)
            awaitItem() // Results received
            awaitItem() // Final state
        }
    }

    @Test
    fun `with weapon list with header handleQuery should set correct view state`() = runTest {
        // GIVEN
        val result = WeaponListResult.Success(stubWeaponListWithHeaderMap())
        every { mockGetWeaponsUseCase(WeaponQuery.All) } returns flowOf(result)

        // WHEN
        viewModel.handleQuery(UiWeaponQuery.All)

        // THEN
        viewModel.state.test {
            val initialState = WeaponListViewState()
            assertThat(awaitItem()).isEqualTo(initialState)
            val loading = initialState.onLoading()
            assertThat(awaitItem()).isEqualTo(loading)
            awaitItem() // Results received
            awaitItem() // Final state
        }
    }

    @Test
    fun `with error handleQuery should log exception`() = runTest {
        // GIVEN
        val exception = IllegalStateException()
        every { mockGetWeaponsUseCase(WeaponQuery.All) } returns flow { throw exception }

        // WHEN
        viewModel.handleQuery(UiWeaponQuery.All)

        // THEN
        advanceUntilIdle()
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `with error handleQuery should set correct view state`() = runTest {
        // GIVEN
        val result = WeaponListResult.Error
        every { mockGetWeaponsUseCase(WeaponQuery.All) } returns flowOf(result)

        // WHEN
        viewModel.handleQuery(UiWeaponQuery.All)

        // THEN
        viewModel.state.test {
            val initialState = WeaponListViewState()
            assertThat(awaitItem()).isEqualTo(initialState)
            val loading = initialState.onLoading()
            assertThat(awaitItem()).isEqualTo(loading)
            val empty = loading.onError()
            assertThat(awaitItem()).isEqualTo(empty)
            val finalState = empty.onFinishedLoading()
            assertThat(awaitItem()).isEqualTo(finalState)
        }
    }

    @Test
    fun `onWeaponClicked should track button click event`() {
        // WHEN
        val weapon = stubUiWeapon()
        viewModel.onWeaponClicked(weapon)

        // THEN
        verify { mockAnalytics.trackWeaponClicked(weapon.name) }
    }

    @Test
    fun `onWeaponClicked should send NavigateToWeaponDetails action`() = runTest {
        // WHEN
        val weapon = stubUiWeapon()
        viewModel.onWeaponClicked(weapon)

        // THEN
        viewModel.action.test {
            val expected = WeaponListViewAction.ShowWeaponDetails(weapon)
            assertThat(awaitItem()).isEqualTo(expected)
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
            assertThat(awaitItem()).isEqualTo(WeaponListViewAction.Finish)
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
            assertThat(awaitItem()).isEqualTo(WeaponListViewAction.Finish)
        }
    }
}
