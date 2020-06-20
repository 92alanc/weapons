package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.framework.db.CalibreDao
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.db.WeaponTypeDao
import com.alancamargo.weapons.framework.model.conversions.fromDbToDomain

class WeaponLocalDataSourceImpl(
    private val weaponDao: WeaponDao,
    private val weaponTypeDao: WeaponTypeDao,
    private val countryDao: CountryDao,
    private val calibreDao: CalibreDao
) : WeaponLocalDataSource {

    override suspend fun getWeapons(): List<Weapon> {
        val dbWeapons = weaponDao.select()
        val countryIds = dbWeapons.map { it.countryId }
        val typeIds = dbWeapons.map { it.typeId }
        val calibreIds = dbWeapons.map { it.calibreId }

        val countries = countryIds.map { countryDao.select(it).fromDbToDomain() }
        val types = typeIds.map { weaponTypeDao.select(it).fromDbToDomain() }

        return emptyList() // TODO
    }

}