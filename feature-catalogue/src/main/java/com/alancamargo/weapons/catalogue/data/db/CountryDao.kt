package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.catalogue.data.model.DbCountry

@Dao
internal interface CountryDao {

    @Query("SELECT * FROM COUNTRY ORDER BY NAME")
    suspend fun selectAll(): List<DbCountry>

    @Query("SELECT * FROM COUNTRY WHERE ID = :id")
    suspend fun selectById(id: Long): DbCountry

    @Query("SELECT * FROM COUNTRY WHERE ID = (SELECT COUNTRY_ID FROM WEAPON WHERE NAME = :weaponName)")
    suspend fun getCountryByWeaponName(weaponName: String): DbCountry?
}
