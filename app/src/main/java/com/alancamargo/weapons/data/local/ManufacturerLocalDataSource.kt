package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.entities.Manufacturer

interface ManufacturerLocalDataSource {

    suspend fun getManufacturers(): List<Manufacturer>

    suspend fun getManufacturerById(id: Long): Manufacturer

}