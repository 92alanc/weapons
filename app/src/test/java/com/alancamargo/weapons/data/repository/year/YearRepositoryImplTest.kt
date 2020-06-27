package com.alancamargo.weapons.data.repository.year

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.YearLocalDataSource
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

class YearRepositoryImplTest {

    @MockK lateinit var mockLocalDataSource: YearLocalDataSource
    @MockK lateinit var mockCrashReportHelper: CrashReportHelper

    private lateinit var repository: YearRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        val ioHelper = IoHelper(mockCrashReportHelper)

        repository = YearRepositoryImpl(mockLocalDataSource, ioHelper)
    }

    @Test
    fun shouldGetYears() = runBlocking {
        coEvery { mockLocalDataSource.getYears() } returns listOf(mockk(), mockk(), mockk())

        val result = repository.getYears()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(3)
    }

    @Test
    fun getYears_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getYears() } throws exception

        repository.getYears()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getYears_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getYears() } throws IOException()

        val result = repository.getYears()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

}