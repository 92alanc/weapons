package com.alancamargo.weapons.ui.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.calibre.CalibreRepository
import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.ui.entities.UiCalibre
import com.alancamargo.weapons.ui.entities.conversions.fromDomainToUi
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch

class CalibreViewModel(private val repository: CalibreRepository) : ViewModel() {

    private val stateLiveData = MutableLiveData<State>()

    init {
        loadState()
    }

    fun getState(): LiveData<State> = stateLiveData

    private fun loadState() {
        viewModelScope.launch {
            stateLiveData.postValue(State.Loading)
            val result = repository.getCalibres()
            processResult(result)
        }
    }

    private fun processResult(result: Result<List<Calibre>>) {
        when (result) {
            is Result.Success -> {
                val calibres = result.body.map { it.fromDomainToUi() }
                stateLiveData.postValue(State.Ready(calibres))
            }

            is Result.Error -> stateLiveData.postValue(State.Error)
        }
    }

    sealed class State : Parcelable {

        @Parcelize
        object Loading : State()

        @Parcelize
        data class Ready(val calibres: List<UiCalibre>) : State()

        @Parcelize
        object Error : State()

    }

}