package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.model.entities.DbWeaponType

@Dao
interface WeaponTypeDao {

    @Query("SELECT * FROM WEAPON_TYPE")
    suspend fun selectAll(): List<DbWeaponType>

    @Query("SELECT * FROM WEAPON_TYPE WHERE ID = :id")
    suspend fun selectById(id: Long): DbWeaponType

    @Query("SELECT * FROM WEAPON_TYPE WHERE NAME_ID = :name")
    suspend fun selectByNameId(name: String): List<DbWeaponType>
    
    @Query("SELECT * FROM WEAPON_TYPE WHERE NAME_ID = :nameId AND CATEGORY_ID = :categoryId")
    suspend fun selectByNameIdAndCategoryId(nameId: String, categoryId: String): List<DbWeaponType>

}