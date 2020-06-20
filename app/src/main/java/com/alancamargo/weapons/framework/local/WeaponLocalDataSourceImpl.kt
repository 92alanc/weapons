package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.framework.db.CalibreDao
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.db.WeaponTypeDao
import com.alancamargo.weapons.framework.model.conversions.fromDbToDomain
import com.alancamargo.weapons.framework.model.entities.DbWeapon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeaponLocalDataSourceImpl(
    private val weaponDao: WeaponDao,
    private val weaponTypeDao: WeaponTypeDao,
    private val countryDao: CountryDao,
    private val calibreDao: CalibreDao
) : WeaponLocalDataSource {

    override suspend fun getWeapons(): List<Weapon> = withContext(Dispatchers.IO) {
        weaponDao.selectAll().fromDbToDomain()
    }

    override suspend fun getWeaponById(id: Long): Weapon = withContext(Dispatchers.IO) {
        weaponDao.selectById(id).fromDbToDomain()
    }

    override suspend fun getWeaponsByName(name: String): List<Weapon> {
        return withContext(Dispatchers.IO) {
            weaponDao.selectByName(name).fromDbToDomain()
        }
    }

    override suspend fun getWeaponsByYear(year: Int): List<Weapon> {
        return withContext(Dispatchers.IO) {
            weaponDao.selectByYear(year).fromDbToDomain()
        }
    }

    override suspend fun getWeaponsByCountry(countryId: Long): List<Weapon> {
        return withContext(Dispatchers.IO) {
            val dbWeapons = weaponDao.selectByCountry(countryId)
            val country = countryDao.selectById(countryId)

            dbWeapons.map {
                val type = weaponTypeDao.select(it.typeId)
                val calibre = calibreDao.selectById(it.calibreId)
                it.fromDbToDomain(country, type, calibre)
            }
        }
    }

    override suspend fun getWeaponsByType(typeId: Long): List<Weapon> {
        return withContext(Dispatchers.IO) {
            val dbWeapons = weaponDao.selectByType(typeId)
            val type = weaponTypeDao.select(typeId)

            dbWeapons.map {
                val country = countryDao.selectById(it.countryId)
                val calibre = calibreDao.selectById(it.calibreId)
                it.fromDbToDomain(country, type, calibre)
            }
        }
    }

    override suspend fun getWeaponsByCalibre(calibreId: Long): List<Weapon> {
        return withContext(Dispatchers.IO) {
            val dbWeapons = weaponDao.selectByCalibre(calibreId)
            val calibre = calibreDao.selectById(calibreId)

            dbWeapons.map {
                val country = countryDao.selectById(it.countryId)
                val type = weaponTypeDao.select(it.typeId)
                it.fromDbToDomain(country, type, calibre)
            }
        }
    }

    private suspend fun List<DbWeapon>.fromDbToDomain(): List<Weapon> {
        return this.map {
            it.fromDbToDomain()
        }
    }

    private suspend fun DbWeapon.fromDbToDomain(): Weapon {
        val country = countryDao.selectById(countryId)
        val type = weaponTypeDao.select(typeId)
        val calibre = calibreDao.selectById(calibreId)
        return fromDbToDomain(country, type, calibre)
    }

}