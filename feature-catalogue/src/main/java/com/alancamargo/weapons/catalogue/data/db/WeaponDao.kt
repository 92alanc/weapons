package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.catalogue.data.model.DbCountry
import com.alancamargo.weapons.catalogue.data.model.DbWeapon

@Dao
internal interface WeaponDao {

    @Query("SELECT * FROM WEAPON ORDER BY NAME")
    suspend fun getAllWeapons(): List<DbWeapon>

    @Query("SELECT * FROM WEAPON WHERE NAME LIKE '%' || :name || '%' ORDER BY NAME")
    suspend fun getWeaponsByName(name: String): List<DbWeapon>

    @Query("SELECT * FROM COUNTRY ORDER BY NAME")
    suspend fun getAllCountries(): List<DbCountry>

    @Query("SELECT * FROM COUNTRY WHERE ID = :id")
    suspend fun getCountryById(id: Long): DbCountry

    @Query("SELECT * FROM COUNTRY WHERE ID = (SELECT COUNTRY_ID FROM WEAPON WHERE NAME = :weaponName)")
    suspend fun getCountryByWeaponName(weaponName: String): DbCountry?
}
