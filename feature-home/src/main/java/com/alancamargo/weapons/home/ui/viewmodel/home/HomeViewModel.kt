package com.alancamargo.weapons.home.ui.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.di.IoDispatcher
import com.alancamargo.weapons.home.ui.analytics.HomeAnalytics
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

private const val PRIVACY_POLICY_URL = "https://pastebin.com/raw/Krd7c6aJ"

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val analytics: HomeAnalytics,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    private val _action = MutableSharedFlow<HomeViewAction>()

    val state: StateFlow<HomeViewState> = _state
    val action: SharedFlow<HomeViewAction> = _action

    fun getQueryTypes() {
        analytics.trackScreenViewed()
        val queryTypes = WeaponQueryType.values().toList()
        _state.update { it.onQueryTypesReceived(queryTypes) }
    }

    fun onAllWeaponsClicked() {
        analytics.trackAllWeaponsClicked()
        val action = HomeViewAction.NavigateToWeaponList(UiWeaponQuery.All)
        sendAction(action)
    }

    fun onQueryItemClicked(query: WeaponQueryType) {
        val action = when (query) {
            WeaponQueryType.BY_CALIBRE -> {
                analytics.trackGroupByCalibreClicked()
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCalibre)
            }

            WeaponQueryType.BY_COUNTRY -> {
                analytics.trackGroupByCountryClicked()
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByCountry)
            }

            WeaponQueryType.BY_MAKE -> {
                analytics.trackGroupByMakeClicked()
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByMake)
            }

            WeaponQueryType.BY_NAME -> {
                analytics.trackGroupByNameClicked()
                HomeViewAction.ShowWeaponSearchDialogue
            }

            WeaponQueryType.BY_TYPE -> {
                analytics.trackGroupByTypeClicked()
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByType)
            }

            WeaponQueryType.BY_YEAR -> {
                analytics.trackGroupByYearClicked()
                HomeViewAction.NavigateToWeaponList(UiWeaponQuery.ByYear)
            }
        }

        sendAction(action)
    }

    fun onAppInfoClicked() {
        analytics.trackAppInfoClicked()
        val action = HomeViewAction.ShowAppInfo
        sendAction(action)
    }

    fun onPrivacyPolicyClicked() {
        analytics.trackPrivacyPolicyClicked()
        val action = HomeViewAction.ShowPrivacyPolicy(PRIVACY_POLICY_URL)
        sendAction(action)
    }

    private fun sendAction(action: HomeViewAction) {
        viewModelScope.launch(dispatcher) {
            _action.emit(action)
        }
    }
}
