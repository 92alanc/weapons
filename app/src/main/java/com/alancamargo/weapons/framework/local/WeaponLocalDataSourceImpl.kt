package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.framework.db.CalibreDao
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.db.WeaponTypeDao

class WeaponLocalDataSourceImpl(
    private val weaponDao: WeaponDao,
    private val weaponTypeDao: WeaponTypeDao,
    private val countryDao: CountryDao,
    private val calibreDao: CalibreDao
) : WeaponLocalDataSource {

    override suspend fun getWeapons(): List<Weapon> {
        return emptyList()
    }

}