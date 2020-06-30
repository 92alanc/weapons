package com.alancamargo.weapons.ui.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.conversions.fromDomainToUi
import com.alancamargo.weapons.ui.queries.WeaponQuery
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch

class WeaponViewModel(private val repository: WeaponRepository) : ViewModel() {

    private val stateLiveData = MutableLiveData<State>()

    fun start(query: WeaponQuery) = when (query) {
        is WeaponQuery.All -> loadAllWeapons()
        is WeaponQuery.ByName -> loadWeaponsByName(query.name)
        is WeaponQuery.ByYear -> loadWeaponsByYear(query.yearId)
        is WeaponQuery.ByCountry -> loadWeaponsByCountry(query.countryId)
        is WeaponQuery.ByType -> loadWeaponsByType(query.typeId)
        is WeaponQuery.ByCalibre -> loadWeaponsByCalibre(query.calibreId)
        is WeaponQuery.ByManufacturer -> loadWeaponsByManufacturer(query.manufacturerId)
    }

    fun getState(): LiveData<State> = stateLiveData

    private fun loadAllWeapons() = runQuery {
        repository.getWeapons()
    }

    private fun loadWeaponsByName(name: String) = runQuery {
        repository.getWeaponsByName(name)
    }

    private fun loadWeaponsByYear(yearId: Long) = runQuery {
        repository.getWeaponsByYear(yearId)
    }

    private fun loadWeaponsByCountry(countryId: Long) = runQuery {
        repository.getWeaponsByCountry(countryId)
    }

    private fun loadWeaponsByType(typeId: Long) = runQuery {
        repository.getWeaponsByType(typeId)
    }

    private fun loadWeaponsByCalibre(calibreId: Long) = runQuery {
        repository.getWeaponsByCalibre(calibreId)
    }

    private fun loadWeaponsByManufacturer(manufacturerId: Long) = runQuery {
        repository.getWeaponsByManufacturer(manufacturerId)
    }

    private fun runQuery(query: suspend () -> Result<List<Weapon>>) {
        viewModelScope.launch {
            stateLiveData.postValue(State.Loading)
            val result = query.invoke()
            processResult(result)
        }
    }

    private fun processResult(result: Result<List<Weapon>>) {
        when (result) {
            is Result.Success -> {
                val weapons = result.body.map { it.fromDomainToUi() }
                stateLiveData.postValue(State.Ready(weapons))
            }
            is Result.Error -> stateLiveData.postValue(State.Error)
        }
    }

    sealed class State : Parcelable {
        @Parcelize
        object Loading : State()

        @Parcelize
        data class Ready(val weapons: List<UiWeapon>) : State()

        @Parcelize
        object Error : State()
    }

}