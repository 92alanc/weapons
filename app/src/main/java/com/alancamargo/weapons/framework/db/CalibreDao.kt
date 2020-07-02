package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.entities.DbCalibre

@Dao
interface CalibreDao {

    @Query("SELECT * FROM CALIBRE ORDER BY NAME")
    suspend fun selectAll(): List<DbCalibre>

    @Query("SELECT * FROM CALIBRE WHERE ID = :id")
    suspend fun selectById(id: Long): DbCalibre

}