package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.Manufacturer

interface ManufacturerLocalDataSource {

    suspend fun getManufacturers(): List<Manufacturer>

}