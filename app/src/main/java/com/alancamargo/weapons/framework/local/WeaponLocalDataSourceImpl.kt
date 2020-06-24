package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.*
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.entities.conversions.fromDbToDomain
import com.alancamargo.weapons.framework.entities.DbWeapon

class WeaponLocalDataSourceImpl(
    private val weaponDao: WeaponDao,
    private val weaponTypeLocalDataSource: WeaponTypeLocalDataSource,
    private val countryLocalDataSource: CountryLocalDataSource,
    private val calibreLocalDataSource: CalibreLocalDataSource,
    private val manufacturerLocalDataSource: ManufacturerLocalDataSource
) : WeaponLocalDataSource {

    override suspend fun getWeapons(): List<Weapon> = weaponDao.selectAll().fromDbToDomain()

    override suspend fun getWeaponsByName(name: String): List<Weapon> {
        return weaponDao.selectByName(name).fromDbToDomain()
    }

    override suspend fun getWeaponsByYear(year: Int): List<Weapon> {
        return weaponDao.selectByYear(year).fromDbToDomain()
    }

    override suspend fun getWeaponsByCountry(countryId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByCountry(countryId)
        val country = countryLocalDataSource.getCountryById(countryId)

        return dbWeapons.map {
            val type = weaponTypeLocalDataSource.getWeaponTypeById(it.typeId)
            val calibre = calibreLocalDataSource.getCalibreById(it.calibreId)
            val manufacturer = manufacturerLocalDataSource.getManufacturerById(it.manufacturerId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    override suspend fun getWeaponsByType(typeId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByType(typeId)
        val type = weaponTypeLocalDataSource.getWeaponTypeById(typeId)

        return dbWeapons.map {
            val country = countryLocalDataSource.getCountryById(it.countryId)
            val calibre = calibreLocalDataSource.getCalibreById(it.calibreId)
            val manufacturer = manufacturerLocalDataSource.getManufacturerById(it.manufacturerId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    override suspend fun getWeaponsByCalibre(calibreId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByCalibre(calibreId)
        val calibre = calibreLocalDataSource.getCalibreById(calibreId)

        return dbWeapons.map {
            val country = countryLocalDataSource.getCountryById(it.countryId)
            val type = weaponTypeLocalDataSource.getWeaponTypeById(it.typeId)
            val manufacturer = manufacturerLocalDataSource.getManufacturerById(it.manufacturerId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    override suspend fun getWeaponsByManufacturer(manufacturerId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByManufacturer(manufacturerId)
        val manufacturer = manufacturerLocalDataSource.getManufacturerById(manufacturerId)

        return dbWeapons.map {
            val country = countryLocalDataSource.getCountryById(it.countryId)
            val type = weaponTypeLocalDataSource.getWeaponTypeById(it.typeId)
            val calibre = calibreLocalDataSource.getCalibreById(it.calibreId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    private suspend fun List<DbWeapon>.fromDbToDomain(): List<Weapon> {
        return this.map {
            it.fromDbToDomain()
        }
    }

    private suspend fun DbWeapon.fromDbToDomain(): Weapon {
        val country = countryLocalDataSource.getCountryById(countryId)
        val type = weaponTypeLocalDataSource.getWeaponTypeById(typeId)
        val calibre = calibreLocalDataSource.getCalibreById(calibreId)
        val manufacturer = manufacturerLocalDataSource.getManufacturerById(manufacturerId)
        return fromDbToDomain(manufacturer, country, type, calibre)
    }

}