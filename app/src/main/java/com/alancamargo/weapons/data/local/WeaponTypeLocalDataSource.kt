package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.catalogue.domain.model.WeaponType

interface WeaponTypeLocalDataSource {

    suspend fun getWeaponTypes(): List<WeaponType>

    suspend fun getWeaponTypeById(id: Long): WeaponType

}