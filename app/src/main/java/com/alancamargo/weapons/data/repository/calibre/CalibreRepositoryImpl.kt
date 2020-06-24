package com.alancamargo.weapons.data.repository.calibre

import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.CalibreLocalDataSource
import com.alancamargo.weapons.domain.Calibre

class CalibreRepositoryImpl(
    private val localDataSource: CalibreLocalDataSource,
    private val ioHelper: IoHelper
) : CalibreRepository {

    override suspend fun getCalibres(): Result<List<Calibre>> = ioHelper.safeIoCall {
        localDataSource.getCalibres()
    }

}