package com.alancamargo.weapons.ui.viewmodel

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
import kotlinx.coroutines.launch

class WeaponListViewModel(private val repository: WeaponRepository) : ViewModel() {

    private val commandLiveData = MutableLiveData<Command>()

    fun start(query: WeaponQuery) = when (query) {
        is WeaponQuery.All -> loadAllWeapons()
        is WeaponQuery.ByName -> loadWeaponsByName(query.name)
        is WeaponQuery.ByYear -> loadWeaponsByYear(query.yearId)
        is WeaponQuery.ByCountry -> loadWeaponsByCountry(query.countryId)
        is WeaponQuery.ByType -> loadWeaponsByType(query.typeId)
        is WeaponQuery.ByCalibre -> loadWeaponsByCalibre(query.calibreId)
        is WeaponQuery.ByManufacturer -> loadWeaponsByManufacturer(query.manufacturerId)
    }

    fun getCommand(): LiveData<Command> = commandLiveData

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
            commandLiveData.postValue(Command.Load)
            val result = query.invoke()
            processResult(result)
        }
    }

    private fun processResult(result: Result<List<Weapon>>) {
        when (result) {
            is Result.Success -> {
                val weapons = result.body.map { it.fromDomainToUi() }
                commandLiveData.postValue(Command.Display(weapons))
            }
            is Result.Error -> commandLiveData.postValue(Command.Error)
        }
    }

    sealed class Command {
        object Load : Command()
        data class Display(val weapons: List<UiWeapon>) : Command()
        object Error : Command()
    }

}