package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.*
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.domain.entities.WeaponListHeader
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.entities.DbWeapon
import com.alancamargo.weapons.framework.entities.conversions.fromDbToDomain

class WeaponLocalDataSourceImpl(
    private val weaponDao: WeaponDao,
    private val weaponTypeLocalDataSource: WeaponTypeLocalDataSource,
    private val countryLocalDataSource: CountryLocalDataSource,
    private val calibreLocalDataSource: CalibreLocalDataSource,
    private val manufacturerLocalDataSource: ManufacturerLocalDataSource,
    private val yearLocalDataSource: YearLocalDataSource
) : WeaponLocalDataSource {

    override suspend fun getWeapons(): Map<WeaponListHeader?, List<Weapon>> {
        return mapOf(null to getAllWeaponsFromDb())
    }

    override suspend fun getWeaponsByName(name: String): Map<WeaponListHeader?, List<Weapon>> {
        return mapOf(null to weaponDao.selectByName("%$name%").fromDbToDomain())
    }

    override suspend fun getWeaponsByYear(): Map<WeaponListHeader?, List<Weapon>> {
        return getAllWeaponsFromDb().groupBy { it.year }
    }

    override suspend fun getWeaponsByCountry(): Map<WeaponListHeader?, List<Weapon>> {
        return getAllWeaponsFromDb().groupBy { it.country }
    }

    override suspend fun getWeaponsByType(): Map<WeaponListHeader?, List<Weapon>> {
        return getAllWeaponsFromDb().groupBy { it.type }
    }

    override suspend fun getWeaponsByCalibre(): Map<WeaponListHeader?, List<Weapon>> {
        return getAllWeaponsFromDb().groupBy { it.calibre }
    }

    override suspend fun getWeaponsByManufacturer(): Map<WeaponListHeader?, List<Weapon>> {
        return getAllWeaponsFromDb().groupBy { it.manufacturer }
    }

    private suspend fun getAllWeaponsFromDb() = weaponDao.selectAll().fromDbToDomain()

    private suspend fun List<DbWeapon>.fromDbToDomain(): List<Weapon> {
        return this.map {
            it.fromDbToDomain()
        }
    }

    private suspend fun DbWeapon.fromDbToDomain(): Weapon {
        val country = countryId?.let {
            countryLocalDataSource.getCountryById(it)
        }

        val type = weaponTypeLocalDataSource.getWeaponTypeById(typeId)

        val calibre = calibreId?.let {
            calibreLocalDataSource.getCalibreById(it)
        }

        val manufacturer = manufacturerId?.let {
            manufacturerLocalDataSource.getManufacturerById(it)
        }

        val year = yearId?.let {
            yearLocalDataSource.getYearById(it)
        }

        return fromDbToDomain(manufacturer, country, type, calibre, year)
    }

}