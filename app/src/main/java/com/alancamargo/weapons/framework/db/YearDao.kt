package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.entities.DbYear

@Dao
interface YearDao {

    @Query("SELECT * FROM YEAR ORDER BY YEAR")
    suspend fun selectAll(): List<DbYear>

    @Query("SELECT * FROM YEAR WHERE ID = :id")
    suspend fun selectById(id: Long): DbYear

}