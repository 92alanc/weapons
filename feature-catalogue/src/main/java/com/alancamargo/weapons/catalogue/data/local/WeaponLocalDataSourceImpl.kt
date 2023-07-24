package com.alancamargo.weapons.catalogue.data.local

import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import com.alancamargo.weapons.catalogue.data.mapping.toDomain
import com.alancamargo.weapons.catalogue.data.model.DbWeapon
import com.alancamargo.weapons.catalogue.data.tools.FileHelper
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import javax.inject.Inject

internal class WeaponLocalDataSourceImpl @Inject constructor(
    private val weaponDao: WeaponDao,
    private val fileHelper: FileHelper
) : WeaponLocalDataSource {

    override suspend fun getAllWeapons(): Map<WeaponListHeader?, List<Weapon>> {
        return mapOf(null to weaponDao.getAllWeapons().toDomain())
    }

    override suspend fun filterWeaponsByName(name: String): Map<WeaponListHeader?, List<Weapon>> {
        return mapOf(null to weaponDao.getWeaponsByName(name).toDomain())
    }

    override suspend fun groupWeaponsByYear(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().toDomain().groupBy { it.year }
    }

    override suspend fun groupWeaponsByCountry(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().toDomain().groupBy { it.country }
    }

    override suspend fun groupWeaponsByType(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().toDomain().groupBy { it.type }
    }

    override suspend fun groupWeaponsByCalibre(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().toDomain().groupBy { it.calibre }
    }

    override suspend fun groupWeaponsByManufacturer(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().toDomain().groupBy { it.manufacturer }
    }

    private suspend fun List<DbWeapon>.toDomain() = map {
        val photos = fileHelper.getImageFilePaths(it.weapon.name)
        it.toDomain(photos)
    }
}
