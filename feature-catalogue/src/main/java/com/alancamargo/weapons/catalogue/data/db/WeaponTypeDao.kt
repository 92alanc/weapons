package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType

@Dao
internal interface WeaponTypeDao {

    @Query("SELECT * FROM WEAPON_TYPE")
    suspend fun selectAll(): List<DbWeaponType>

    @Query("SELECT * FROM WEAPON_TYPE WHERE ID = :id")
    suspend fun selectById(id: Long): DbWeaponType
}
