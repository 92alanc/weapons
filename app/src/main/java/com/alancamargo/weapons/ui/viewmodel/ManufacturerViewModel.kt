package com.alancamargo.weapons.ui.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.manufacturer.ManufacturerRepository
import com.alancamargo.weapons.domain.entities.Manufacturer
import com.alancamargo.weapons.ui.entities.UiManufacturer
import com.alancamargo.weapons.ui.entities.conversions.fromDomainToUi
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch

class ManufacturerViewModel(private val repository: ManufacturerRepository) : ViewModel() {

    private val stateLiveData = MutableLiveData<State>()

    init {
        loadState()
    }

    fun getState(): LiveData<State> = stateLiveData

    private fun loadState() {
        viewModelScope.launch {
            stateLiveData.postValue(State.Loading)
            val result = repository.getManufacturers()
            processResult(result)
        }
    }

    private fun processResult(result: Result<List<Manufacturer>>) {
        when (result) {
            is Result.Success -> {
                val manufacturers = result.body.map { it.fromDomainToUi() }
                stateLiveData.postValue(State.Ready(manufacturers))
            }

            is Result.Error -> stateLiveData.postValue(State.Error)
        }
    }

    sealed class State : Parcelable {

        @Parcelize
        object Loading : State()

        @Parcelize
        data class Ready(val calibres: List<UiManufacturer>) : State()

        @Parcelize
        object Error : State()

    }

}