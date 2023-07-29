package com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.ui.analytics.WeaponDetailsAnalytics
import com.alancamargo.weapons.catalogue.ui.model.UiLabelledCountry
import com.alancamargo.weapons.catalogue.ui.model.UiLabelledWeapon
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.core.di.IoDispatcher
import com.alancamargo.weapons.core.resources.ResourcesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WeaponDetailsViewModel @Inject constructor(
    private val resourcesHelper: ResourcesHelper,
    private val analytics: WeaponDetailsAnalytics,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(WeaponDetailsViewState())
    private val _action = MutableSharedFlow<WeaponDetailsViewAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun start(weapon: UiWeapon) {
        analytics.trackScreenViewed()
        val labelledWeapon = buildLabelledWeapon(weapon)
        _state.update { it.setWeapon(labelledWeapon) }
    }

    fun onBackClicked() {
        analytics.trackBackClicked()
        sendAction(WeaponDetailsViewAction.Finish)
    }

    fun onNativeBackClicked() {
        analytics.trackNativeBackClicked()
        sendAction(WeaponDetailsViewAction.Finish)
    }

    private fun buildLabelledWeapon(weapon: UiWeapon): UiLabelledWeapon {
        val country = weapon.country?.let {
            resourcesHelper.getDrawable(it.flagId)?.let { flagDrawable ->
                UiLabelledCountry(it.name, flagDrawable)
            }
        }

        val year = weapon.year?.let {
            resourcesHelper.getFormattedString(R.string.year_format, it.year)
        }

        val manufacturer = weapon.manufacturer?.let {
            resourcesHelper.getFormattedString(R.string.manufacturer_format, it.name)
        }

        val type = resourcesHelper.getFormattedString(R.string.type_format, weapon.type.name)

        val calibre = weapon.calibre?.let {
            resourcesHelper.getFormattedString(R.string.calibre_format, it.name)
        }

        val length = weapon.lengthInMm?.let {
            resourcesHelper.getFormattedString(R.string.length_format, it)
        }

        val mass = weapon.massInKg?.let {
            resourcesHelper.getFormattedString(R.string.mass_format, it)
        }

        val capacity = weapon.capacityInRounds?.let {
            resourcesHelper.getPluralString(R.plurals.capacity_plural, it)
        }

        val rateOfFire = weapon.rateOfFireInRpm?.let {
            resourcesHelper.getFormattedString(R.string.rate_of_fire_format, it)
        }

        val effectiveRange = weapon.effectiveRangeInM?.let {
            resourcesHelper.getFormattedString(R.string.effective_range_format, it)
        }

        return UiLabelledWeapon(
            name = weapon.name,
            country = country,
            year = year,
            manufacturer = manufacturer,
            type = type,
            calibre = calibre,
            length = length,
            mass = mass,
            capacity = capacity,
            rateOfFire = rateOfFire,
            effectiveRange = effectiveRange,
            photos = weapon.photos
        )
    }

    private fun sendAction(action: WeaponDetailsViewAction) {
        viewModelScope.launch(dispatcher) {
            _action.emit(action)
        }
    }
}
