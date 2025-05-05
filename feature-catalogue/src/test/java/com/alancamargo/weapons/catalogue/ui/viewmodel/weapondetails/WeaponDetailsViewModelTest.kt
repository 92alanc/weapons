package com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails

import com.alancamargo.weapons.catalogue.ui.analytics.WeaponDetailsAnalytics
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class WeaponDetailsViewModelTest {

    private val mockAnalytics = mockk<WeaponDetailsAnalytics>(relaxed = true)
    private val viewModel = WeaponDetailsViewModel(mockAnalytics)

    @Test
    fun `onScreenViewed should track screen view event`() {
        // When
        viewModel.onScreenViewed()

        // Then
        verify { mockAnalytics.trackScreenViewed() }
    }
}
