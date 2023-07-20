package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.catalogue.data.model.DbCalibre

@Dao
internal interface CalibreDao {

    @Query("SELECT * FROM CALIBRE ORDER BY NAME")
    suspend fun selectAll(): List<DbCalibre>

    @Query("SELECT * FROM CALIBRE WHERE ID = :id")
    suspend fun selectById(id: Long): DbCalibre
}
