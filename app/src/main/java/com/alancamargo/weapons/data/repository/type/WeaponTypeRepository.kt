package com.alancamargo.weapons.data.repository.type

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.domain.entities.WeaponType

interface WeaponTypeRepository {

    suspend fun getWeaponTypes(): Result<List<WeaponType>>

}