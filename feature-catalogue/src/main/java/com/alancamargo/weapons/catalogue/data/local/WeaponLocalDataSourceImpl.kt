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
        return weaponDao.getAllWeapons().groupBy {
            it.year
        }.map { (year, weapons) ->
            year?.toDomain() to weapons.toDomain()
        }.toMap()
    }

    override suspend fun groupWeaponsByCountry(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().groupBy {
            it.country
        }.map { (country, weapons) ->
            country?.toDomain() to weapons.toDomain()
        }.toMap()
    }

    override suspend fun groupWeaponsByType(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().groupBy {
            it.type
        }.map { (type, weapons) ->
            type.toDomain() to weapons.toDomain()
        }.toMap()
    }

    override suspend fun groupWeaponsByCalibre(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().groupBy {
            it.calibre
        }.map { (calibre, weapons) ->
            calibre?.toDomain() to weapons.toDomain()
        }.toMap()
    }

    override suspend fun groupWeaponsByMake(): Map<WeaponListHeader?, List<Weapon>> {
        return weaponDao.getAllWeapons().groupBy {
            it.make
        }.map { (make, weapons) ->
            make?.toDomain() to weapons.toDomain()
        }.toMap()
    }

    private suspend fun List<DbWeapon>.toDomain() = map {
        val photos = fileHelper.getImageFilePaths(it.weapon.name)
        it.toDomain(photos)
    }
}
