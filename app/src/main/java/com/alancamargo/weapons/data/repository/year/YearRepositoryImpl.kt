package com.alancamargo.weapons.data.repository.year

import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.YearLocalDataSource
import com.alancamargo.weapons.domain.entities.Year

class YearRepositoryImpl(
    private val localDataSource: YearLocalDataSource,
    private val ioHelper: IoHelper
) : YearRepository {

    override suspend fun getYears(): Result<List<Year>> = ioHelper.safeIoCall {
        localDataSource.getYears()
    }

}