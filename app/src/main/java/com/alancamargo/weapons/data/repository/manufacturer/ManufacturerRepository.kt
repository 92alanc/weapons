package com.alancamargo.weapons.data.repository.manufacturer

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.domain.entities.Manufacturer

interface ManufacturerRepository {

    suspend fun getManufacturers(): Result<List<Manufacturer>>

}