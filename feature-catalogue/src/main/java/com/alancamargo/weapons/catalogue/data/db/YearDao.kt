package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.catalogue.data.model.DbYear

@Dao
internal interface YearDao {

    @Query("SELECT * FROM YEAR ORDER BY YEAR")
    suspend fun selectAll(): List<DbYear>

    @Query("SELECT * FROM YEAR WHERE ID = :id")
    suspend fun selectById(id: Long): DbYear
}
