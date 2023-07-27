package com.alancamargo.weapons.home.ui.viewmodel.weaponsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.di.IoDispatcher
import com.alancamargo.weapons.home.ui.analytics.WeaponSearchAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WeaponSearchViewModel @Inject constructor(
    private val analytics: WeaponSearchAnalytics,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _action = MutableSharedFlow<WeaponSearchViewAction>()

    val action: SharedFlow<WeaponSearchViewAction> = _action

    fun start() {
        analytics.trackDialogueViewed()
    }

    fun onCancel() {
        analytics.trackDialogueCancelled()
    }

    fun onOkClicked(weaponName: String) {
        analytics.trackWeaponSearched(weaponName)

        viewModelScope.launch(dispatcher) {
            val query = UiWeaponQuery.ByName(weaponName)
            val action = WeaponSearchViewAction.NavigateToWeaponList(query)
            _action.emit(action)
        }
    }
}
