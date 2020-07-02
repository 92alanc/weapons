package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.entities.DbWeapon

@Dao
interface WeaponDao {

    @Query("SELECT * FROM WEAPON ORDER BY NAME")
    suspend fun selectAll(): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE NAME LIKE :name ORDER BY NAME")
    suspend fun selectByName(name: String): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE YEAR_ID = :yearId ORDER BY NAME")
    suspend fun selectByYear(yearId: Long): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE COUNTRY_ID = :countryId ORDER BY NAME")
    suspend fun selectByCountry(countryId: Long): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE TYPE_ID = :typeId ORDER BY NAME")
    suspend fun selectByType(typeId: Long): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE CALIBRE_ID = :calibreId ORDER BY NAME")
    suspend fun selectByCalibre(calibreId: Long): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE MANUFACTURER_ID = :manufacturerId ORDER BY NAME")
    suspend fun selectByManufacturer(manufacturerId: Long): List<DbWeapon>

}