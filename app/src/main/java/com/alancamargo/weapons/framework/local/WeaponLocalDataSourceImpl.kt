package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.framework.db.*
import com.alancamargo.weapons.framework.model.conversions.fromDbToDomain
import com.alancamargo.weapons.framework.model.entities.DbWeapon

class WeaponLocalDataSourceImpl(
    private val weaponDao: WeaponDao,
    private val weaponTypeDao: WeaponTypeDao,
    private val countryDao: CountryDao,
    private val calibreDao: CalibreDao,
    private val manufacturerDao: ManufacturerDao
) : WeaponLocalDataSource {

    override suspend fun getWeapons(): List<Weapon> = weaponDao.selectAll().fromDbToDomain()

    override suspend fun getWeaponById(id: Long): Weapon = weaponDao.selectById(id).fromDbToDomain()

    override suspend fun getWeaponsByName(name: String): List<Weapon> {
        return weaponDao.selectByName(name).fromDbToDomain()
    }

    override suspend fun getWeaponsByYear(year: Int): List<Weapon> {
        return weaponDao.selectByYear(year).fromDbToDomain()
    }

    override suspend fun getWeaponsByCountry(countryId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByCountry(countryId)
        val country = countryDao.selectById(countryId)

        return dbWeapons.map {
            val type = weaponTypeDao.selectById(it.typeId)
            val calibre = calibreDao.selectById(it.calibreId)
            val manufacturer = manufacturerDao.selectById(it.manufacturerId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    override suspend fun getWeaponsByType(typeId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByType(typeId)
        val type = weaponTypeDao.selectById(typeId)

        return dbWeapons.map {
            val country = countryDao.selectById(it.countryId)
            val calibre = calibreDao.selectById(it.calibreId)
            val manufacturer = manufacturerDao.selectById(it.manufacturerId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    override suspend fun getWeaponsByCalibre(calibreId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByCalibre(calibreId)
        val calibre = calibreDao.selectById(calibreId)

        return dbWeapons.map {
            val country = countryDao.selectById(it.countryId)
            val type = weaponTypeDao.selectById(it.typeId)
            val manufacturer = manufacturerDao.selectById(it.manufacturerId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    override suspend fun getWeaponsByManufacturer(manufacturerId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByManufacturer(manufacturerId)
        val manufacturer = manufacturerDao.selectById(manufacturerId)

        return dbWeapons.map {
            val country = countryDao.selectById(it.countryId)
            val type = weaponTypeDao.selectById(it.typeId)
            val calibre = calibreDao.selectById(it.calibreId)
            it.fromDbToDomain(manufacturer, country, type, calibre)
        }
    }

    private suspend fun List<DbWeapon>.fromDbToDomain(): List<Weapon> {
        return this.map {
            it.fromDbToDomain()
        }
    }

    private suspend fun DbWeapon.fromDbToDomain(): Weapon {
        val country = countryDao.selectById(countryId)
        val type = weaponTypeDao.selectById(typeId)
        val calibre = calibreDao.selectById(calibreId)
        val manufacturer = manufacturerDao.selectById(manufacturerId)
        return fromDbToDomain(manufacturer, country, type, calibre)
    }

}