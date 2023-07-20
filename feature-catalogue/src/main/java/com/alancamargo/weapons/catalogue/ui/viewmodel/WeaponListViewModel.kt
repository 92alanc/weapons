package com.alancamargo.weapons.catalogue.ui.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.catalogue.domain.model.Calibre
import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.catalogue.domain.model.Manufacturer
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.catalogue.domain.model.Year
import com.alancamargo.weapons.catalogue.domain.usecase.GetWeaponsUseCase
import com.alancamargo.weapons.catalogue.ui.mapping.toDomain
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
internal class WeaponListViewModel @Inject constructor(
    private val getWeaponsUseCase: GetWeaponsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val stateLiveData = MutableLiveData<State>().apply {
        value = State.Loading
    }

    fun handleQuery(query: UiWeaponQuery) {
        viewModelScope.launch(dispatcher) {
            getWeaponsUseCase(query.toDomain()).onStart {
                // TODO
            }.onCompletion {
                // TODO
            }.catch {
                // TODO
            }.collect {
                // TODO
            }
        }
    }

    fun getState(): LiveData<State> = stateLiveData

    private fun processResult(result: Result<Map<WeaponListHeader?, List<Weapon>>>) {
        when (result) {
            is Result.Success -> {
                val body = result.body

                if (body.isEmpty()) {
                    stateLiveData.postValue(State.NoResults)
                } else {
                    if (body.isWeaponList())
                        processWeaponList(body)
                    else
                        processWeaponListWithHeader(body)
                }
            }
            is Result.Error -> stateLiveData.postValue(State.Error)
        }
    }

    private fun Map<WeaponListHeader?, List<Weapon>>.isWeaponList(): Boolean {
        return size == 1 && entries.first().key == null
    }

    private fun processWeaponList(body: Map<WeaponListHeader?, List<Weapon>>) {
        val weapons = body.flatMap {
            it.value
        }.map(weaponMapper::map)

        stateLiveData.postValue(State.WeaponListReady(weapons))
    }

    private fun processWeaponListWithHeader(body: Map<WeaponListHeader?, List<Weapon>>) {
        val weaponList = body.flatMap {
            it.value.map(weaponMapper::map)
        }

        val headerClass = body.keys.first { it != null }?.javaClass
            ?: throw IllegalStateException("Type must not be null")

        val weapons: Map<UiWeaponListHeader?, List<UiWeapon>> =
            weaponList.createMapFromHeaderType(headerClass)

        stateLiveData.postValue(State.WeaponListWithHeaderReady(weapons))
    }

    private fun List<UiWeapon>.createMapFromHeaderType(
        headerClass: Class<WeaponListHeader>
    ): Map<UiWeaponListHeader?, List<UiWeapon>> {
        return when (headerClass) {
            Calibre::class.java -> groupBy { it.calibre }
            Country::class.java -> groupBy { it.country }
            WeaponType.Melee::class.java,
            WeaponType.Pistol::class.java,
            WeaponType.Rifle::class.java,
            WeaponType.Shotgun::class.java,
            WeaponType.BoobyTrap::class.java,
            WeaponType.Carbine::class.java,
            WeaponType.MachineGun::class.java,
            WeaponType.SubMachineGun::class.java,
            WeaponType.Grenade::class.java,
            WeaponType.Mine::class.java,
            WeaponType.GrenadeLauncher::class.java,
            WeaponType.BoobyTrap::class.java,
            WeaponType.Flamethrower::class.java -> groupBy { it.type }
            Manufacturer::class.java -> groupBy { it.manufacturer }
            Year::class.java -> groupBy { it.year }
            else -> throw IllegalStateException("Must be an implementation of WeaponListHeader")
        }
    }

    sealed class State : Parcelable {
        @Parcelize
        object Loading : State()

        @Parcelize
        data class WeaponListWithHeaderReady(
            val weapons: Map<UiWeaponListHeader?, List<UiWeapon>>
        ) : State()

        @Parcelize
        data class WeaponListReady(val weapons: List<UiWeapon>) : State()

        @Parcelize
        object Error : State()

        @Parcelize
        object NoResults : State()
    }
}
