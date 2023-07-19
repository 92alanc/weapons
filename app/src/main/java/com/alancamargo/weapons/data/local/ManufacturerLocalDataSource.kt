package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.catalogue.domain.model.Manufacturer

interface ManufacturerLocalDataSource {

    suspend fun getManufacturers(): List<Manufacturer>

    suspend fun getManufacturerById(id: Long): Manufacturer

}