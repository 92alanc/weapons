package com.alancamargo.weapons.ui.viewmodel

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.domain.entities.WeaponListHeader
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.UiWeaponListHeader
import com.alancamargo.weapons.ui.entities.conversions.fromDomainToUi
import com.alancamargo.weapons.ui.queries.WeaponQuery
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch

class WeaponViewModel(
    private val repository: WeaponRepository,
    private val context: Context
) : ViewModel() {

    private val stateLiveData = MutableLiveData<State>()

    fun start(query: WeaponQuery) = when (query) {
        is WeaponQuery.All -> loadAllWeapons()
        is WeaponQuery.ByName -> loadWeaponsByName(query.name)
        is WeaponQuery.ByYear -> loadWeaponsByYear()
        is WeaponQuery.ByCountry -> loadWeaponsByCountry()
        is WeaponQuery.ByType -> loadWeaponsByType()
        is WeaponQuery.ByCalibre -> loadWeaponsByCalibre()
        is WeaponQuery.ByManufacturer -> loadWeaponsByManufacturer()
    }

    fun getState(): LiveData<State> = stateLiveData

    private fun loadAllWeapons() = runQuery {
        repository.getWeapons()
    }

    private fun loadWeaponsByName(name: String) = runQuery {
        repository.getWeaponsByName(name)
    }

    private fun loadWeaponsByYear() = runQuery {
        repository.getWeaponsByYear()
    }

    private fun loadWeaponsByCountry() = runQuery {
        repository.getWeaponsByCountry()
    }

    private fun loadWeaponsByType() = runQuery {
        repository.getWeaponsByType()
    }

    private fun loadWeaponsByCalibre() = runQuery {
        repository.getWeaponsByCalibre()
    }

    private fun loadWeaponsByManufacturer() = runQuery {
        repository.getWeaponsByManufacturer()
    }

    private fun runQuery(query: suspend () -> Result<Map<WeaponListHeader?, List<Weapon>>>) {
        viewModelScope.launch {
            stateLiveData.postValue(State.Loading)
            val result = query.invoke()
            processResult(result)
        }
    }

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
        val weapons = body.values.first().map {
            it.fromDomainToUi(context)
        }

        stateLiveData.postValue(State.WeaponListReady(weapons))
    }

    private fun processWeaponListWithHeader(body: Map<WeaponListHeader?, List<Weapon>>) {
        // FIXME: WeaponType mapping needs fixing
        val weapons = body.mapKeys {
            it.key?.fromDomainToUi(context)
        }.mapValues {
            it.value.map { weapon ->
                weapon.fromDomainToUi(context)
            }
        }

        stateLiveData.postValue(State.WeaponListWithHeaderReady(weapons))
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