package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.model.entities.DbWeapon

@Dao
interface WeaponDao {

    @Query("SELECT * FROM WEAPON")
    suspend fun select(): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE NAME = :name")
    suspend fun select(name: String): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE YEAR = :year")
    suspend fun select(year: Int): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE COUNTRY_ID = :countryId")
    suspend fun selectByCountry(countryId: Long): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE TYPE_ID = :typeId")
    suspend fun selectByType(typeId: Long): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE CALIBRE_ID = :calibreId")
    suspend fun selectByCalibre(calibreId: Long): List<DbWeapon>

}