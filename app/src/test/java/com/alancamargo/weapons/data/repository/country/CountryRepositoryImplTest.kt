package com.alancamargo.weapons.data.repository.country

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.CountryLocalDataSource
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CountryRepositoryImplTest {

    @MockK lateinit var mockLocalDataSource: CountryLocalDataSource
    @MockK lateinit var mockCrashReportHelper: CrashReportHelper

    private lateinit var repository: CountryRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        val ioHelper = IoHelper(mockCrashReportHelper)

        repository = CountryRepositoryImpl(mockLocalDataSource, ioHelper)
    }

    @Test
    fun shouldGetCountries() = runBlocking {
        coEvery { mockLocalDataSource.getCountries() } returns listOf(mockk(), mockk(), mockk())

        val result = repository.getCountries()

        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        Truth.assertThat(result.body.size).isEqualTo(3)
    }

    @Test
    fun getCountries_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getCountries() } throws exception

        repository.getCountries()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getCountries_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getCountries() } throws IOException()

        val result = repository.getCountries()

        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
    }

}