package com.alancamargo.weapons.home.ui.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.di.IoDispatcher
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    private val _action = MutableSharedFlow<HomeViewAction>()

    val state: StateFlow<HomeViewState> = _state
    val action: SharedFlow<HomeViewAction> = _action

    fun getQueryTypes() {
        val queryTypes = WeaponQueryType.values().toList()
        _state.update { it.onQueryTypesReceived(queryTypes) }
    }

    fun onQueryItemClicked(query: WeaponQueryType) {
        val action = when (query) {
            WeaponQueryType.ALL -> {
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.All)
            }

            WeaponQueryType.BY_CALIBRE -> {
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCalibre)
            }

            WeaponQueryType.BY_COUNTRY -> {
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCountry)
            }

            WeaponQueryType.BY_MANUFACTURER -> {
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByManufacturer)
            }

            WeaponQueryType.BY_NAME -> {
                HomeViewAction.ShowWeaponSearchDialogue
            }

            WeaponQueryType.BY_TYPE -> {
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByType)
            }

            WeaponQueryType.BY_YEAR -> {
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByYear)
            }
        }

        sendAction(action)
    }

    fun onAppInfoClicked() {
        val action = HomeViewAction.ShowAppInfo
        sendAction(action)
    }

    fun onPrivacyPolicyClicked() {
        val action = HomeViewAction.ShowPrivacyPolicy
        sendAction(action)
    }

    private fun sendAction(action: HomeViewAction) {
        viewModelScope.launch(dispatcher) {
            _action.emit(action)
        }
    }
}
