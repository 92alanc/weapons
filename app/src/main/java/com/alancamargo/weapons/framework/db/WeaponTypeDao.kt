package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.model.entities.DbWeaponType

@Dao
interface WeaponTypeDao {

    @Query("SELECT * FROM WEAPON_TYPE")
    suspend fun select(): List<DbWeaponType>

    @Query("SELECT * FROM WEAPON_TYPE WHERE NAME = :name")
    suspend fun select(name: String): List<DbWeaponType>
    
    @Query("SELECT * FROM WEAPON_TYPE WHERE NAME = :name AND CATEGORY = :category")
    suspend fun select(name: String, category: String): List<DbWeaponType>

}