package com.alancamargo.weapons.data.repository.type

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.WeaponTypeLocalDataSource
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

class WeaponTypeRepositoryImplTest {

    @MockK lateinit var mockLocalDataSource: WeaponTypeLocalDataSource
    @MockK lateinit var mockCrashReportHelper: CrashReportHelper

    private lateinit var repository: WeaponTypeRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        val ioHelper = IoHelper(mockCrashReportHelper)

        repository = WeaponTypeRepositoryImpl(mockLocalDataSource, ioHelper)
    }

    @Test
    fun shouldGetWeaponTypes() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponTypes() } returns listOf(mockk(), mockk(), mockk())

        val result = repository.getWeaponTypes()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(3)
    }

    @Test
    fun getCountries_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponTypes() } throws exception

        repository.getWeaponTypes()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getCountries_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponTypes() } throws IOException()

        val result = repository.getWeaponTypes()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

}