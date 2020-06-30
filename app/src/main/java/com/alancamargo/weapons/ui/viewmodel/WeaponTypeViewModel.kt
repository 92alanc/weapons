package com.alancamargo.weapons.ui.viewmodel

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.type.WeaponTypeRepository
import com.alancamargo.weapons.domain.entities.WeaponType
import com.alancamargo.weapons.ui.entities.UiWeaponType
import com.alancamargo.weapons.ui.entities.conversions.fromDomainToUi
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch

class WeaponTypeViewModel(
    private val repository: WeaponTypeRepository,
    private val context: Context
) : ViewModel() {

    private val stateLiveData = MutableLiveData<State>()

    init {
        loadState()
    }

    fun getState(): LiveData<State> = stateLiveData

    private fun loadState() {
        viewModelScope.launch {
            stateLiveData.postValue(State.Loading)
            val result = repository.getWeaponTypes()
            processResult(result)
        }
    }

    private fun processResult(result: Result<List<WeaponType>>) {
        when (result) {
            is Result.Success -> {
                val types = result.body.map { it.fromDomainToUi(context) }
                stateLiveData.postValue(State.Ready(types))
            }

            is Result.Error -> stateLiveData.postValue(State.Error)
        }
    }

    sealed class State : Parcelable {

        @Parcelize
        object Loading : State()

        @Parcelize
        data class Ready(val types: List<UiWeaponType>) : State()

        @Parcelize
        object Error : State()

    }

}