package com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails

import app.cash.turbine.test
import com.alancamargo.weapons.catalogue.testtools.stubUiWeapon
import com.alancamargo.weapons.catalogue.ui.analytics.WeaponDetailsAnalytics
import com.alancamargo.weapons.catalogue.ui.model.UiLabelledWeapon
import com.alancamargo.weapons.core.resources.ResourcesHelper
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

@OptIn(ExperimentalCoroutinesApi::class)
class WeaponDetailsViewModelTest {

    private val mockResourcesHelper = mockk<ResourcesHelper>()
    private val mockAnalytics = mockk<WeaponDetailsAnalytics>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = WeaponDetailsViewModel(
        mockResourcesHelper,
        mockAnalytics,
        testDispatcher
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `start should track screen view event`() {
        // GIVEN
        every {
            mockResourcesHelper.getFormattedString(
                stringId = any(),
                arg = any()
            )
        } returns "formatted string"

        // WHEN
        viewModel.start(stubUiWeapon())

        // THEN
        verify { mockAnalytics.trackScreenViewed() }
    }

    @Test
    fun `start should update view state`() = runTest {
        // GIVEN
        every {
            mockResourcesHelper.getFormattedString(
                stringId = any(),
                arg = any()
            )
        } returns "formatted string"

        // WHEN
        val weapon = stubUiWeapon()
        viewModel.start(weapon)

        // THEN
        viewModel.state.test {
            val labelledWeapon = UiLabelledWeapon(
                name = weapon.name,
                country = null,
                year = null,
                make = null,
                type = "formatted string",
                calibre = null,
                length = null,
                mass = null,
                capacity = null,
                rateOfFire = null,
                effectiveRange = null,
                photos = weapon.photos
            )

            val expected = WeaponDetailsViewState(labelledWeapon)
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
            assertThat(awaitItem()).isEqualTo(WeaponDetailsViewAction.Finish)
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
            assertThat(awaitItem()).isEqualTo(WeaponDetailsViewAction.Finish)
        }
    }
}
