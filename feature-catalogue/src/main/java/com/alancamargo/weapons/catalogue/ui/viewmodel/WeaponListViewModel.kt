package com.alancamargo.weapons.catalogue.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.usecase.GetWeaponsUseCase
import com.alancamargo.weapons.catalogue.ui.mapping.createMapFromHeaderType
import com.alancamargo.weapons.catalogue.ui.mapping.toDomain
import com.alancamargo.weapons.catalogue.ui.mapping.toUi
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.di.IoDispatcher
import com.alancamargo.weapons.core.log.Logger
import com.alancamargo.weapons.core.resources.ResourcesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WeaponListViewModel @Inject constructor(
    private val getWeaponsUseCase: GetWeaponsUseCase,
    private val logger: Logger,
    private val resourcesHelper: ResourcesHelper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(WeaponListViewState())
    private val _action = MutableSharedFlow<WeaponListViewAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun handleQuery(query: UiWeaponQuery) {
        viewModelScope.launch(dispatcher) {
            getWeaponsUseCase(query.toDomain()).onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch { exception ->
                logger.error(exception)
                _state.update { it.onError() }
            }.collect(::handleResult)
        }
    }

    fun onWeaponClicked(weapon: UiWeapon) {
        val action = WeaponListViewAction.NavigateToWeaponDetails(weapon)
        sendAction(action)
    }

    fun onBackClicked() {
        sendAction(WeaponListViewAction.Finish)
    }

    private fun handleResult(result: WeaponListResult) = when (result) {
        is WeaponListResult.Success -> {
            val body = result.weapons

            if (body.isEmpty()) {
                _state.update { it.onEmptyState() }
            } else {
                if (body.isWeaponList()) {
                    handleWeaponList(body)
                } else {
                    handleWeaponListWithHeader(body)
                }
            }
        }

        is WeaponListResult.Error -> _state.update { it.onError() }
    }

    private fun Map<WeaponListHeader?, List<Weapon>>.isWeaponList(): Boolean {
        return size == 1 && entries.first().key == null
    }

    private fun handleWeaponList(body: Map<WeaponListHeader?, List<Weapon>>) {
        val weapons = body.flatMap {
            it.value
        }.map { it.toUi(resourcesHelper) }

        _state.update { it.onWeaponsReceived(weapons) }
    }

    private fun handleWeaponListWithHeader(body: Map<WeaponListHeader?, List<Weapon>>) {
        val weaponList = body.flatMap {
            it.value.map { weapon ->
                weapon.toUi(resourcesHelper)
            }
        }

        val headerClass = body.keys.first { it != null }?.javaClass
            ?: throw IllegalStateException("Type must not be null")

        val weapons = weaponList.createMapFromHeaderType(headerClass).entries.sortedBy {
            it.key?.text
        }
        _state.update { it.onWeaponListWithHeaderReceived(weapons) }
    }

    private fun sendAction(action: WeaponListViewAction) {
        viewModelScope.launch(dispatcher) {
            _action.emit(action)
        }
    }
}
