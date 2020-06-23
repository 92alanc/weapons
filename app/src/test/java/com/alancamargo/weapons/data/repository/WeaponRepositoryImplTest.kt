package com.alancamargo.weapons.data.repository

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.WeaponLocalDataSource
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

class WeaponRepositoryImplTest {

    @MockK lateinit var mockLocalDataSource: WeaponLocalDataSource
    @MockK lateinit var mockCrashReportHelper: CrashReportHelper

    private lateinit var repository: WeaponRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        val ioHelper = IoHelper(mockCrashReportHelper)
        repository = WeaponRepositoryImpl(mockLocalDataSource, ioHelper)
    }

    @Test
    fun shouldGetWeapons() = runBlocking {
        coEvery { mockLocalDataSource.getWeapons() } returns listOf(mockk(), mockk(), mockk())

        val result = repository.getWeapons()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(3)
    }

    @Test
    fun getWeapons_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeapons() } throws exception

        repository.getWeapons()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeapons_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeapons() } throws IOException()

        val result = repository.getWeapons()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponById() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponById(any()) } returns mockk()

        val result = repository.getWeaponById(111L)

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body).isNotNull()
    }

    @Test
    fun getWeaponById_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponById(any()) } throws exception

        repository.getWeaponById(111L)

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponById_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponById(any()) } throws IOException()

        val result = repository.getWeaponById(111L)

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByName() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByName(any()) } returns listOf(mockk(), mockk())

        val result = repository.getWeaponsByName("AK-47")

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByName_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByName(any()) } throws exception

        repository.getWeaponsByName("AK-47")

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByName_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByName(any()) } throws IOException()

        val result = repository.getWeaponsByName("AK-47")

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByYear() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByYear(any()) } returns listOf(mockk(), mockk())

        val result = repository.getWeaponsByYear(1947)

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByYear_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByYear(any()) } throws exception

        repository.getWeaponsByYear(1947)

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByYear_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByYear(any()) } throws IOException()

        val result = repository.getWeaponsByYear(1947)

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByCountry() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByCountry(any()) } returns listOf(mockk(), mockk())

        val result = repository.getWeaponsByCountry(222L)

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByCountry_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByCountry(any()) } throws exception

        repository.getWeaponsByCountry(222L)

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByCountry_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByCountry(any()) } throws IOException()

        val result = repository.getWeaponsByCountry(222L)

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByType() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByType(any()) } returns listOf(mockk(), mockk())

        val result = repository.getWeaponsByType(333L)

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByType_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByType(any()) } throws exception

        repository.getWeaponsByType(333L)

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByType_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByType(any()) } throws IOException()

        val result = repository.getWeaponsByType(333L)

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByCalibre() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByCalibre(any()) } returns listOf(mockk(), mockk())

        val result = repository.getWeaponsByCalibre(444L)

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByCalibre_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByCalibre(any()) } throws exception

        repository.getWeaponsByCalibre(444L)

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByCalibre_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByCalibre(any()) } throws IOException()

        val result = repository.getWeaponsByCalibre(444L)

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

}