package com.alancamargo.weapons.data.repository.manufacturer

import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.ManufacturerLocalDataSource
import com.alancamargo.weapons.domain.Manufacturer

class ManufacturerRepositoryImpl(
    private val localDataSource: ManufacturerLocalDataSource,
    private val ioHelper: IoHelper
) : ManufacturerRepository {

    override suspend fun getManufacturers(): Result<List<Manufacturer>> = ioHelper.safeIoCall {
        localDataSource.getManufacturers()
    }

}