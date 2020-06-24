package com.alancamargo.weapons.data.repository.calibre

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.CalibreLocalDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CalibreRepositoryImplTest {

    @MockK lateinit var mockLocalDataSource: CalibreLocalDataSource
    @MockK lateinit var mockCrashReportHelper: CrashReportHelper

    private lateinit var repository: CalibreRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        val ioHelper = IoHelper(mockCrashReportHelper)

        repository = CalibreRepositoryImpl(mockLocalDataSource, ioHelper)
    }

    @Test
    fun shouldGetCalibres() = runBlocking {
        coEvery { mockLocalDataSource.getCalibres() } returns listOf(mockk(), mockk(), mockk())

        val result = repository.getCalibres()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(3)
    }

    @Test
    fun getCalibres_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getCalibres() } throws exception

        repository.getCalibres()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getCalibres_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getCalibres() } throws IOException()

        val result = repository.getCalibres()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

}