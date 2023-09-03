package com.alancamargo.weapons.catalogue.domain.usecase

import com.alancamargo.weapons.catalogue.domain.repository.WeaponRepository
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.model.WeaponQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetWeaponsUseCaseImpl @Inject constructor(
    private val repository: WeaponRepository
) : GetWeaponsUseCase {

    override fun invoke(query: WeaponQuery): Flow<WeaponListResult> = flow {
        val result = getWeaponListResult(query)
        emit(result)
    }

    private suspend fun getWeaponListResult(query: WeaponQuery) = when (query) {
        is WeaponQuery.All -> repository.getAllWeapons()
        is WeaponQuery.ByCalibre -> repository.groupWeaponsByCalibre()
        is WeaponQuery.ByCountry -> repository.groupWeaponsByCountry()
        is WeaponQuery.ByMake -> repository.groupWeaponsByMake()
        is WeaponQuery.ByType -> repository.groupWeaponsByType()
        is WeaponQuery.ByYear -> repository.groupWeaponsByYear()
        is WeaponQuery.ByName -> repository.filterWeaponsByName(query.name)
    }
}
