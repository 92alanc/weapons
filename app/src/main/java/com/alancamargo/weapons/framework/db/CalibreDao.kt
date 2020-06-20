package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.model.entities.DbCalibre

@Dao
interface CalibreDao {

    @Query("SELECT * FROM CALIBRE")
    suspend fun select(): List<DbCalibre>

    @Query("SELECT * FROM CALIBRE WHERE ID = :id")
    suspend fun select(id: Long): DbCalibre

}