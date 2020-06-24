package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.entities.DbManufacturer

@Dao
interface ManufacturerDao {

    @Query("SELECT * FROM MANUFACTURER")
    suspend fun selectAll(): List<DbManufacturer>

    @Query("SELECT * FROM MANUFACTURER WHERE ID = :id")
    suspend fun selectById(id: Long): DbManufacturer

}