package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.catalogue.data.model.DbManufacturer

@Dao
internal interface ManufacturerDao {

    @Query("SELECT * FROM MANUFACTURER ORDER BY NAME")
    suspend fun selectAll(): List<DbManufacturer>

    @Query("SELECT * FROM MANUFACTURER WHERE ID = :id")
    suspend fun selectById(id: Long): DbManufacturer
}
