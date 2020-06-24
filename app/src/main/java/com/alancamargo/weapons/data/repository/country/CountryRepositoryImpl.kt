package com.alancamargo.weapons.data.repository.country

import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.CountryLocalDataSource
import com.alancamargo.weapons.domain.entities.Country

class CountryRepositoryImpl(
    private val localDataSource: CountryLocalDataSource,
    private val ioHelper: IoHelper
) : CountryRepository {

    override suspend fun getCountries(): Result<List<Country>> = ioHelper.safeIoCall {
        localDataSource.getCountries()
    }

}