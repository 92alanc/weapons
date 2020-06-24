package com.alancamargo.weapons.data.repository.manufacturer

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.ManufacturerLocalDataSource
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

class ManufacturerRepositoryImplTest {

    @MockK lateinit var mockLocalDataSource: ManufacturerLocalDataSource
    @MockK lateinit var mockCrashReportHelper: CrashReportHelper

    private lateinit var repository: ManufacturerRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        val ioHelper = IoHelper(mockCrashReportHelper)

        repository = ManufacturerRepositoryImpl(mockLocalDataSource, ioHelper)
    }

    @Test
    fun shouldGetManufacturers() = runBlocking {
        coEvery { mockLocalDataSource.getManufacturers() } returns listOf(mockk(), mockk(), mockk())

        val result = repository.getManufacturers()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(3)
    }

    @Test
    fun getManufacturers_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getManufacturers() } throws exception

        repository.getManufacturers()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getManufacturers_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getManufacturers() } throws IOException()

        val result = repository.getManufacturers()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

}