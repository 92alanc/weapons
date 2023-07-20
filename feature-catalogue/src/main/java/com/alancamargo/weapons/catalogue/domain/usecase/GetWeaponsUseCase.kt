package com.alancamargo.weapons.catalogue.domain.usecase

import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.model.WeaponQuery
import kotlinx.coroutines.flow.Flow

internal interface GetWeaponsUseCase {

    operator fun invoke(query: WeaponQuery): Flow<WeaponListResult>
}
