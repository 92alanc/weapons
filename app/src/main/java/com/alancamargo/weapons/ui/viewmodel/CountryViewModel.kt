package com.alancamargo.weapons.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.country.CountryRepository
import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.ui.entities.UiCountry
import com.alancamargo.weapons.ui.entities.conversions.fromDomainToUi
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {

    init {
        loadCountries()
    }

    private val stateLiveData = MutableLiveData<State>()

    fun getState(): LiveData<State> = stateLiveData

    private fun loadCountries() {
        viewModelScope.launch {
            stateLiveData.postValue(State.Loading)
            val result = repository.getCountries()
            processResult(result)
        }
    }

    private fun processResult(result: Result<List<Country>>) {
        when (result) {
            is Result.Success -> {
                val countries = result.body.map { it.fromDomainToUi() }
                stateLiveData.postValue(State.Ready(countries))
            }

            is Result.Error -> stateLiveData.postValue(State.Error)
        }
    }

    sealed class State {
        object Loading : State()
        data class Ready(val countries: List<UiCountry>) : State()
        object Error : State()
    }

}