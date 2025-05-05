package com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails

import androidx.lifecycle.ViewModel
import com.alancamargo.weapons.catalogue.ui.analytics.WeaponDetailsAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class WeaponDetailsViewModel @Inject constructor(
    private val analytics: WeaponDetailsAnalytics
) : ViewModel() {

    fun onScreenViewed() {
        analytics.trackScreenViewed()
    }
}
