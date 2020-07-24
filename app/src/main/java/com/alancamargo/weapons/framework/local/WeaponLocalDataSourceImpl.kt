package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.*
import com.alancamargo.weapons.domain.entities.Weapon
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

    override suspend fun getWeapons(): List<Weapon> = weaponDao.selectAll().fromDbToDomain()

    override suspend fun getWeaponsByName(name: String): List<Weapon> {
        return weaponDao.selectByName("%$name%").fromDbToDomain()
    }

    override suspend fun getWeaponsByYear(yearId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByYear(yearId)
        val year = yearLocalDataSource.getYearById(yearId)

        return dbWeapons.map {
            val type = weaponTypeLocalDataSource.getWeaponTypeById(it.typeId)
            val calibre = it.calibreId?.let { calibreId ->
                calibreLocalDataSource.getCalibreById(calibreId)
            }
            val manufacturer = it.manufacturerId?.let { manufacturerId ->
                manufacturerLocalDataSource.getManufacturerById(manufacturerId)
            }
            val country = it.countryId?.let { countryId ->
                countryLocalDataSource.getCountryById(countryId)
            }
            it.fromDbToDomain(manufacturer, country, type, calibre, year)
        }
    }

    override suspend fun getWeaponsByCountry(countryId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByCountry(countryId)
        val country = countryLocalDataSource.getCountryById(countryId)

        return dbWeapons.map {
            val type = weaponTypeLocalDataSource.getWeaponTypeById(it.typeId)
            val calibre = it.calibreId?.let { calibreId ->
                calibreLocalDataSource.getCalibreById(calibreId)
            }
            val manufacturer = it.manufacturerId?.let { manufacturerId ->
                manufacturerLocalDataSource.getManufacturerById(manufacturerId)
            }
            val year = it.yearId?.let { yearId ->
                yearLocalDataSource.getYearById(yearId)
            }
            it.fromDbToDomain(manufacturer, country, type, calibre, year)
        }
    }

    override suspend fun getWeaponsByType(typeId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByType(typeId)
        val type = weaponTypeLocalDataSource.getWeaponTypeById(typeId)

        return dbWeapons.map {
            val country = it.countryId?.let { countryId ->
                countryLocalDataSource.getCountryById(countryId)
            }
            val calibre = it.calibreId?.let { calibreId ->
                calibreLocalDataSource.getCalibreById(calibreId)
            }
            val manufacturer = it.manufacturerId?.let { manufacturerId ->
                manufacturerLocalDataSource.getManufacturerById(manufacturerId)
            }
            val year = it.yearId?.let { yearId ->
                yearLocalDataSource.getYearById(yearId)
            }
            it.fromDbToDomain(manufacturer, country, type, calibre, year)
        }
    }

    override suspend fun getWeaponsByCalibre(calibreId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByCalibre(calibreId)
        val calibre = calibreLocalDataSource.getCalibreById(calibreId)

        return dbWeapons.map {
            val country = it.countryId?.let { countryId ->
                countryLocalDataSource.getCountryById(countryId)
            }
            val type = weaponTypeLocalDataSource.getWeaponTypeById(it.typeId)
            val manufacturer = it.manufacturerId?.let { manufacturerId ->
                manufacturerLocalDataSource.getManufacturerById(manufacturerId)
            }
            val year = it.yearId?.let { yearId ->
                yearLocalDataSource.getYearById(yearId)
            }
            it.fromDbToDomain(manufacturer, country, type, calibre, year)
        }
    }

    override suspend fun getWeaponsByManufacturer(manufacturerId: Long): List<Weapon> {
        val dbWeapons = weaponDao.selectByManufacturer(manufacturerId)
        val manufacturer = manufacturerLocalDataSource.getManufacturerById(manufacturerId)

        return dbWeapons.map {
            val country = it.countryId?.let { countryId ->
                countryLocalDataSource.getCountryById(countryId)
            }
            val type = weaponTypeLocalDataSource.getWeaponTypeById(it.typeId)
            val calibre = it.calibreId?.let { calibreId ->
                calibreLocalDataSource.getCalibreById(calibreId)
            }
            val year = it.yearId?.let { yearId ->
                yearLocalDataSource.getYearById(yearId)
            }
            it.fromDbToDomain(manufacturer, country, type, calibre, year)
        }
    }

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