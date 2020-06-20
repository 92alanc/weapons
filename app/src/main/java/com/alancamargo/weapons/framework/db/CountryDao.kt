package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.model.entities.DbCountry

@Dao
interface CountryDao {

    @Query("SELECT * FROM COUNTRY")
    suspend fun selectAll(): List<DbCountry>

    @Query("SELECT * FROM COUNTRY WHERE ID = :id")
    suspend fun selectById(id: Long): DbCountry

}