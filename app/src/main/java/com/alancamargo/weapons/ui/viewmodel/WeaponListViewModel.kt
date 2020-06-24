package com.alancamargo.weapons.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.domain.Weapon
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
            is Result.Success -> commandLiveData.postValue(Command.Display(result.body))
            is Result.Error -> commandLiveData.postValue(Command.Error)
        }
    }

    sealed class Command {
        object Load : Command()
        data class Display(val weapons: List<Weapon>) : Command()
        object Error : Command()
    }

}