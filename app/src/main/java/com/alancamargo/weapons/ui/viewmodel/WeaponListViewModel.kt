package com.alancamargo.weapons.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.conversions.fromDomainToUi
import kotlinx.coroutines.launch

class WeaponListViewModel(private val repository: WeaponRepository) : ViewModel() {

    init {
        loadWeapons()
    }

    private val commandLiveData = MutableLiveData<Command>()

    fun getCommand(): LiveData<Command> = commandLiveData

    private fun loadWeapons() {
        viewModelScope.launch {
            commandLiveData.postValue(Command.Load)
            val result = repository.getWeapons()
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