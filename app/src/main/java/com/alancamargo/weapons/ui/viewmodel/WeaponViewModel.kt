package com.alancamargo.weapons.ui.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.UiWeaponListHeader
import com.alancamargo.weapons.ui.queries.WeaponQuery
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class WeaponViewModel(
    private val repository: WeaponRepository,
    private val weaponMapper: EntityMapper<Weapon, UiWeapon>
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