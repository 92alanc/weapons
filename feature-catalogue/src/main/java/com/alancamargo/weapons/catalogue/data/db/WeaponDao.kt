package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.alancamargo.weapons.catalogue.data.model.DbWeapon

@Dao
internal interface WeaponDao {

    @Transaction
    @Query("SELECT * FROM WEAPON ORDER BY NAME")
    suspend fun getAllWeapons(): List<DbWeapon>

    @Transaction
    @Query("SELECT * FROM WEAPON WHERE NAME LIKE '%' || :name || '%' ORDER BY NAME")
    suspend fun getWeaponsByName(name: String): List<DbWeapon>
}
