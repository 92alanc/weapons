package com.alancamargo.weapons.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.common.ui.WeaponQuery
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
                HomeViewAction.NavigateToWeaponsList(WeaponQuery.All)
            }

            WeaponQueryType.BY_CALIBRE -> {
                HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByCalibre)
            }

            WeaponQueryType.BY_COUNTRY -> {
                HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByCountry)
            }

            WeaponQueryType.BY_MANUFACTURER -> {
                HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByManufacturer)
            }

            WeaponQueryType.BY_NAME -> {
                HomeViewAction.ShowWeaponSearchDialogue
            }

            WeaponQueryType.BY_TYPE -> {
                HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByType)
            }

            WeaponQueryType.BY_YEAR -> {
                HomeViewAction.NavigateToWeaponsList(WeaponQuery.ByYear)
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