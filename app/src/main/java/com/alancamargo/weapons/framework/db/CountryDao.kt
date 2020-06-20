package com.alancamargo.weapons.framework.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.weapons.framework.model.entities.DbCountry

@Dao
interface CountryDao {

    @Query("SELECT * FROM COUNTRY")
    suspend fun select(): List<DbCountry>

    @Query("SELECT * FROM COUNTRY WHERE ID = :id")
    suspend fun select(id: Long): DbCountry

}